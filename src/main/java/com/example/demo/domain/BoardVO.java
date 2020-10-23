package com.example.demo.domain;

import java.util.Date;

public class BoardVO {
 
    private int boardNum;
    private Date reg_date;
    private String writer;
    private String title;
    private String content;
    private String board_pass;
    private int read_cnt;
    private String B_REMV_FLAG;
    
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getBoard_pass() {
		return board_pass;
	}
	public void setBoard_pass(String board_pass) {
		this.board_pass = board_pass;
	}
	
	public int getRead_cnt() {
		return read_cnt;
	}
	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}
	
	public String getB_REMV_FLAG() {
		return B_REMV_FLAG;
	}
	public void setB_REMV_FLAG(String b_REMV_FLAG) {
		this.B_REMV_FLAG = b_REMV_FLAG;
	}
}