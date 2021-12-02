package com.example.vo;

public class MemberGradeVo {

	private int memberNo; // 회원번호
	private int memberGrade; // 등급
	private String memberGradeName; // 등급명
	private boolean memberGradeType; // 등업 방식( 0 : 자동, 1 : 수동 )
	private int memberGradeBoard; // 등업기준_게시글수
	private int memberGradeComment; // 등업기준_댓글수
	private int memberGradeVisit; // 등업기준_방문일수
	private boolean memberGradeUse; // 등급사용여부( 0 : 미사용, 1 : 사용)
	private int memberVisit;
	private int memberBoard;
	private int memberComment;

	public MemberGradeVo(int memberNo, int memberGrade, String memberGradeName, boolean memberGradeType,
			int memberGradeBoard, int memberGradeComment, int memberGradeVisit, boolean memberGradeUse, int memberVisit,
			int memberBoard, int memberComment) {
		super();
		this.memberNo = memberNo;
		this.memberGrade = memberGrade;
		this.memberGradeName = memberGradeName;
		this.memberGradeType = memberGradeType;
		this.memberGradeBoard = memberGradeBoard;
		this.memberGradeComment = memberGradeComment;
		this.memberGradeVisit = memberGradeVisit;
		this.memberGradeUse = memberGradeUse;
		this.memberVisit = memberVisit;
		this.memberBoard = memberBoard;
		this.memberComment = memberComment;
	}
	public MemberGradeVo() {
		
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(int memberGrade) {
		this.memberGrade = memberGrade;
	}
	public String getMemberGradeName() {
		return memberGradeName;
	}
	public void setMemberGradeName(String memberGradeName) {
		this.memberGradeName = memberGradeName;
	}
	public boolean isMemberGradeType() {
		return memberGradeType;
	}
	public void setMemberGradeType(boolean memberGradeType) {
		this.memberGradeType = memberGradeType;
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
	public int getMemberGradeVisit() {
		return memberGradeVisit;
	}
	public void setMemberGradeVisit(int memberGradeVisit) {
		this.memberGradeVisit = memberGradeVisit;
	}
	public boolean isMemberGradeUse() {
		return memberGradeUse;
	}
	public void setMemberGradeUse(boolean memberGradeUse) {
		this.memberGradeUse = memberGradeUse;
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