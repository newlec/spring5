package com.newlecture.web.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberDao memberDao;

	@GetMapping("login")
	public String login() {
		
		return "member.login";
	}
	
	@GetMapping("join")
	public String join() {
		
		return "member.join";
	}
	
	@PostMapping("join")
	public String join(Member member) throws ClassNotFoundException, SQLException {
				
		String pwd = member.getPwd();
		
		PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		pwd = pwdEncoder.encode(pwd);
		
		member.setPwd(pwd);
		
		memberDao.insert(member);
		//memberRoleDao.insert(memberRole); ROLE_MEMBER
				
		return "redirect:../index";
	}
	
	/*
	@PostMapping("login")
	public String login(String id, String pwd) {
		
		return "redirect:../index";
	}
	*/
}
