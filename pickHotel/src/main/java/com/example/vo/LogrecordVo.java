package com.example.vo;

public class LogrecordVo {
	private int logNo;
	private String mbIp;
	private String mbId;
	private String mbLog;
	/**
	 * 
	 */
	public LogrecordVo() {
		super();
	}
	/**
	 * @param logNo
	 * @param mbIp
	 * @param mbId
	 * @param mbLog
	 */
	public LogrecordVo(int logNo, String mbIp, String mbId, String mbLog) {
		super();
		this.logNo = logNo;
		this.mbIp = mbIp;
		this.mbId = mbId;
		this.mbLog = mbLog;
	}
	public int getLogNo() {
		return logNo;
	}
	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}
	public String getMbIp() {
		return mbIp;
	}
	public void setMbIp(String mbIp) {
		this.mbIp = mbIp;
	}
	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	public String getMbLog() {
		return mbLog;
	}
	public void setMbLog(String mbLog) {
		this.mbLog = mbLog;
	}
	
	
}
