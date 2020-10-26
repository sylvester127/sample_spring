package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		// members에서 POST의 request 객체를 받아서 모델에 넣어준다.
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
	
	// 보안의 용의성 때문에 GET보다는 POST로 처리한다.
	// 더 좋은 방법은 SSL(https)로 처리하는 것이다.
	@PostMapping("members/member")
	public String login(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "password", required = false) String password, HttpSession session) {
		String path = "member/login";
		
		// DB에서 아이디, 패스워드에 맞는 결과가 있다면 성공
		if (service.checkMember(id, password)) {
			session.setAttribute("id", id);
			path = "redirect:../notices";			
		}
		
		return path;
	}
	
	@DeleteMapping("members/{id}/session")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:login";
	}

}
