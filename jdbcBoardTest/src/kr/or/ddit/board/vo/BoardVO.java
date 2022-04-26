package kr.or.ddit.board.vo;

import java.sql.Date;

public class BoardVO {

	private int no;
	private String title;
	private String user;
	private String cont;
	private int num;
	private Date date;

	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", user=" + user + ", cont=" + cont + ", num=" + num + "]";
	}
	
	
}
