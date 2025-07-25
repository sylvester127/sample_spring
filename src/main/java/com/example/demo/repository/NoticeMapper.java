package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.NoticeVO;
import com.example.demo.domain.FileVO;
import com.example.demo.domain.MemberVO;

import java.util.List;

@Repository
public interface NoticeMapper {  
	// 게시글 작성
	public int insertNotice(NoticeVO notice);
    
	// 게시글 목록
    public List<NoticeVO> getNoticeList();
    
    // 게시글 상세
    public NoticeVO getNotice(int noticeNum);
    
    //게시글 수정
    public int updateNotice(NoticeVO notice);
    
    //게시글 삭제
    public int deleteNotice(int noticeNum);
    
    //파일 업로드
    public int insertFile(FileVO file);
    
    //파일 상세
    public List<FileVO> getFileList(int noticeNum);
}