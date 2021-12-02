package com.example.vo;

public class GradeUpVo {

	private int no;
	private int memNo;
	private int afterGrade;
	private int beforeGrade;
	private int memberGrade;
	private String datetime;
	private String gradeUpState;
	private int memberVisit;
	private int memberBoard;
	private int memberComment;
	
	
	
	public GradeUpVo(int no, int afterGrade, int beforeGrade) {
		super();
	}


	public GradeUpVo(int no, int memNo, int afterGrade, int beforeGrade, int memberGrade, String datetime,
			String gradeUpState, int memberVisit,int memberBoard,int memberComment) {
		super();
		
		this.no = no;
		this.memNo = memNo;
		this.afterGrade = afterGrade;
		this.beforeGrade = beforeGrade;
		this.memberGrade = memberGrade;
		this.datetime = datetime;
		this.gradeUpState = gradeUpState;
		this.memberVisit = memberVisit;
		this.memberBoard = memberBoard;
		this.memberComment = memberComment;
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
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
	public int getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(int memberGrade) {
		this.memberGrade = memberGrade;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getGradeUpState() {
		return gradeUpState;
	}
	public void setGradeUpState(String gradeUpState) {
		this.gradeUpState = gradeUpState;
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