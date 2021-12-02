package com.example.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import domain.NoteVo;
import model.dao.note.NoteDao;
import util.DBConn;

public class NoteService {
	private static NoteService service;

	private NoteService() {

	}

	public static NoteService getInstance() {
		if (service == null) {
			service = new NoteService();
		}

		return service;
	}

	// ���� �ۼ�
	public void registerNote(String userId, String noteCon, String[] getMbIdArray) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);

			NoteDao notedao = NoteDao.getInstance();
			// �������� �����ϰ� ���� ��ȣ ���ϱ�
			int noteNo = notedao.insertNote(noteCon, conn);

			// ���� ��� ��ȣ ���ϱ�
			int sendMbno = notedao.selectMbno(userId, conn);

			// noteindex �����ϱ�
			for (String getMbId : getMbIdArray) {
				int getMbno = notedao.selectMbno(getMbId, conn);
				notedao.insertNoteIndex(noteNo, sendMbno, getMbno, conn);
			}

			isSuccess = true;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}
					conn.close();
				}
			} catch (Exception e2) {
				throw e2;
			}
		}

	}

	// ���� ���� ��� ��ȸ
	public ArrayList<NoteVo> retrieveReceiveNoteList(String userId, int startRow, int postSize) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int getMbno = notedao.selectMbno(userId);
		ArrayList<NoteVo> getNoteList = notedao.selectReceiveNoteList(getMbno, startRow, postSize);
		return getNoteList;
	}

	// ���� ���� ��� ��ȸ
	public ArrayList<NoteVo> retrieveSendNoteList(String userId, int startRow, int postSize) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int sendMbno = notedao.selectMbno(userId);
		ArrayList<NoteVo> sendNoteList = notedao.selectSendNoteList(sendMbno, startRow, postSize);
		return sendNoteList;
	}

	// ������ ��� ��ȸ
	public ArrayList<NoteVo> retrieveSaveNoteList(String userId, int startRow, int postSize) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int saveMbno = notedao.selectMbno(userId);
		ArrayList<NoteVo> saveNoteList = notedao.selectSaveNoteList(saveMbno, startRow, postSize);
		return saveNoteList;
	}

	// ���� ���� ���� ���ϱ�
	public int retrieveGetNoteCount(String userId) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int getMbno = notedao.selectMbno(userId);
		int receiveCount = notedao.selectReceiveNoteCount(getMbno);
		return receiveCount;
	}

	// ���� ���� ���� ���ϱ�
	public int retrieveSendNoteCount(String userId) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int getMbno = notedao.selectMbno(userId);
		int receiveCount = notedao.selectReceiveNoteCount(getMbno);
		return receiveCount;
	}

	// ������ ���� ���� ���ϱ�
	public int retrieveSaveNoteCount(String userId) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int Mbno = notedao.selectMbno(userId);
		int sendSaveNoteCount = notedao.selectSendSaveNoteCount(Mbno);
		int retrieveSaveNoteCount = notedao.selectRetrieveSaveNoteCount(Mbno);
		int saveNoteCount = sendSaveNoteCount + retrieveSaveNoteCount;
		return saveNoteCount;
	}

	// ���õ� �������� ����ó���ϱ�
	public void removeRetrieveNotes(String userId, int[] noteNos) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int getMbno = notedao.selectMbno(userId);
		System.out.println(getMbno);
		for (int noteNo : noteNos) {
			notedao.deleteReceiveNotes(getMbno, noteNo);
		}
	}

	// ���õ� �߽����� ����ó���ϱ�
	public void removeSendNotes(String userId, int[] noteNos) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int getMbno = notedao.selectMbno(userId);

		for (int noteNo : noteNos) {
			notedao.deleteSendNotes(getMbno, noteNo);
		}
	}

	// ���õ� ������ ���� ����ó���ϱ�
	public void removeSaveNotes(String userId, int[] noteNos) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int mbNo = notedao.selectMbno(userId);

		for (int noteNo : noteNos) {
			notedao.deleteSaveReceiveNotes(mbNo, noteNo);
			notedao.deleteSaveSendNotes(mbNo, noteNo);
		}
	}

	// ���õ� �������� ���������� �����ϱ�
	public void reviseSaveRetrieveNote(String userId, int[] noteNos) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int mbNo = notedao.selectMbno(userId);
		for (int noteNo : noteNos) {
			notedao.updateSaveRetrieveNotes(mbNo, noteNo);
		}
	}

	// ���õ� �������� ���������� �����ϱ�
	public void reviseSaveSendNote(String userId, int[] noteNos) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int mbNo = notedao.selectMbno(userId);
		System.out.println("�۵�");
		for (int noteNo : noteNos) {
			notedao.updateSaveSendNotes(mbNo, noteNo);
		}
	}
}
