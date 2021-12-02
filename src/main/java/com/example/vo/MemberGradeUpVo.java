package com.example.vo;

public class MemberGradeUpVo {

	private int memberNo;
	private String id;
	private String nick;
	private int afterGrade;
	private int beforeGrade;
	private int memberGradeVisit; // 등업기준_방문일수
	private int memberGradeBoard; // 등업기준_게시글수
	private int memberGradeComment; // 등업기준_댓글수
	private String memberdate;
	private String gradeUpdate;
	private int memberVisit;
	private int memberBoard;
	private int memberComment;
	public MemberGradeUpVo(int memberNo, String id, String nick, int afterGrade, int beforeGrade, int memberGradeVisit,
			int memberGradeBoard, int memberGradeComment, String memberdate, String gradeUpdate, int memberVisit,
			int memberBoard, int memberComment) {
		super();
		this.memberNo = memberNo;
		this.id = id;
		this.nick = nick;
		this.afterGrade = afterGrade;
		this.beforeGrade = beforeGrade;
		this.memberGradeVisit = memberGradeVisit;
		this.memberGradeBoard = memberGradeBoard;
		this.memberGradeComment = memberGradeComment;
		this.memberdate = memberdate;
		this.gradeUpdate = gradeUpdate;
		this.memberVisit = memberVisit;
		this.memberBoard = memberBoard;
		this.memberComment = memberComment;
	}
	public MemberGradeUpVo() {
		super();
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getAfterGrade() {
		return afterGrade;
	}
	public void setAfterGrade(int afterGrade) {
		this.afterGrade = afterGrade;
	}
	public int getBeforeGrade() {
		return beforeGrade;
	}
	public void setBeforeGrade(int beforeGrade) {
		this.beforeGrade = beforeGrade;
	}
	public int getMemberGradeVisit() {
		return memberGradeVisit;
	}
	public void setMemberGradeVisit(int memberGradeVisit) {
		this.memberGradeVisit = memberGradeVisit;
	}
	public int getMemberGradeBoard() {
		return memberGradeBoard;
	}
	public void setMemberGradeBoard(int memberGradeBoard) {
		this.memberGradeBoard = memberGradeBoard;
	}
	public int getMemberGradeComment() {
		return memberGradeComment;
	}
	public void setMemberGradeComment(int memberGradeComment) {
		this.memberGradeComment = memberGradeComment;
	}
	public String getMemberdate() {
		return memberdate;
	}
	public void setMemberdate(String memberdate) {
		this.memberdate = memberdate;
	}
	public String getGradeUpdate() {
		return gradeUpdate;
	}
	public void setGradeUpdate(String gradeUpdate) {
		this.gradeUpdate = gradeUpdate;
	}
	public int getMemberVisit() {
		return memberVisit;
	}
	public void setMemberVisit(int memberVisit) {
		this.memberVisit = memberVisit;
	}
	public int getMemberBoard() {
		return memberBoard;
	}
	public void setMemberBoard(int memberBoard) {
		this.memberBoard = memberBoard;
	}
	public int getMemberComment() {
		return memberComment;
	}
	public void setMemberComment(int memberComment) {
		this.memberComment = memberComment;
	}

}


	
	
	

