package com.example.vo;

public class NoteVo {
	private int noteNo;
	private String noteCon;
	private int noteGetmbNo;
	private int noteSendmbNo;
	private int noteGetmbDelState;
	private int noteGetmbSaveState;
	private int noteGetmbReadState;
	private int noteSendmbDelState;
	private int noteSendmbSaveState;
	private String noteDateTime;
	int noteIdentifySendGetCode;
	/**
	 * 
	 */
	public NoteVo() {
		super();
	}
	/**
	 * @param noteNo
	 * @param noteCon
	 * @param noteGetmbNo
	 * @param noteSendmbNo
	 * @param noteGetmbDelState
	 * @param noteGetmbSaveState
	 * @param noteGetmbReadState
	 * @param noteSendmbDelState
	 * @param noteSendmbSaveState
	 * @param noteDateTime
	 * @param noteIdentifySendGetCode
	 */
	public NoteVo(int noteNo, String noteCon, int noteGetmbNo, int noteSendmbNo, int noteGetmbDelState,
			int noteGetmbSaveState, int noteGetmbReadState, int noteSendmbDelState, int noteSendmbSaveState,
			String noteDateTime, int noteIdentifySendGetCode) {
		super();
		this.noteNo = noteNo;
		this.noteCon = noteCon;
		this.noteGetmbNo = noteGetmbNo;
		this.noteSendmbNo = noteSendmbNo;
		this.noteGetmbDelState = noteGetmbDelState;
		this.noteGetmbSaveState = noteGetmbSaveState;
		this.noteGetmbReadState = noteGetmbReadState;
		this.noteSendmbDelState = noteSendmbDelState;
		this.noteSendmbSaveState = noteSendmbSaveState;
		this.noteDateTime = noteDateTime;
		this.noteIdentifySendGetCode = noteIdentifySendGetCode;
	}
	public int getNoteNo() {
		return noteNo;
	}
	public void setNoteNo(int noteNo) {
		this.noteNo = noteNo;
	}
	public String getNoteCon() {
		return noteCon;
	}
	public void setNoteCon(String noteCon) {
		this.noteCon = noteCon;
	}
	public int getNoteGetmbNo() {
		return noteGetmbNo;
	}
	public void setNoteGetmbNo(int noteGetmbNo) {
		this.noteGetmbNo = noteGetmbNo;
	}
	public int getNoteSendmbNo() {
		return noteSendmbNo;
	}
	public void setNoteSendmbNo(int noteSendmbNo) {
		this.noteSendmbNo = noteSendmbNo;
	}
	public int getNoteGetmbDelState() {
		return noteGetmbDelState;
	}
	public void setNoteGetmbDelState(int noteGetmbDelState) {
		this.noteGetmbDelState = noteGetmbDelState;
	}
	public int getNoteGetmbSaveState() {
		return noteGetmbSaveState;
	}
	public void setNoteGetmbSaveState(int noteGetmbSaveState) {
		this.noteGetmbSaveState = noteGetmbSaveState;
	}
	public int getNoteGetmbReadState() {
		return noteGetmbReadState;
	}
	public void setNoteGetmbReadState(int noteGetmbReadState) {
		this.noteGetmbReadState = noteGetmbReadState;
	}
	public int getNoteSendmbDelState() {
		return noteSendmbDelState;
	}
	public void setNoteSendmbDelState(int noteSendmbDelState) {
		this.noteSendmbDelState = noteSendmbDelState;
	}
	public int getNoteSendmbSaveState() {
		return noteSendmbSaveState;
	}
	public void setNoteSendmbSaveState(int noteSendmbSaveState) {
		this.noteSendmbSaveState = noteSendmbSaveState;
	}
	public String getNoteDateTime() {
		return noteDateTime;
	}
	public void setNoteDateTime(String noteDateTime) {
		this.noteDateTime = noteDateTime;
	}
	public int getNoteIdentifySendGetCode() {
		return noteIdentifySendGetCode;
	}
	public void setNoteIdentifySendGetCode(int noteIdentifySendGetCode) {
		this.noteIdentifySendGetCode = noteIdentifySendGetCode;
	}
	
	
}
