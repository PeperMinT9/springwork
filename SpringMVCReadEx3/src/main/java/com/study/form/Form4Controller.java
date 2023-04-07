package com.study.form;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Form4Controller {
	
	@GetMapping("/form4") 
	public String form4() {
		return "myform/form4";
	}
	
	@PostMapping("/read4")
	public String read4(@RequestParam String message, 
			HttpServletRequest request,
			@RequestParam("oneupload") MultipartFile one,
			@RequestParam("multiupload") ArrayList<MultipartFile> multi,
			Model model) {
		
		//사진을 업로드할 실제경로 구하기
		String realPath = request.getSession().getServletContext().getRealPath("/resources/image");
		System.out.println(realPath);
		
		String onefile = "";
		String multifile = "";
		
		// 업로드
		try {
			one.transferTo(new File(realPath + "/" + one.getOriginalFilename()));
			// 1개 업로드한 파일명 저장
			onefile = one.getOriginalFilename();
			
			// 여러개 업로드
			for(MultipartFile mfile: multi) {
				mfile.transferTo(new File(realPath + "/" + mfile.getOriginalFilename()));
				
				// 만약 3개 업로드시 a.jpg,b.jpg,c.jpg,
				multifile += mfile.getOriginalFilename() + ",";
			}
			multifile = multifile.substring(0, multifile.length() - 1);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// model에 필요한 데이터를 저장
		model.addAttribute("message", message);
		model.addAttribute("onefile", onefile);
		model.addAttribute("multifile", multifile);
		
		return "result/readdata4";
	}
}
