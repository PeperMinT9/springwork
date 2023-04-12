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
		// ���ǿ� ����� �̸���
		String email = (String)session.getAttribute("login_email");
		// �� �̸��Ͽ� �ش��ϴ� member ���̺��� num��
		int num = memberDao.selectOneOfEmail(email).getNum();
		// dto�� num����
		dto.setNum(num);
		
		// ���ε��� ���
		String realPath = request.getSession().getServletContext().getRealPath("/resources/photo");
		String images = "";
		// ������ ���ε� ��������� db�� no��� �����غ���
		// ���ε�������� ���ϸ��� ��¥�� �����غ���
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fname = sdf.format(new Date());
		
		// ���ε带 ���Ѱ��
		if(upload.get(0).getOriginalFilename().equals("")) {
			images = "no";
		}
		else {
			int i = 0;
			for(MultipartFile mfile: upload) {
				String originalName = mfile.getOriginalFilename();
				// . �� �������� ��������(������ .��� �����)
				StringTokenizer st = new StringTokenizer(originalName, ".");
				String fileName = st.nextToken();
				String extName = st.nextToken();
				System.out.println(fileName + ", " + extName);
				
				// ���ϸ��� ��¥�� �����ϱ�(�ڿ� �ε��� ���̱�)
				fileName = fname + "_" + i++ + "." + extName;
				System.out.println(fileName); // ���ε��� ���� ���ϸ�
				
				images += fileName + ",";
				
				// ���� ���ε�
				try {
					mfile.transferTo(new File(realPath + "/" + fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// dto�� photo�� ����
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
		
		// �Խ����� �� �۰��� ���
		int totalCount = boardDao.getTotalCount();
		int totalPage = -1; // ����������
		int perPage = 3; // �� �������� ������ ���� ����
		int perBlock = 3; // �� ���� ������ ������ ����
		int startNum = -1; // ���������� ������ ���� ���۹�ȣ
		int startPage = -1; // �� ������ ������ ���� ������ ��ȣ
		int endPage = -1; // �� ������ ������ �� ������ ��ȣ
		int no = -1; // �� ��½� ����� ���۹�ȣ
		
		// �������� ��
		totalPage = totalCount / perPage + (totalCount % perPage == 0? 0 : 1);
		// ����������
		startPage = (currentPage - 1)/perBlock * perBlock + 1;
		// ��������
		endPage = startPage + perBlock - 1;
		// �̶������� ....endPage�� totalPage���� ũ�� �ȵȴ�
		if(endPage > totalPage)
			endPage = totalPage;
		
		// �� �������� ���۹�ȣ(1������: 0, 2������: 3, 3������: 6)
		startNum = (currentPage - 1) * perPage;
		// �� �۸��� ����� �۹�ȣ(��: 10���� ���, 1������: 10, 2������: 7...)
		no = totalCount - startNum;
		
		// �� �������� �ʿ��� ��ñ� db���� ��������
		List<BoardDto> list = boardDao.getPagingList(startNum, perPage);
		
		// �� �Խñۿ� �۾������ name�� dto�� �����ϱ�
		for(BoardDto bdto: list) {
			// �۾������ num
			int num = bdto.getNum();
			// num�� �ش��ϴ� ����� �̸�
			String name = "";
			try {
				// num�� �ش��ϴ� �����Ͱ� ������� �������Ϳ����� �߻�
				name = memberDao.selectOneOfNum(num).getName();
			}
			catch (NullPointerException e) {
				name = "Ż��ȸ��";
			}
					
			// bdto�� ����
			bdto.setName(name);
			
			// ��۰��� acount�� ����
			int acount = answerDao.getAllAnswer(bdto.getIdx()).size();
			bdto.setAcount(acount);
		}
		
		// ��½� �ʿ��� �������� model�� ��� ����		
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
		// ��ȸ�� ���� �ʿ�
		boardDao.updateReadCount(idx);
		
		// board ���̺��� dto �����͸� ��´�
		BoardDto dto = boardDao.selectOneBoard(idx);
		
		String name = "";
		String photo = "";
		
		try {
			// num�� �ش��ϴ� name, photo�� �� dto�� �־��ش�
			name = memberDao.selectOneOfNum(dto.getNum()).getName();
			photo = memberDao.selectOneOfNum(dto.getNum()).getPhoto();
			
			dto.setName(name);
			dto.setPhoto(photo);
		}
		catch (NullPointerException e) {
			dto.setName("Ż����ȸ��");
			dto.setPhoto("no_image.png");
		}
		
		// model�� dto, currentPage
		model.addAttribute("dto", dto);
		model.addAttribute("currentPage", currentPage);
		
		return "board/content";
	}
	
	@GetMapping("/board/delete")
	public String delete(@RequestParam int idx, @RequestParam int currentPage) {
		// ������ ���� ������ ������� �̵�
		boardDao.deleteBoard(idx);
		
		return "redirect:list?currentPage=" + currentPage;
	}
	
	@GetMapping("/board/updateform")
	public String updateForm(Model model, @RequestParam int idx, @RequestParam int currentPage) {
		// idx�� �ش��ϴ� dto ���
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
		
		// ���ε��� ���
		String realPath = request.getSession().getServletContext().getRealPath("/resources/photo");
		String images = "";
		// ������ ���ε� ��������� db�� no��� �����غ���
		// ���ε�������� ���ϸ��� ��¥�� �����غ���
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fname = sdf.format(new Date());
		
		// ���ε带 ���Ѱ��
		if(upload.get(0).getOriginalFilename().equals("")) {
			images = null; // ���� ������ �����ȴ�
		}
		else {
			int i = 0;
			for(MultipartFile mfile: upload) {
				String originalName = mfile.getOriginalFilename();
				// . �� �������� ��������(������ .��� �����)
				StringTokenizer st = new StringTokenizer(originalName, ".");
				String fileName = st.nextToken();
				String extName = st.nextToken();
				System.out.println(fileName + ", " + extName);
				
				// ���ϸ��� ��¥�� �����ϱ�(�ڿ� �ε��� ���̱�)
				fileName = fname + "_" + i++ + "." + extName;
				System.out.println(fileName); // ���ε��� ���� ���ϸ�
				
				images += fileName + ",";
				
				// ���� ���ε�
				try {
					mfile.transferTo(new File(realPath + "/" + fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// dto�� photo�� ����
		dto.setImages(images);
		
		// db ����
		boardDao.updateBoard(dto);
		
		// ���뺸��
		return "redirect:content?idx=" + dto.getIdx() + "&currentPage=" + currentPage;
	}
}
