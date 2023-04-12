package board.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import board.dao.AnswerDao;
import board.dao.MemberDao;
import board.dto.AnswerDto;

@RestController
public class AnswerController {
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	AnswerDao answerDao;
	
	// 댓글 insert
	@GetMapping("/board/ainsert")
	public void insertAnswer(@RequestParam String content, @RequestParam int idx, @RequestParam int num) {
		System.out.println("num="+num);
		
		// dto에 담는다
		AnswerDto dto = new AnswerDto();
		dto.setContent(content);
		dto.setIdx(idx);
		dto.setNum(num);
		
		answerDao.insertAnswer(dto);
	}
	
	// 댓글 전체 list
	@GetMapping("/board/alist")
	public List<AnswerDto> getAllAnswers(@RequestParam int idx) {
		
		List<AnswerDto> list = answerDao.getAllAnswer(idx); // list가 json형태로 반환
		for(AnswerDto dto: list) {
			try {
				// member 테이블로부터 name, photo를 얻어서 dto에 넣는다
				String name = memberDao.selectOneOfNum(dto.getNum()).getName();
				String photo = memberDao.selectOneOfNum(dto.getNum()).getPhoto();
				
				dto.setName(name);
				dto.setPhoto(photo);
			}
			catch (NullPointerException e) {
				dto.setName("탈퇴한회원");
				dto.setPhoto("no_image.png");
			}
		}
		
		return list;
	}
	
	 @GetMapping("/board/adelete")
	 public void deleteAnswer(@RequestParam int seq) {
		 answerDao.deleteAnswer(seq);
	 }
}
