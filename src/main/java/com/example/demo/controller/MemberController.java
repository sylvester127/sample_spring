package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.MemberVO;
import com.example.demo.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;

	@GetMapping("join")
	public String join() {
		return "member/join";
	}

	@PostMapping("join")
	public String join(MemberVO member, Model model) {
		int result = service.insertMember(member);
		
		if (result == 0) {
			model.addAttribute("message", "duplicated id");
		}
		
		return "redirect:login";
	}

	@GetMapping("login")
	public String login() {
		return "member/login";
	}

	@PostMapping("login")
	public String login(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "pw", required = false) String pw, HttpSession session) {
		String path = "member/login";
		
		// 로그인 성공
		if (service.checkMember(id, pw)) {		
			session.setAttribute("userId", id);
			path = "redirect:list";			
		}
		
		return path;
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "member/login";
	}

}
