package com.example.demo.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.FileVO;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/notice/")
public class ListController {

    @Autowired
    BoardService service;
    
    @RequestMapping("list") //게시판 리스트 화면 호출  
    private String boardList(Model model, HttpSession session) throws Exception{
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("list", service.boardListService());
        return "boardList"; //생성할 jsp
    }
    
    @RequestMapping("insert") //게시글 작성폼 호출  
    private String boardInsertForm(){
        return "insert";
    }

    @RequestMapping("insertProc")
    private String boardInsertProc(BoardVO board, FileVO file, HttpServletRequest request, @RequestPart MultipartFile realfile) throws Exception{
    	if(file.isEmpty()){	//업로드할 파일이 없을 시
        	service.boardInsertService(board, request);	//게시글 insert
        }else{
	        service.boardInsertService(board, request);	//게시글 insert    	    
            service.fileInsertService(board, file, realfile); //파일 insert
        }
        return "redirect:/list";  
    }
    
    @RequestMapping("fileDown/{boardNum}")
    private void fileDown(@PathVariable int boardNum, HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("UTF-8");
        service.fileDetailService(boardNum, request, response);
    }
    
    @RequestMapping("update/{boardNum}") //게시글 수정폼 호출  
    private String boardUpdate(@PathVariable int boardNum, Model model) throws Exception{
        model.addAttribute("detail", service.boardDetailService(boardNum));        
        return "update";
    }
    
    @RequestMapping("updateProc")
    private String boardUpdateProc(/*@ModelAttribute*/ BoardVO board, HttpServletRequest request) throws Exception{
        service.boardUpdateService(board, request);
        return "redirect:/detail/"+request.getParameter("boardNum");
    }
 
    @RequestMapping("delete/{boardNum}")
    private String boardDelete(@PathVariable int boardNum) throws Exception{
        service.boardDeleteService(boardNum);
        return "redirect:/list";
    }
}
