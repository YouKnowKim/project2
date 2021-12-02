package com.example.dao.statistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.LogrecordVo;
import util.DBConn;

public class LogrecordDao {

	private static LogrecordDao LogrecordDao;

	private LogrecordDao() {

	}

	public static LogrecordDao getInstance() {
		if (LogrecordDao == null) {
			LogrecordDao = new LogrecordDao();
		}
		return LogrecordDao;
	}

	// �α� ��� ��ȸ
	public ArrayList<LogrecordVo> selectMemberLogRecord(int startRow, int postSize) throws Exception {
		ArrayList<LogrecordVo> records = new ArrayList<LogrecordVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT log_no, mb_ip, mb_id, mb_log ");
			sql.append("FROM logrecord ");
			sql.append("ORDER BY mb_log DESC ");
			sql.append("LIMIT ? OFFSET ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, postSize);
			pstmt.setInt(2, startRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt(1);
				String mbIp = rs.getString(2);
				String mbId = rs.getString(3);
				String mbLog = rs.getString(4);
				records.add(new LogrecordVo(no, mbIp, mbId, mbLog));
			}

			return records;

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

	// �� �α� ��� ��
	public int selectTotalLogrecordCount() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT count(*) ");
			sql.append("FROM logrecord ");

			rs = stmt.executeQuery(sql.toString());

			int no = 0;
			while (rs.next()) {
				no = rs.getInt(1);
			}

			return no;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}

	}
	
	// ���� �α� ��� ��
	public int selectTodayLogrecordCount() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT count(*) FROM logrecord ");
			sql.append("WHERE DATEDIFF(NOW(), mb_log) = 0 ");

			rs = stmt.executeQuery(sql.toString());

			int no = 0;
			while (rs.next()) {
				no = rs.getInt(1);
			}

			return no;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}

	}

	// �׷��� Ÿ�� �� �α� ��� Ƚ�� ��ȸ
	public ArrayList<Integer> selectWeeklyLogrecordCount() throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Integer> typeOfGraphLecordCounts = new ArrayList<Integer>();
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(log_no) FROM logrecord ");
			sql.append("WHERE DATEDIFF(NOW(), mb_log) <= 7 AND ");
			sql.append("DATEDIFF(NOW(), mb_log) > 0 ");
			sql.append("GROUP BY DATE(mb_log) ");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery(sql.toString());

			while (rs.next()) {
				typeOfGraphLecordCounts.add(rs.getInt(1));

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

		return typeOfGraphLecordCounts;

	}

	// �׷��� Ÿ�� �� �α� ��� Ƚ�� ��ȸ
	public ArrayList<Integer> selectMonthlyLogrecordCount() throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Integer> typeOfGraphLecordCounts = new ArrayList<Integer>();
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(log_no) FROM logrecord ");
			sql.append("WHERE DATEDIFF(NOW(), mb_log) <= 30 AND ");
			sql.append("DATEDIFF(NOW(), mb_log) > 0 ");
			sql.append("GROUP BY DATE(mb_log) ");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery(sql.toString());

			while (rs.next()) {
				typeOfGraphLecordCounts.add(rs.getInt(1));

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

		return typeOfGraphLecordCounts;

	}

	// 7�ϰ� �Խñ� ���� ���Ѵ�.
	public ArrayList<Integer> selectWeeklyPostCount() throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Integer> typeOfGraphPostCounts = new ArrayList<Integer>();
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(post_no) FROM post ");
			sql.append("WHERE DATEDIFF(NOW(), po_datetime) <= 7 AND ");
			sql.append("DATEDIFF(NOW(), po_datetime) > 0 ");
			sql.append("GROUP BY DATE(po_datetime) ");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery(sql.toString());

			while (rs.next()) {
				typeOfGraphPostCounts.add(rs.getInt(1));

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

		return typeOfGraphPostCounts;

	}

	// 30�ϰ� �Խñ� ���� ���Ѵ�.
	public ArrayList<Integer> selectMonthlyPostCount() throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Integer> typeOfGraphPostCounts = new ArrayList<Integer>();
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(post_no) FROM post ");
			sql.append("WHERE DATEDIFF(NOW(), po_datetime) <= 30 AND ");
			sql.append("DATEDIFF(NOW(), po_datetime) > 0 ");
			sql.append("GROUP BY DATE(po_datetime) ");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery(sql.toString());

			while (rs.next()) {
				typeOfGraphPostCounts.add(rs.getInt(1));

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

		return typeOfGraphPostCounts;

	}
	
	//�α� ���� �����ϱ�
	public void insertLogrecord(String userIp,String userId ) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO logrecord (mb_ip, mb_id, mb_log ) ");
			sql.append("VALUES ( ?, ? , now() ) ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, userIp);
			pstmt.setString(2, userId);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

}

















