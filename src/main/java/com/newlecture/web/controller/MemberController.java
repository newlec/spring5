package com.newlecture.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@GetMapping("login")
	public String login() {
		
		return "member.login";
	}
	
	/*
	@PostMapping("login")
	public String login(String id, String pwd) {
		
		return "redirect:../index";
	}
	*/
}
