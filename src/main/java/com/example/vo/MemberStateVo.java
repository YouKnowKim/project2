package com.example.vo;



public class MemberStateVo {
	
	
	private int stateNo;
	private int memNo;
	private int[] memNos;
	private String reason;
	private String withdrawDate;
	private String nick;
	private int state;
	
	
	
	
	public String getNick() {
		return nick;
	}


	public MemberStateVo(String nick, int state, int stateNo, int memNo, String reason, String withdrawDate) {
		super();
		this.stateNo = stateNo;
		this.memNo = memNo;
		this.reason = reason;
		this.withdrawDate = withdrawDate;
		this.nick = nick;
		this.state = state;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public MemberStateVo(int stateNo, int memNo, int[] memNos, String reason, String withdrawDate, String nick) {
		super();
		this.stateNo = stateNo;
		this.memNo = memNo;
		this.memNos = memNos;
		this.reason = reason;
		this.withdrawDate = withdrawDate;
		this.nick = nick;
	}


	public MemberStateVo(int memNo, String reason) {
		super();
		this.memNo = memNo;
		this.reason = reason;
	}


	public int[] getMemNos() {
		return memNos;
	}


	public void setMemNos(int[] memNos) {
		this.memNos = memNos;
	}


	public MemberStateVo(int stateNo, int memNo, String reason, String withdrawDate) {
		super();
		this.stateNo = stateNo;
		this.memNo = memNo;
		this.reason = reason;
		this.withdrawDate = withdrawDate;
	}


	public MemberStateVo(int[] memNos, String reason) {
		super();
		this.memNos = memNos;
		this.reason = reason;
	}


	public MemberStateVo(int stateNo, int memNo, int[] memNos, String reason, String withdrawDate) {
		super();
		this.stateNo = stateNo;
		this.memNo = memNo;
		this.memNos = memNos;
		this.reason = reason;
		this.withdrawDate = withdrawDate;
	}

	
	public MemberStateVo() {
		super();
	}


	public int getStateNo() {
		return stateNo;
	}

	public void setStateNo(int stateNo) {
		this.stateNo = stateNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(String withdrawDate) {
		this.withdrawDate = withdrawDate;
	}
	
}
