package com.example.demo.controller.notice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.FileVO;
import com.example.demo.domain.MemberVO;
import com.example.demo.repository.BoardMapper;
import com.example.demo.service.BoardService;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
//@RequestMapping("/board/")
public class DetailController {

    @Autowired
    BoardService boardService;
    
    @RequestMapping("/list") //게시판 리스트 화면 호출  
    private String boardList(Model model, HttpSession session) throws Exception{
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("list", boardService.boardListService());
        return "boardList"; //생성할 jsp
    }
    
    @RequestMapping("/detail/{boardNum}") 
    private String boardDetail(@PathVariable int boardNum, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        model.addAttribute("detail", boardService.boardDetailService(boardNum));
		model.addAttribute("files", boardService.fileDetailService(boardNum, request, null));
        boardService.updateViewCntService(boardNum);
        return "detail";
    }
    
    @RequestMapping("/insert") //게시글 작성폼 호출  
    private String boardInsertForm(){
        return "insert";
    }

    @RequestMapping("/insertProc")
    private String boardInsertProc(BoardVO board, FileVO file, HttpServletRequest request, @RequestPart MultipartFile realfile) throws Exception{
    	if(file.isEmpty()){	//업로드할 파일이 없을 시
        	boardService.boardInsertService(board, request);	//게시글 insert
        }else{
	        boardService.boardInsertService(board, request);	//게시글 insert    	    
            boardService.fileInsertService(board, file, realfile); //파일 insert
        }
        return "redirect:/list";  
    }
    
    @RequestMapping("/fileDown/{boardNum}")
    private void fileDown(@PathVariable int boardNum, HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("UTF-8");
        boardService.fileDetailService(boardNum, request, response);
    }
    
    @RequestMapping("/update/{boardNum}") //게시글 수정폼 호출  
    private String boardUpdate(@PathVariable int boardNum, Model model) throws Exception{
        model.addAttribute("detail", boardService.boardDetailService(boardNum));        
        return "update";
    }
    
    @RequestMapping("/updateProc")
    private String boardUpdateProc(/*@ModelAttribute*/ BoardVO board, HttpServletRequest request) throws Exception{
        boardService.boardUpdateService(board, request);
        return "redirect:/detail/"+request.getParameter("boardNum");
    }
 
    @RequestMapping("/delete/{boardNum}")
    private String boardDelete(@PathVariable int boardNum) throws Exception{
        boardService.boardDeleteService(boardNum);
        return "redirect:/list";
    }
}
