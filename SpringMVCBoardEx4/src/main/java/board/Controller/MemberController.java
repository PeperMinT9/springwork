package board.Controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import board.dao.MemberDao;
import board.dto.MemberDto;

@Controller
public class MemberController {
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/member/form")
	public String form(Model model) {
		// 가입된 총 인원수를 폼위에 출력하기 위해 값을 얻는다
		int totalCount = memberDao.getTotalCount();
		// request에 저장
		model.addAttribute("totalCount", totalCount);
		
		return "member/form";
	}
	
	@PostMapping("/member/addmember")
	public String insertmember(
			@ModelAttribute MemberDto dto,
			@RequestParam MultipartFile upload,
			HttpServletRequest request) 
	{
		// 업로드할 폴더 경로 구하기
		String realFolder = request.getSession().getServletContext().getRealPath("/resources/photo");
		System.out.println(realFolder);
		// 사진 업로드
		String photo = upload.getOriginalFilename();// 업로드한 파일명

		try {
			upload.transferTo(new File(realFolder + "/" + photo));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dto.setPhoto(photo);
		// insert
		memberDao.insertMember(dto);
		// 회원가입 저장후 멤버 목록으로 이동
		return "redirect:list";
	}
	
	@GetMapping("/member/list")
	public String listMember(Model model) {
		int totalCount = memberDao.getTotalCount();
		List<MemberDto> list = memberDao.getAllMembers();
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("list", list);
		
		return "member/list";
	}
	
	@GetMapping("/member/delete")
	public String delete(@RequestParam int num) {
		memberDao.deleteMember(num);
		
		return "redirect:./list";
	}
	
	// json으로 반환하는 메서드
	// @Controller 일 경우 json반환인 경우 @ResponseBody를 붙인다
	// @RestController일 경우는 무조건 json으로 처리되므로 @ResponseBody를 안붙인다
	@GetMapping("/member/isemail")
	public @ResponseBody Map<String, String> emailProcess(@RequestParam String email) {
		// 해당 이메일이 존재하면 1, 존재하지 않으면 0
		int count = memberDao.isEqualEmail(email);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", count == 0? "success" : "fail");
		
		return map;
	}
	
	@GetMapping("/member/updateform")
	public String update_select(@RequestParam int num, Model model) {
		MemberDto dto = memberDao.selectOneOfNum(num);
		model.addAttribute("dto", dto);
		
		return "member/updatepage";
	}
	
	@PostMapping("/member/updatemember")
	public String update(@ModelAttribute MemberDto dto,
			@RequestParam MultipartFile upload,
			HttpServletRequest request) {
		String realFolder = request.getSession().getServletContext().getRealPath("/resources/photo");
		System.out.println(realFolder);
		// 사진 업로드
		String photo = upload.getOriginalFilename();// 업로드한 파일명
		if(photo.equals("")) {
			dto.setPhoto(null);
		}
		else {
			try {
				upload.transferTo(new File(realFolder + "/" + photo));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		dto.setPhoto(photo);
		// insert
		memberDao.updateOfMember(dto);
		// 회원가입 저장후 멤버 목록으로 이동
		return "redirect:list";
	}
}
