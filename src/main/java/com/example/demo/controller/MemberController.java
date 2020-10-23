package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.MemberVO;
import com.example.demo.service.UserService;

@Controller
public class MemberController {
	
	@Autowired
	UserService service;
	
	@RequestMapping("/join")
	public String getUser(MemberVO user, Model model)  { 
		return "InsertUser";
	}

	@RequestMapping(value="/join", method= RequestMethod.POST)
	public String insertUser(MemberVO user, Model model)  { 
		int result = service.insertUser(user);
//		System.out.println(user.toString());
		if(result==0){
			model.addAttribute("message","duplicated id");
		}
		return "login";
	}
	
	@RequestMapping("/login")
    public String getLogin(){
		
        return "login";
    }
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public String postLogin(@RequestParam(value="id", required=false) String id, 
    		@RequestParam(value="pw", required=false) String pw, 
    		HttpSession session){

//		System.out.println("ID: " + id + ", " + "Password: " + pw);

		MemberVO user = service.checkUser(id, pw);
//		System.out.println(user == null);	// true면 실패, false면 성공
//		로그인 성공	
		if(user != null) {
//			System.out.println("test");
			session.setAttribute("userId", id);
			return "redirect:list";
//		로그인 실패
		}else{
			return "login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(MemberVO user, Model model, HttpSession session) {
		session.invalidate();		
		//session.setAttribute("loginId",null); 으로 해줘도 된다.
		return "login";
	}
	
}
