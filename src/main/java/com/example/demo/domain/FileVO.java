package com.example.demo.domain;

public class FileVO {

	private int fileNum; 			// 파일번호
	private int noticeNum; 			// 게시글 번호
	private String filePath;		// 파일의 저장 경로
	
	public FileVO() {
		super();
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

	public int getBoardNum() {
		return noticeNum;
	}

	public void setBoardNum(int boardNum) {
		this.noticeNum = boardNum;
	}

	public String getFileUrl() {
		return filePath;
	}

	public void setFileUrl(String fileUrl) {
		this.filePath = fileUrl;
	}

	public boolean isEmpty() {
		return false;
	}
}