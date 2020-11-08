package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.domain.NoticeVO;
import com.example.demo.service.NoticeService;

@Controller
@RequestMapping("notices")
public class NoticeController {

	@Autowired
	ServletContext context;

	@Autowired
	NoticeService service;

	@GetMapping("")
	private String list(Model model) throws Exception {
		model.addAttribute("list", service.getList());

		return "notice/list";
	}

	@GetMapping("/reg")
	private String reg() {
		return "notice/reg";
	}

	@ResponseBody
	@PostMapping("/reg")
	private int reg(NoticeVO notice, @RequestParam MultipartFile[] files) throws Exception {
		int result = service.regNotice(context, notice, files);
		
		return result;
	}

	@GetMapping("/{noticeNum}")
	private String detail(@PathVariable int noticeNum, Model model) throws Exception {
		Map<String, Object> map = service.getNotice(noticeNum);
		
		model.addAttribute("detail", map.get("notice"));
		model.addAttribute("files", map.get("files"));
		
		return "notice/detail";
	}
	
	@PatchMapping("/{noticeNum}") // 게시글 수정폼 호출
	private String update(@PathVariable int noticeNum, Model model) throws Exception {
		model.addAttribute("detail", service.getNotice(noticeNum));
		
		return "notice/update";
	}
	
//	@GetMapping("updateProc")
//	private String updateProc(/* @ModelAttribute */ NoticeVO board, HttpServletRequest request) throws Exception {
//		service.regNotice(board, request);
//		return "redirect:/detail/" + request.getParameter("boardNum");
//	}

	@DeleteMapping("/{noticeNum}")
	private String delete(@PathVariable int noticeNum) {
		service.deleteNotice(noticeNum);
		
		return "redirect:/notices";
	}
	
//	@GetMapping("/fileDown/{boardNum}")
//	private void fileDown(@PathVariable int boardNum, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		request.setCharacterEncoding("UTF-8");
//		service.fileDetailService(boardNum, request, response);
//	}
}
