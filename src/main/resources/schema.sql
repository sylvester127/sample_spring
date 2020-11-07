/**
  데이터 베이스 생성 스크립트 입력
 */
CREATE TABLE USER(
	UserId varchar(15),
	Password varchar(15) NOT NULL,
	Name varchar(10),
	Email varchar(30),
	Birthday varchar(30),
	Address varchar(100),
	Phone varchar(20),
	Sex varchar(4),
	U_REMV_FLAG char(1) DEFAULT 'N',
	PRIMARY KEY(UserId)
);

CREATE TABLE BOARD(
	BoardNum int IDENTITY,
	Reg_Date datetime,
	Writer varchar(10),
	Title varchar(80),
	Content varchar(3000),
	Board_Pass varchar(20),
	Read_Cnt int DEFAULT '0',
	B_REMV_FLAG char(1) DEFAULT 'N',
	PRIMARY KEY(BoardNum)
);

CREATE TABLE FILES(
	FileNum int IDENTITY,
	BoardNum int,
	FilePath varchar(500),
	PRIMARY KEY(FileNum)
);

CREATE TABLE COMMENT(
	CommentNum int IDENTITY,
	BoardNum int,
	Content varchar(200),
	Writer varchar(20),
	Reg_date datetime,
	PRIMARY KEY(CommentNum)
);