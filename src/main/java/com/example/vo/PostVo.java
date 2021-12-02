package com.example.vo;

import java.util.ArrayList;

public class PostVo {
	private int no;
	private int boardNo;
	private String subject;
	private String content;
	private String createDate;
	private int writerNo;
	private String writerName;
	private String tag;
	private int views;
	private int blind;
	private ArrayList<AttachVo> attachList = new ArrayList<AttachVo>();
	private ReviewVo review;
			


	public PostVo() {
		super();
	}
	
	
	public PostVo(int boardNo, String subject, String content, int writerNo, String tag) {
		super();
		this.boardNo = boardNo;
		this.subject = subject;
		this.content = content;
		this.writerNo = writerNo;
		this.tag = tag;
	}


	public PostVo(int no, int writerNo, String writerName, String subject, String createDate, int views) {
		super();
		this.no = no;
		this.subject = subject;
		this.createDate = createDate;
		this.writerNo = writerNo;
		this.writerName = writerName;
		this.views = views;
	}

	public PostVo(int no, int boardNo, String subject, String content, String createDate, int writerNo, String tag) {
		super();
		this.no = no;
		this.boardNo = boardNo;
		this.subject = subject;
		this.content = content;
		this.createDate = createDate;
		this.writerNo = writerNo;
		this.tag = tag;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getWriterNo() {
		return writerNo;
	}

	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getBlind() {
		return blind;
	}

	public void setBlind(int blind) {
		this.blind = blind;
	}


	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	public ArrayList<AttachVo> getAttachList() {
		return attachList;
	}

	public void setAttachList(ArrayList<AttachVo> attachList) {
		this.attachList = attachList;
	}
	
	public void addAttach(AttachVo file) {
		this.attachList.add(file);
	}

	public ReviewVo getReview() {
		return review;
	}

	public void setReview(ReviewVo review) {
		this.review = review;
	}
	

}
