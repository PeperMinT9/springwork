package board.Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import board.dao.AnswerDao;
import board.dao.BoardDao;
import board.dao.MemberDao;
import board.dto.BoardDto;
import board.dto.MemberDto;

@Controller
public class BoardController {
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	BoardDao boardDao;
	
	@Autowired
	AnswerDao answerDao;
	
	@GetMapping("/board/form")
	public String form() {
		
		
		return "board/form";
	}
	
	@PostMapping("/board/addboard")
	public String insertBoard(@ModelAttribute BoardDto dto,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpServletRequest request,
			HttpSession session) {
		// 세션에 저장된 이메일
		String email = (String)session.getAttribute("login_email");
		// 그 이메일에 해당하는 member 테이블의 num값
		int num = memberDao.selectOneOfEmail(email).getNum();
		// dto에 num저장
		dto.setNum(num);
		
		// 업로드할 경로
		String realPath = request.getSession().getServletContext().getRealPath("/resources/photo");
		String images = "";
		// 사진을 업로드 안했을경우 db에 no라고 저장해보자
		// 업로드했을경우 파일명을 날짜로 변경해보자
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fname = sdf.format(new Date());
		
		// 업로드를 안한경우
		if(upload.get(0).getOriginalFilename().equals("")) {
			images = "no";
		}
		else {
			int i = 0;
			for(MultipartFile mfile: upload) {
				String originalName = mfile.getOriginalFilename();
				// . 를 기준으로 나눠보자(사진에 .사용 비허용)
				StringTokenizer st = new StringTokenizer(originalName, ".");
				String fileName = st.nextToken();
				String extName = st.nextToken();
				System.out.println(fileName + ", " + extName);
				
				// 파일명을 날짜로 변경하기(뒤에 인덱스 붙이기)
				fileName = fname + "_" + i++ + "." + extName;
				System.out.println(fileName); // 업로드할 최종 파일명
				
				images += fileName + ",";
				
				// 사진 업로드
				try {
					mfile.transferTo(new File(realPath + "/" + fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// dto에 photo값 저장
		dto.setImages(images);
		
		boardDao.insertBoard(dto);
		
		return "redirect:list";
	}
	
	@GetMapping("board/list")
	public String list(HttpSession session, Model model,
			@RequestParam(defaultValue = "1") int currentPage) {
		String email=(String)session.getAttribute("login_email");
		MemberDto dto = memberDao.selectOneOfEmail(email);
	
		model.addAttribute("dto", dto);
		
		// 게시판의 총 글갯수 얻기
		int totalCount = boardDao.getTotalCount();
		int totalPage = -1; // 총페이지수
		int perPage = 3; // 한 페이지당 보여질 글의 갯수
		int perBlock = 3; // 한 블럭당 보여질 페이지 갯수
		int startNum = -1; // 페이지에서 보여질 글의 시작번호
		int startPage = -1; // 각 블럭에서 보여질 시작 페이지 번호
		int endPage = -1; // 각 블럭에서 보여질 끝 페이지 번호
		int no = -1; // 글 출력시 출력할 시작번호
		
		// 총페이지 수
		totalPage = totalCount / perPage + (totalCount % perPage == 0? 0 : 1);
		// 시작페이지
		startPage = (currentPage - 1)/perBlock * perBlock + 1;
		// 끝페이지
		endPage = startPage + perBlock - 1;
		// 이때문제점 ....endPage가 totalPage보다 크면 안된다
		if(endPage > totalPage)
			endPage = totalPage;
		
		// 각 페이지의 시작번호(1페이지: 0, 2페이지: 3, 3페이지: 6)
		startNum = (currentPage - 1) * perPage;
		// 각 글마다 출력할 글번호(예: 10개일 경우, 1페이지: 10, 2페이지: 7...)
		no = totalCount - startNum;
		
		// 각 페이지에 필요한 기시글 db에서 가져오기
		List<BoardDto> list = boardDao.getPagingList(startNum, perPage);
		
		// 각 게시글에 글쓴사람의 name을 dto에 저장하기
		for(BoardDto bdto: list) {
			// 글쓴사람의 num
			int num = bdto.getNum();
			// num에 해당하는 사람의 이름
			String name = "";
			try {
				// num에 해당하는 데이터가 없을경우 널포인터엑셉션 발생
				name = memberDao.selectOneOfNum(num).getName();
			}
			catch (NullPointerException e) {
				name = "탈퇴회원";
			}
					
			// bdto에 저장
			bdto.setName(name);
			
			// 댓글갯수 acount에 저장
			int acount = answerDao.getAllAnswer(bdto.getIdx()).size();
			bdto.setAcount(acount);
		}
		
		// 출력시 필요한 변수들을 model에 모두 저장		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("no", no);
		
		return "board/list";
	}
	
	@GetMapping("/board/content")
	public String content(Model model, @RequestParam int idx, @RequestParam int currentPage) {
		// 조회수 증가 필요
		boardDao.updateReadCount(idx);
		
		// board 테이블의 dto 데이터를 얻는다
		BoardDto dto = boardDao.selectOneBoard(idx);
		
		String name = "";
		String photo = "";
		
		try {
			// num에 해당하는 name, photo를 얻어서 dto에 넣어준다
			name = memberDao.selectOneOfNum(dto.getNum()).getName();
			photo = memberDao.selectOneOfNum(dto.getNum()).getPhoto();
			
			dto.setName(name);
			dto.setPhoto(photo);
		}
		catch (NullPointerException e) {
			dto.setName("탈퇴한회원");
			dto.setPhoto("no_image.png");
		}
		
		// model에 dto, currentPage
		model.addAttribute("dto", dto);
		model.addAttribute("currentPage", currentPage);
		
		return "board/content";
	}
	
	@GetMapping("/board/delete")
	public String delete(@RequestParam int idx, @RequestParam int currentPage) {
		// 삭제후 보던 페이지 목록으로 이동
		boardDao.deleteBoard(idx);
		
		return "redirect:list?currentPage=" + currentPage;
	}
	
	@GetMapping("/board/updateform")
	public String updateForm(Model model, @RequestParam int idx, @RequestParam int currentPage) {
		// idx에 해당하는 dto 얻기
		BoardDto dto = boardDao.selectOneBoard(idx);
		
		model.addAttribute("dto", dto);
		model.addAttribute("currentPage", currentPage);
		
		return "board/updateform";
	}
	
	@PostMapping("/board/updateboard")
	public String updateBoard(@ModelAttribute BoardDto dto,
			@RequestParam ArrayList<MultipartFile> upload,
			@RequestParam int currentPage,
			HttpServletRequest request) {
		
		// 업로드할 경로
		String realPath = request.getSession().getServletContext().getRealPath("/resources/photo");
		String images = "";
		// 사진을 업로드 안했을경우 db에 no라고 저장해보자
		// 업로드했을경우 파일명을 날짜로 변경해보자
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fname = sdf.format(new Date());
		
		// 업로드를 안한경우
		if(upload.get(0).getOriginalFilename().equals("")) {
			images = null; // 기존 사진이 유지된다
		}
		else {
			int i = 0;
			for(MultipartFile mfile: upload) {
				String originalName = mfile.getOriginalFilename();
				// . 를 기준으로 나눠보자(사진에 .사용 비허용)
				StringTokenizer st = new StringTokenizer(originalName, ".");
				String fileName = st.nextToken();
				String extName = st.nextToken();
				System.out.println(fileName + ", " + extName);
				
				// 파일명을 날짜로 변경하기(뒤에 인덱스 붙이기)
				fileName = fname + "_" + i++ + "." + extName;
				System.out.println(fileName); // 업로드할 최종 파일명
				
				images += fileName + ",";
				
				// 사진 업로드
				try {
					mfile.transferTo(new File(realPath + "/" + fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// dto에 photo값 저장
		dto.setImages(images);
		
		// db 수정
		boardDao.updateBoard(dto);
		
		// 내용보기
		return "redirect:content?idx=" + dto.getIdx() + "&currentPage=" + currentPage;
	}
}
