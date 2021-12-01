package com.example.dao.grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.MemberGradeVo;
import util.DBConn;

public class MemberGradeDao {

	private static MemberGradeDao memberGradeDao;

	private MemberGradeDao() {

	}

	public static MemberGradeDao getInstance() {
		if (memberGradeDao == null) {
			memberGradeDao = new MemberGradeDao();
		}
		return memberGradeDao;
	}

	
	//1.멤버 등급을 조회한다.
	public List<MemberGradeVo> selectMemberGradeList() throws Exception {
		List<MemberGradeVo> MemberGradeList = new ArrayList<MemberGradeVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT * FROM memberGrade ");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberGradeVo MemberGrade = new MemberGradeVo();
				MemberGrade.setMemberGrade(rs.getInt(1));
				MemberGrade.setMemberGradeName(rs.getString(2));
				MemberGrade.setMemberGradeType(rs.getBoolean(3));
				MemberGrade.setMemberGradeBoard(rs.getInt(4));
				MemberGrade.setMemberGradeComment(rs.getInt(5));
				MemberGrade.setMemberGradeVisit(rs.getInt(6));
				MemberGrade.setMemberGradeUse(rs.getBoolean(7));
				MemberGradeList.add(MemberGrade);
			}

			return MemberGradeList;

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

	// 2. 등업가능한 등급 목록 조회
	public List<MemberGradeVo> selectPossibleMemberGrade(int memNo) throws Exception {
		List<MemberGradeVo> PossibleMemberGradeList = new ArrayList<MemberGradeVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT mg_grade,  mg_name");
			sql.append("FROM  membergrade");
			sql.append("WHERE mg_grade BETWEEN ( SELECT  (");
			sql.append("CASE WHEN (mb_visit > 99999 AND mb_board > 99999 AND mb_comment > 100 )THEN 5 ");
			sql.append("WHEN (mb_visit > 100 AND mb_board > 100 AND mb_comment > 100 )THEN 4 ");
			sql.append("WHEN (mb_visit > 50 AND mb_board > 50 AND mb_comment > 50 )THEN 3 ");
			sql.append("WHEN (mb_visit < 50 AND mb_board < 50 AND mb_comment < 50 )THEN 2 ");
			sql.append("END as mg_grade FROM member Where mb_no = ? ) AND 4; ");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberGradeVo MemberGrade = new MemberGradeVo();
				MemberGrade.setMemberNo(rs.getInt(1));
			}

			return PossibleMemberGradeList;

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
    //3.멤버 등급을 수정한다.
	public List<MemberGradeVo> reviseMemberGrade(MemberGradeVo MemberGrade) throws Exception {
		List<MemberGradeVo> MemberGradeList = new ArrayList<MemberGradeVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE MemberGrade ");
			sql.append("SET mg_name = ?, mg_type = ?, mg_board = ?, mg_com = ?, mg_visit = ?, mg_use = ? ");
			sql.append("WHERE mg_grade = ?");
			

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberGradeVo memberGrade = new MemberGradeVo();
				MemberGrade.setMemberGradeName(rs.getString(1));
				MemberGrade.setMemberGradeType(rs.getBoolean(2));
				MemberGrade.setMemberGradeBoard(rs.getInt(3));
				MemberGrade.setMemberGradeComment(rs.getInt(4));
				MemberGrade.setMemberGradeVisit(rs.getInt(5));
				MemberGrade.setMemberGradeUse(rs.getBoolean(6));
				MemberGradeList.add(MemberGrade);
	
			}
			return MemberGradeList;

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
