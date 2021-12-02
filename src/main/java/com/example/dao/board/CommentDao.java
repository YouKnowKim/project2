package com.example.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.CommentVo;
import util.DBConn;


public class CommentDao {

	private static CommentDao commentDao;

	private CommentDao() {

	}

	public static CommentDao getInstance() {
		if (commentDao == null) {
			commentDao = new CommentDao();
		}
		return commentDao;
	}

	public void deleteComment(int no) throws Exception {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM comment      ");		
			sql.append("WHERE co_no = ?");			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				throw e2;
			}
		}

	
	}
	
	
	public void updateComment(CommentVo comment) throws Exception {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE comment      ");
			sql.append("SET co_content = ?     ");
			sql.append("WHERE co_no = ?");			
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, comment.getContent());
			pstmt.setInt(2, comment.getNo());
			
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				throw e2;
			}
		}	
	}
	
	public List<CommentVo> selectCommentList(int no) throws Exception {
		List<CommentVo> commentList = new ArrayList<CommentVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT comment.co_no, post_no, co_mb_no, co_content,                    ");
			sql.append("DATE_FORMAT(co_datetime, '%Y/%m/%d') as co_datetime, mb_name   ");
			sql.append("FROM comment LEFT JOIN member ON comment.co_mb_no = member.mb_no   ");
			sql.append("WHERE post_no = ?  ");
			sql.append("ORDER BY co_datetime DESC ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentVo comment = new CommentVo();
				comment.setNo(rs.getInt(1));
				comment.setBoardNo(rs.getInt(2));
				comment.setUserId(rs.getInt(3));
				comment.setContent(rs.getString(4));
				comment.setWriteday(rs.getString(5));

				comment.setUserName(rs.getString(6));
				commentList.add(comment);
			}

			return commentList;

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

	public void insertComment(CommentVo comment) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO comment (post_no, co_mb_no, co_content, co_datetime) ");
			sql.append("VALUES (?, ?, ?, now()) ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, comment.getBoardNo());
			pstmt.setInt(2, comment.getUserId());
			pstmt.setString(3, comment.getContent());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				throw e2;
			}
		}

	}

}
