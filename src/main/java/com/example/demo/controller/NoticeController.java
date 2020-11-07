package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.FileVO;
import com.example.demo.domain.NoticeVO;
import com.example.demo.service.NoticeService;

@Controller
@RequestMapping("notices")
public class NoticeController {

	@Autowired
	NoticeService service;

	@GetMapping("")
	private String list(Model model, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");

		model.addAttribute("id", id);
		model.addAttribute("list", service.getList());

		return "notice/list";
	}

	@GetMapping("/reg")
	private String reg() {
		return "notice/reg";
	}

	@ResponseBody
	@PostMapping("/reg")
	private int reg(NoticeVO notice, @RequestParam("files") MultipartFile[] files) throws Exception {
		int result = service.addNotice(notice, files);
		
		return result;
	}

	@GetMapping("{boardNum}")
	private String detail(@PathVariable int boardNum, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("detail", service.getNotice(boardNum));
		model.addAttribute("files", service.fileDetailService(boardNum, request, null));
		service.updateViewCntService(boardNum);
		return "notice/detail";
	}

	@PatchMapping("{boardNum}") // 게시글 수정폼 호출
	private String update(@PathVariable int boardNum, Model model) throws Exception {
		model.addAttribute("detail", service.getNotice(boardNum));
		return "notice/update";
	}

	@GetMapping("updateProc")
	private String updateProc(/* @ModelAttribute */ NoticeVO board, HttpServletRequest request) throws Exception {
		service.regNotice(board, request);
		return "redirect:/detail/" + request.getParameter("boardNum");
	}

	@DeleteMapping("{boardNum}")
	private String delete(@PathVariable int boardNum) throws Exception {
		service.boardDeleteService(boardNum);
		return "redirect:/list";
	}

	@GetMapping("/fileDown/{boardNum}")
	private void fileDown(@PathVariable int boardNum, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		service.fileDetailService(boardNum, request, response);
	}
}
