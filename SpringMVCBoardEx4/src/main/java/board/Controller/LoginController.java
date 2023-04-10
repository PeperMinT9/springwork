package board.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.dao.MemberDao;

@Controller
public class LoginController {
	@Autowired
	MemberDao memberDao;
	
	@GetMapping("/login/form")
	public String login(HttpSession session) {
		// 로그인 여부를 판달할 세션 - 없을경우
		String login_ok = (String)session.getAttribute("login_ok");
		
		if(login_ok == null) 
			return "login/login";
		else
			return "login/logout";
	}
	
	@PostMapping("/login/loginaction")
	public String loginAction(
			@RequestParam String email,
			@RequestParam String pass,
			@RequestParam(required = false) String save_email,/*체크 안했을때 null 값으로 넘어온다*/
			//@RequestParam(defaultValue = "no") String saveemail, /*체크 안했을경우 no 값으로 변환되서 넘어온다*/
			HttpSession session
			)
	{
		//System.out.println("saveemail="+saveemail);
		//이메일과 비번이 맞는지부터 체크
		int count = memberDao.isEqualPassEmail(email, pass);
		//이메일과 비번이 맞는경우 1반환
		if(count == 1) {
			//로그인 성공시 세션에 저장하기
			session.setAttribute("login_ok", "yes");
			session.setAttribute("login_email", email);
			session.setAttribute("save_email", save_email == null? "no" : "yes");			
			
			return "redirect:../board/list";//로그인 성공시 게시판목록으로 이동
		}else {
			//실패시 loginfail.jsp 로 가서 자바스크립트 코드 실행
			return "login/loginfail";
		}
	}
	
	@GetMapping("/login/logout")
	public String logout(HttpSession session)
	{
		session.removeAttribute("login_ok");
		return "redirect:form";
	}
}
