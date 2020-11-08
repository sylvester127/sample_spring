package com.example.demo.domain;

public class FileVO {

	private int fileNum; 			// 파일번호
	private int noticeNum; 			// 게시글 번호
	private String filePath;		// 파일의 저장 경로
	
	public FileVO() {
		super();
	}
	
	public FileVO(int noticeNum, String filePath) {
		super();
		this.noticeNum = noticeNum;
		this.filePath = filePath;
	}

	public FileVO(int fileNum, int noticeNum, String filePath) {
		super();
		this.fileNum = fileNum;
		this.noticeNum = noticeNum;
		this.filePath = filePath;
	}

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	public int getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}