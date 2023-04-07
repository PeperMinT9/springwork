package com.study.form;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Form3Controller {
	@GetMapping("/form3")
	public String form3( ) {
		return "myform/form3";
	}
	
	@PostMapping("/read3")
	public ModelAndView read3(@RequestParam Map<String, String> map) {
		ModelAndView mview = new ModelAndView();
		
		mview.addObject("name", map.get("name"));
		mview.addObject("addr", map.get("addr"));
		mview.addObject("pn", map.get("pn"));
		mview.addObject("img", map.get("img"));
		
		mview.setViewName("result/readdata3");
		return mview;
	}
}
