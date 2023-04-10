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
		// �α��� ���θ� �Ǵ��� ���� - �������
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
			@RequestParam(required = false) String save_email,/*üũ �������� null ������ �Ѿ�´�*/
			//@RequestParam(defaultValue = "no") String saveemail, /*üũ ��������� no ������ ��ȯ�Ǽ� �Ѿ�´�*/
			HttpSession session
			)
	{
		//System.out.println("saveemail="+saveemail);
		//�̸��ϰ� ����� �´������� üũ
		int count = memberDao.isEqualPassEmail(email, pass);
		//�̸��ϰ� ����� �´°�� 1��ȯ
		if(count == 1) {
			//�α��� ������ ���ǿ� �����ϱ�
			session.setAttribute("login_ok", "yes");
			session.setAttribute("login_email", email);
			session.setAttribute("save_email", save_email == null? "no" : "yes");			
			
			return "redirect:../board/list";//�α��� ������ �Խ��Ǹ������ �̵�
		}else {
			//���н� loginfail.jsp �� ���� �ڹٽ�ũ��Ʈ �ڵ� ����
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
