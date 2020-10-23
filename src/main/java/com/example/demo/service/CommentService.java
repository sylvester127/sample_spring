package com.example.demo.service;

import java.util.List;

import javax.annotation.Resource;
 
import org.springframework.stereotype.Service;
 
import com.example.demo.domain.CommentVO;
import com.example.demo.repository.CommentMapper;

@Service("com.example.demo.service.CommentService")
public class CommentService {
    @Resource(name="com.example.demo.repository.CommentMapper")
    CommentMapper commentMapper;
    
    public List<CommentVO> commentListService() throws Exception{
        return commentMapper.commentList();
    }
    
    public int commentInsertService(CommentVO comment) throws Exception{
        return commentMapper.commentInsert(comment);
    }
    
    public int commentUpdateService(CommentVO comment) throws Exception{
        return commentMapper.commentUpdate(comment);
    }
    
    public int commentDeleteService(int commentNum) throws Exception{
        return commentMapper.commentDelete(commentNum);
    }
}