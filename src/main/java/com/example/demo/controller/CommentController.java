package com.example.demo.controller;

import java.util.List;

import javax.annotation.Resource;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.example.demo.domain.CommentVO;
import com.example.demo.service.CommentService;
 
@Controller
@RequestMapping("/comment")
public class CommentController {
 
    @Resource(name="com.example.demo.service.CommentService")
    CommentService commentService;

    @RequestMapping("/list") //댓글 리스트
    @ResponseBody
    private List<CommentVO> CommentListService(Model model) throws Exception{
        return commentService.commentListService();
    }
    
    @RequestMapping("/insert") //댓글 작성 
    @ResponseBody
    private int CommentInsertService(CommentVO comment, @RequestParam int boardNum, @RequestParam String content) throws Exception{
        System.out.println(comment.getBoardNum());
        comment.setBoardNum(boardNum);
        comment.setContent(content);
        //로그인 기능을 구현했거나 따로 댓글 작성자를 입력받는 폼이 있다면 입력 받아온 값으로 사용하면 됩니다. 저는 따로 폼을 구현하지 않았기때문에 임시로 "test"라는 값을 입력해놨습니다.
        comment.setWriter("test");
//        comment.setWriter("${detail.writer}");
        
        return commentService.commentInsertService(comment);
    }
    
    @RequestMapping("/update") //댓글 수정  
    @ResponseBody
    private int CommentUpdateProcService(@RequestParam int commentNum, @RequestParam String content) throws Exception{
        CommentVO comment = new CommentVO();
        comment.setCommentNum(commentNum);
        comment.setContent(content);
        return commentService.commentUpdateService(comment);
    }
    
    @RequestMapping("/delete/{commentNum}") //댓글 삭제  
    @ResponseBody
    private int commentDeleteService(@PathVariable int commentNum) throws Exception{
    	return commentService.commentDeleteService(commentNum);
    }
}
