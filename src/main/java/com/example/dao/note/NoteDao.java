package com.example.dao.note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.NoteVo;
import util.DBConn;

public class NoteDao {

	private static NoteDao noteDao = new NoteDao();

	private NoteDao() {

	}

	public static NoteDao getInstance() {
		if (noteDao == null) {
			noteDao = new NoteDao();
		}
		return noteDao;
	}

	// mb_no�� userID ã��
	public String selectMbId(int mbNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userId = "";
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT mb_id FROM member ");
			sql.append("WHERE mb_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, mbNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				userId = rs.getString(1);
			}

			return userId;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// userID�� mb_no ã��
	public int selectMbno(String userId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int mbNo = 0;
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT mb_no FROM member ");
			sql.append("WHERE mb_id = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mbNo = rs.getInt(1);
			}

			return mbNo;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// userID�� mb_no ã�� (Ʈ����� ó�� , insert��)
	public int selectMbno(String userId, Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int mbNo = 0;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT mb_no FROM member ");
			sql.append("WHERE mb_id = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mbNo = rs.getInt(1);
			}

			return mbNo;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���� ���� ��� ��ȸ
	public ArrayList<NoteVo> selectReceiveNoteList(int getMbno, int startRow, int postSize) throws Exception {
		ArrayList<NoteVo> NoteList = new ArrayList<NoteVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT note.note_no, note.note_con, note_get_mb_no, note_send_mb_no, ");
			sql.append("note_getmb_del_state, note_getmb_save_state, note_getmb_read_state, ");
			sql.append("note_sendmb_del_state, note_sendmb_save_state, note_datetime, note_identify_send_get_state ");
			sql.append("FROM note INNER JOIN noteindex ON note.note_no = noteindex.note_no ");
			sql.append("WHERE note_get_mb_no = ? AND note_getmb_del_state = 0 AND note_getmb_save_state = 0 ");
			sql.append("ORDER BY note_datetime DESC ");
			sql.append("LIMIT ? OFFSET ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, getMbno);
			pstmt.setInt(2, postSize);
			pstmt.setInt(3, startRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int noteNo = rs.getInt(1);
				String noteCon = rs.getString(2);
				int noteGetmbNo = rs.getInt(3);
				int noteSendmbNo = rs.getInt(4);
				int noteGetmbDelState = rs.getInt(5);
				int noteGetmbSaveState = rs.getInt(6);
				int noteGetmbReadState = rs.getInt(7);
				int noteSendmbDelState = rs.getInt(8);
				int noteSendmbSaveState = rs.getInt(9);
				String noteDateTime = rs.getString(10);
				int noteIdentifySendGetState = rs.getInt(11);
				NoteList.add(new NoteVo(noteNo, noteCon, noteGetmbNo, noteSendmbNo, noteGetmbDelState,
						noteGetmbSaveState, noteGetmbReadState, noteSendmbDelState, noteSendmbSaveState, noteDateTime,
						noteIdentifySendGetState));
			}

			return NoteList;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// �߽� ���� ��� ��ȸ
	public ArrayList<NoteVo> selectSendNoteList(int sendMbno, int startRow, int postSize) throws Exception {
		ArrayList<NoteVo> NoteList = new ArrayList<NoteVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DISTINCT note.note_no, note.note_con, note_get_mb_no, note_send_mb_no, ");
			sql.append("note_getmb_del_state, note_getmb_save_state, note_getmb_read_state, ");
			sql.append("note_sendmb_del_state, note_sendmb_save_state, note_datetime, note_identify_send_get_state ");
			sql.append("FROM note INNER JOIN noteindex ON note.note_no = noteindex.note_no ");
			sql.append("WHERE note_send_mb_no = ? AND note_sendmb_del_state = 0 AND note_sendmb_save_state = 0 ");
			sql.append("ORDER BY note_datetime DESC ");
			sql.append("LIMIT ? OFFSET ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, sendMbno);
			pstmt.setInt(2, postSize);
			pstmt.setInt(3, startRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int noteNo = rs.getInt(1);
				String noteCon = rs.getString(2);
				int noteGetmbNo = rs.getInt(3);
				int noteSendmbNo = rs.getInt(4);
				int noteGetmbDelState = rs.getInt(5);
				int noteGetmbSaveState = rs.getInt(6);
				int noteGetmbReadState = rs.getInt(7);
				int noteSendmbDelState = rs.getInt(8);
				int noteSendmbSaveState = rs.getInt(9);
				String noteDateTime = rs.getString(10);
				int noteIdentifySendGetState = rs.getInt(11);
				NoteList.add(new NoteVo(noteNo, noteCon, noteGetmbNo, noteSendmbNo, noteGetmbDelState,
						noteGetmbSaveState, noteGetmbReadState, noteSendmbDelState, noteSendmbSaveState, noteDateTime,
						noteIdentifySendGetState));
			}

			return NoteList;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ������ ���� ��� ��ȸ?
	public ArrayList<NoteVo> selectSaveNoteList(int Mbno, int startRow, int postSize) throws Exception {
		ArrayList<NoteVo> NoteList = new ArrayList<NoteVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DISTINCT note.note_no, note.note_con, note_get_mb_no, note_send_mb_no, ");
			sql.append("note_getmb_del_state, note_getmb_save_state, note_getmb_read_state, ");
			sql.append("note_sendmb_del_state, note_sendmb_save_state, note_datetime, note_identify_send_get_state ");
			sql.append("FROM note INNER JOIN noteindex ON note.note_no = noteindex.note_no ");
			sql.append("WHERE (note_send_mb_no = ? AND note_sendmb_del_state = 0 AND note_sendmb_save_state = 1) OR ");
			sql.append("(note_get_mb_no = ? AND note_getmb_del_state = 0 AND note_getmb_save_state = 1) ");
			sql.append("ORDER BY note_datetime DESC ");
			sql.append("LIMIT ? OFFSET ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, Mbno);
			pstmt.setInt(2, Mbno);
			pstmt.setInt(3, postSize);
			pstmt.setInt(4, startRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int noteNo = rs.getInt(1);
				String noteCon = rs.getString(2);
				int noteGetmbNo = rs.getInt(3);
				int noteSendmbNo = rs.getInt(4);
				int noteGetmbDelState = rs.getInt(5);
				int noteGetmbSaveState = rs.getInt(6);
				int noteGetmbReadState = rs.getInt(7);
				int noteSendmbDelState = rs.getInt(8);
				int noteSendmbSaveState = rs.getInt(9);
				String noteDateTime = rs.getString(10);
				int noteIdentifySendGetState = rs.getInt(11);
				NoteList.add(new NoteVo(noteNo, noteCon, noteGetmbNo, noteSendmbNo, noteGetmbDelState,
						noteGetmbSaveState, noteGetmbReadState, noteSendmbDelState, noteSendmbSaveState, noteDateTime,
						noteIdentifySendGetState));
			}

			return NoteList;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���� ���⸦ note ���̺� �����ϰ� note_no�� ��ȯ�Ѵ�.
	public int insertNote(String noteCon, Connection conn) throws Exception {
		int noteNo = 0;
		PreparedStatement notePstmt = null;
		Statement noteNoStmt = null;
		ResultSet rs = null;

		try {
			// note�� �����Է�
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO note (note_con) ");
			sql.append("VALUES (? ) ");
			notePstmt = conn.prepareStatement(sql.toString());

			notePstmt.setString(1, noteCon);

			notePstmt.executeUpdate();
			notePstmt.close();

			// DB�� ����� note ��ȣ�� ���Ѵ�.
			noteNoStmt = conn.createStatement();

			sql.delete(0, sql.length());
			sql.append("SELECT LAST_INSERT_ID() ");
			rs = noteNoStmt.executeQuery(sql.toString());

			if (rs.next()) {
				noteNo = rs.getInt(1);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (noteNoStmt != null)
					noteNoStmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return noteNo;

	}

	// ������ ���õ� noteindex�� �����Ѵ�.
	public void insertNoteIndex(int noteNo, int sendMbno, int getMbno, Connection conn) throws Exception {

		PreparedStatement pstmt = null;

		try {

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO noteindex (note_no, note_get_mb_no, note_send_mb_no, note_datetime) ");
			sql.append("VALUES (?, ?, ?, now() ) ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, noteNo);
			pstmt.setInt(2, getMbno);
			pstmt.setInt(3, sendMbno);

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}

	}

	// ���� ���� ��� ���� ���ϱ�
	public int selectReceiveNoteCount(int getMbno) throws Exception {
		int receiveCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(DISTINCT note_no) FROM noteindex ");
			sql.append("WHERE note_get_mb_no = ? AND note_getmb_del_state = 0 ");
			sql.append("AND note_getmb_save_state = 0 ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, getMbno);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				receiveCount = rs.getInt(1);
			}

			return receiveCount;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���� ���� ��� ���� ���ϱ�
	public int selectSendNoteCount(int sendMbno) throws Exception {
		int sendCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(DISTINCT note_no) FROM noteindex ");
			sql.append("WHERE note_send_mb_no = ? AND note_sendmb_del_state = 0 ");
			sql.append("AND note_sendmb_save_state = 0 ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, sendMbno);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				sendCount = rs.getInt(1);
			}

			return sendCount;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���� ���� ������ ��� ���� ���ϱ�
	public int selectSendSaveNoteCount(int Mbno) throws Exception {
		int sendCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(DISTINCT note_no) FROM noteindex ");
			sql.append("WHERE note_send_mb_no = ? AND note_sendmb_del_state = 0 ");
			sql.append("AND note_sendmb_save_state = 1 ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, Mbno);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				sendCount = rs.getInt(1);
			}

			return sendCount;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���� ���� ������ ��� ���� ���ϱ�
	public int selectRetrieveSaveNoteCount(int Mbno) throws Exception {
		int getCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(DISTINCT note_no) FROM noteindex ");
			sql.append("WHERE note_get_mb_no = ? AND note_getmb_del_state = 0 ");
			sql.append("AND note_getmb_save_state = 1 ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, Mbno);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				getCount = rs.getInt(1);
			}

			return getCount;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���õ� ���� ���� ����ó��
	public void deleteReceiveNotes(int mbNo, int noteNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE noteindex SET note_getmb_del_state = 1 ");
			sql.append("WHERE note_get_mb_no = ? AND note_no = ? AND note_getmb_save_state = 0 ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, mbNo);
			pstmt.setInt(2, noteNo);

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���õ� �߽� ���� ����ó��
	public void deleteSendNotes(int mbNo, int noteNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE noteindex SET note_sendmb_del_state = 1 ");
			sql.append("WHERE note_send_mb_no = ? AND note_no = ? AND note_sendmb_save_state = 0 ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, mbNo);
			pstmt.setInt(2, noteNo);

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���õ� ������ ���� ���� ����
	public void deleteSaveReceiveNotes(int mbNo, int noteNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE noteindex SET note_getmb_del_state = 1 ");
			sql.append("WHERE note_get_mb_no = ? AND note_no = ? AND note_getmb_save_state = 1 ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, mbNo);
			pstmt.setInt(2, noteNo);
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���õ� ������ �߽� ���� ����
	public void deleteSaveSendNotes(int mbNo, int noteNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE noteindex SET note_sendmb_del_state = 1 ");
			sql.append("WHERE note_send_mb_no = ? AND note_no = ? AND note_sendmb_save_state = 1 ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, mbNo);
			pstmt.setInt(2, noteNo);
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���õ� �������� ���������� ����
	public void updateSaveRetrieveNotes(int mbNo, int noteNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE noteindex SET note_getmb_save_state = 1 ");
			sql.append("WHERE note_get_mb_no = ? AND note_no = ? AND note_getmb_save_state = 0 ");
			sql.append("AND note_getmb_del_state = 0 ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, mbNo);
			pstmt.setInt(2, noteNo);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���õ� �������� ���������� ����
	public void updateSaveSendNotes(int mbNo, int noteNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE noteindex SET note_sendmb_save_state = 1 ");
			sql.append("WHERE note_send_mb_no = ? AND note_no = ? AND note_sendmb_save_state = 0 ");
			sql.append("AND note_sendmb_del_state = 0 ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, mbNo);
			pstmt.setInt(2, noteNo);
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

}
