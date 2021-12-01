package com.example.vo;

public class AlarmVo {

	private int no;
	private String alarmType;
	private int alarmRead;
	private String alarmContent;
	private String alarmUrl;
	private int memNo;

	public AlarmVo() {
		super();
	}
	
	public AlarmVo(String alarmType, String alarmContent, String alarmUrl, int memNo) {
		super();
		this.alarmType = alarmType;
		this.alarmContent = alarmContent;
		this.alarmUrl = alarmUrl;
		this.memNo = memNo;
	}
	
	public AlarmVo(int no, String alarmType, int alarmRead, String alarmContent, String alarmUrl, int memNo) {
		super();
		this.no = no;
		this.alarmType = alarmType;
		this.alarmRead = alarmRead;
		this.alarmContent = alarmContent;
		this.alarmUrl = alarmUrl;
		this.memNo = memNo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public int getAlarmRead() {
		return alarmRead;
	}

	public void setAlarmRead(int alarmRead) {
		this.alarmRead = alarmRead;
	}

	public String getAlarmContent() {
		return alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}

	public String getAlarmUrl() {
		return alarmUrl;
	}

	public void setAlarmUrl(String alarmUrl) {
		this.alarmUrl = alarmUrl;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

}
