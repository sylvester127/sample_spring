package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.example.demo.domain.MemberVO;
import com.example.demo.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;

	@GetMapping("")
	public String index() {
		return "member/login";
	}

	@GetMapping("members")
	public String join(Model model, HttpServletRequest request) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		
		if(null != inputFlashMap) {
			model.addAttribute("message", inputFlashMap.get("message"));
		}
		
		return "member/join";
	}
	
	@PostMapping("members")
	public String join(MemberVO member, RedirectAttributes redirectAttributes) {
		int result = service.joinMember(member);
		String path = "redirect:/members";
		
		if (result == 1) {
			path = "redirect:/";
		} else if (result == 0) {
			redirectAttributes.addFlashAttribute("message", "Duplicated id");
		} else if (result == -1) {
			redirectAttributes.addFlashAttribute("message", "Enter ID and password");
		}
		
		return path;
	}

	@PostMapping("members/{id}")
	public String login(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "pw", required = false) String pw, HttpSession session) {
		String path = "member/login";
		
		// 로그인 성공
		if (service.checkMember(id, pw)) {
			session.setAttribute("id", id);
			path = "redirect:notices";			
		}
		
		return path;
	}

	@GetMapping("members/{id}")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:login";
	}

}
