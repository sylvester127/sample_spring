package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.NoticeVO;
import com.example.demo.domain.FileVO;
import com.example.demo.domain.MemberVO;

import java.util.List;

@Repository
public interface NoticeMapper {  
    //게시글 개수  
    public int boardCount() throws Exception;
    
    //게시글 목록  
    public List<NoticeVO> boardList() throws Exception;
    
    //게시글 상세
    public NoticeVO boardDetail(int boardNum) throws Exception;
    
    //게시글 작성  
    public int boardInsert(NoticeVO board) throws Exception;
    
    //게시글 수정  
    public int boardUpdate(NoticeVO board) throws Exception;
    
    //게시글 조회수 카운트
	public int updateReadCnt(int boardNum);
    
    //게시글 삭제  
    public int boardDelete(int boardNum) throws Exception; 
    
    //파일 업로드
    public int fileInsert(FileVO file) throws Exception;

    //파일 상세
    public FileVO fileDetail(int boardNum) throws Exception;
}
