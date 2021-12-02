package com.example.vo;

public class CommentVo {
	private int no;
	private String content;
	private String writeday;
	private int userId;
	private String userName;
	private int boardNo;

	public CommentVo() {
		super();
	}

	public CommentVo(int userId, String content, int boardNo) {
		super();
		this.content = content;		
		this.userId = userId;
		this.boardNo = boardNo;
	}
	
	public CommentVo(int no, String content) {		
		super();
		this.no = no;
		this.content = content;
	}
	
	public CommentVo(int no, String content, String writeday, int userId, int boardNo, String userName) {
		super();
		this.no = no;
		this.content = content;
		this.writeday = writeday;
		this.userId = userId;
		this.boardNo = boardNo;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteday() {
		return writeday;
	}

	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "CommentVo [no=" + no + ", content=" + content + ", writeday=" + writeday + ", userId=" + userId
				+ ", boardNo=" + boardNo + "]";
	}

}
