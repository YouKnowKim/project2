package com.example.dao.grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.GradeUpVo;
import domain.MemberGradeUpVo;
import util.DBConn;

public class GradeUpDao {

	private static GradeUpDao gradeUpDao;


	public static GradeUpDao getInstance() {
		if (gradeUpDao == null) {
			gradeUpDao = new GradeUpDao();
		}
		return gradeUpDao;
	}

	// 1.등업 신청 정보를 등록한다.
	public void insertGradeUp(GradeUpVo gradeUp) throws Exception {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO gradeUp (mb_no, gu_aft_grade,gu_bef_grade,gu_datetime, gu_state " );
			sql.append("VALUES (?, ?, ?, now(), 1)");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, gradeUp.getMemNo());
			pstmt.setInt(2, gradeUp.getAfterGrade());
			pstmt.setInt(3, gradeUp.getBeforeGrade());
			

			pstmt.executeUpdate();
			pstmt.close();

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e2) {
				throw e2;
			}
		}
	}

	// 2.대기중인 등업신청목록을 조회한다.
	public List<MemberGradeUpVo> selectGradeUpList(int memNo) throws Exception {
		List<MemberGradeUpVo> MemberGradeUpList = new ArrayList<MemberGradeUpVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT m.mb_no, m.mb_id,m.mb_nick, g.gu_aft_grade, g.gu_bef_grade, m.mb_visit, m.mb_board, m.mb_comment ");
			sql.append(
					"date_format(m.mb_datetime,'%Y-%m-%d' ) as mb_datetime, date_format(g.gu_datetime,'%Y-%m-%d') as gu_datetime ");
			sql.append("FROM member as m left join gradeup as g on m.mb_no = g.mb_no ");
			if (memNo == 0) { //관리자일 때
				sql.append("WHERE gu_aft_grade != mb_grade "); 
			} else {
				sql.append("WHERE m.mb_no = ? ");
			}

			pstmt = conn.prepareStatement(sql.toString());

			if (memNo != 0) {
				pstmt.setInt(1, memNo);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberGradeUpVo MemberGradeUp = new MemberGradeUpVo();
				MemberGradeUp.setMemberNo(rs.getInt(1));
				MemberGradeUp.setId(rs.getString(2));
				MemberGradeUp.setNick(rs.getString(3));
				MemberGradeUp.setAfterGrade(rs.getInt(4));
				MemberGradeUp.setBeforeGrade(rs.getInt(5));
				MemberGradeUp.setMemberGradeVisit(rs.getInt(6));
				MemberGradeUp.setMemberGradeBoard(rs.getInt(7));
				MemberGradeUp.setMemberdate(rs.getString(8));
				MemberGradeUp.setGradeUpdate(rs.getString(9));

				MemberGradeUpList.add(MemberGradeUp);
			}

			return MemberGradeUpList;

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

	// 3.등업신청 목록을 일괄 승인한다. - 일괄 수정 (관리자)
	public void approvereviseGradeUp(ArrayList<GradeUpVo> gradeUpList) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean isSuccess = false;

		try {
			conn = DBConn.getConnection();

			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE gradeUp ");
			sql.append("SET gu_aft_grade = ? , gu_state = 2");
			sql.append("WHERE mb_no = ? and gu_state = 1");
			pstmt = conn.prepareStatement(sql.toString());

			for (GradeUpVo GradeUp : gradeUpList) {
				pstmt.setInt(1, GradeUp.getAfterGrade());
			    pstmt.setInt(2, GradeUp.getNo());
				
			
			    pstmt.addBatch();
				pstmt.clearParameters();
			}
			pstmt.executeBatch();

			conn.commit();
			System.out.println("커밋");
			isSuccess = true;

		} catch (

		Exception e) {
			e.printStackTrace();
			try {
				if (!isSuccess) {
					conn.rollback();
					System.out.println("롤백");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

}// 3.등업신청 목록을 일괄 반려한다. - 일괄 수정 (관리자)
		public void rejectreviseGradeUp(ArrayList<GradeUpVo> gradeUpList) throws Exception {

			Connection conn = null;
			PreparedStatement pstmt = null;
			boolean isSuccess = false;

			try {
				conn = DBConn.getConnection();

				conn.setAutoCommit(false);

				StringBuffer sql = new StringBuffer();
				sql.append("UPDATE gradeUp ");
				sql.append("SET gu_aft_grade = ? , gu_state = 3");
				sql.append("WHERE mb_no = ? and gu_state = 1");
				pstmt = conn.prepareStatement(sql.toString());

				for (GradeUpVo GradeUp : gradeUpList) {
					pstmt.setInt(1, GradeUp.getAfterGrade());
					pstmt.setInt(2, GradeUp.getNo());
					
				
				    pstmt.addBatch();
					pstmt.clearParameters();
				}
				pstmt.executeBatch();

				conn.commit();
				System.out.println("커밋");
				isSuccess = true;

			} catch (

			Exception e) {
				e.printStackTrace();
				try {
					if (!isSuccess) {
						conn.rollback();
						System.out.println("롤백");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
	}

	// 5.등업신청 목록을 취소한다. - 등업 취소 ( 사용자 )
	public void cancelreviseGradeUp(GradeUpVo gradeUp) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE gradeUp " );
			sql.append("SET gu_state = 4 ");
			sql.append("WHERE mb_no = ? " );

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, gradeUp.getMemNo());

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
