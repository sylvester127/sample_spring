package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.FileVO;
import com.example.demo.domain.UserVO;

import java.util.List;

@Repository
public interface BoardMapper {  
    //게시글 개수  
    public int boardCount() throws Exception;
    
    //게시글 목록  
    public List<BoardVO> boardList() throws Exception;
    
    //게시글 상세
    public BoardVO boardDetail(int boardNum) throws Exception;
    
    //게시글 작성  
    public int boardInsert(BoardVO board) throws Exception;
    
    //게시글 수정  
    public int boardUpdate(BoardVO board) throws Exception;
    
    //게시글 조회수 카운트
	public int updateReadCnt(int boardNum);
    
    //게시글 삭제  
    public int boardDelete(int boardNum) throws Exception; 
    
    //파일 업로드
    public int fileInsert(FileVO file) throws Exception;

    //파일 상세
    public FileVO fileDetail(int boardNum) throws Exception;
}
