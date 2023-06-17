package com.board.domain;

import java.util.Date;

public class BoardVO {

	/*
	 * create table tbl_board( bno int not null auto_increment, title varchar(50)
	 * not null, content text not null, writer varchar(30) not null, regDate
	 * timestamp not null default now(), viewCnt int default 0, primary key(bno) );
	 * 
	 * tbl_board 테이블을 만들 때 사용한 쿼리
	 * VO를 만들 때 테이블과 똑같이 만들어줘야 하니까 확인하기 쉽도록, 
	 * 다른 사람이 이 VO를 보더라도 어느 테이블을 사용하는지 바로 알 수 있기 때문에 작성함
	 */
	
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private int viewCnt;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	
}
