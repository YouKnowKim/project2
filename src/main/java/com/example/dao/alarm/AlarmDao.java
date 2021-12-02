package com.example.dao.alarm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.vo.AlarmVo;

public class AlarmDao {

	private static AlarmDao alarmDao;

	private AlarmDao() {

	}

	public static AlarmDao getInstance() {
		if (alarmDao == null) {
			alarmDao = new AlarmDao();
		}
		return alarmDao;
	}

	// �˸� ������ ����Ѵ�
	public int insertAlarm(List<AlarmVo> alarms, Connection conn) throws Exception {
		int success = 0;
		PreparedStatement psmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO alarm (al_type, al_content, al_url, mb_no)");
			sql.append(" VALUES (?, ?, ?, ?)");

			psmt = conn.prepareStatement(sql.toString());
			for (AlarmVo alarm : alarms) {
				psmt.setString(1, alarm.getAlarmType());
				psmt.setString(2, alarm.getAlarmContent());
				psmt.setString(3, alarm.getAlarmUrl());
				psmt.setInt(4, alarm.getMemNo());
				psmt.addBatch();
				psmt.clearParameters(); // �Ķ���� �ʱ�ȭ
				success++;
			}
			psmt.executeBatch();
			psmt.clearParameters(); // Batch �ʱ�ȭ
			
			return success; //������Ʈ ���� ���� ����
			
		} catch (Exception e) {
			throw e;
		} finally {

			try {

				if (psmt != null) {
					psmt.close();
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ȸ���� �˸� ����� ��ȸ�Ѵ�.
	public List<AlarmVo> selectAlarmList(int memNo) throws Exception {
		List<AlarmVo> alarmList = new ArrayList<AlarmVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT al_no, al_type, al_content, al_url, al_read ");
			sql.append("FROM alarm    ");
			sql.append("WHERE mb_no = ?  ");
			sql.append("ORDER BY al_no DESC LIMIT 30");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AlarmVo alarm = new AlarmVo();
				alarm.setNo(rs.getInt(1));
				alarm.setAlarmType(rs.getString(2));
				alarm.setAlarmContent(rs.getString(3));
				alarm.setAlarmUrl(rs.getString(4));
				alarm.setAlarmRead(rs.getInt(5));
				alarmList.add(alarm);
			}

			return alarmList;

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

	// ȸ���� �̿��� ������ �˸��� ������ ��ȸ�Ѵ�.
	public int selectNoReadAlarmCount(int memNo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT count(*) ");
			sql.append("FROM alarm ");
			sql.append("WHERE al_read = 0 AND mb_no = ?  ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int rowCount = rs.getInt(1);
				return rowCount;
			} else {
				return 0;
			}
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

	// �˸� ���� ���·� �����Ѵ�.
	public void updateReadAlarmList(int memNo) throws Exception{
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE alarm SET al_read = 1 WHERE mb_no = ?");
			
			psmt = conn.prepareStatement(sql.toString());
			psmt.setInt(1, memNo);
			psmt.executeUpdate();
			
		} catch (Exception e) {

			throw e;

		} finally {

			try {

				if (conn != null) {
					conn.close();
				}
				if (psmt != null) {
					psmt.close();
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���ǿ� �ش��ϴ� �˸��� �����Ѵ�.
	public void delectAlarm(int no) throws Exception{
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM alarm WHERE al_no = ?");
			
			psmt = conn.prepareStatement(sql.toString());
			psmt.setInt(1, no);
			psmt.executeUpdate();
			
		} catch (Exception e) {

			throw e;

		} finally {

			try {

				if (conn != null) {
					conn.close();
				}
				if (psmt != null) {
					psmt.close();
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ���ǿ� �ش��ϴ� �˸��� ��ü �����Ѵ�.
	public void delectAllAlarm(int memNo) throws Exception{
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM alarm WHERE mb_no = ?");
			
			psmt = conn.prepareStatement(sql.toString());
			psmt.setInt(1, memNo);
			psmt.executeUpdate();
			
		} catch (Exception e) {

			throw e;

		} finally {

			try {

				if (conn != null) {
					conn.close();
				}
				if (psmt != null) {
					psmt.close();
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}
}
