package com.example.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import domain.AttachVo;
import domain.PostVo;
import domain.ReviewVo;
import util.DBConn;

public class PostDao {
	// singleton pattern
	private static PostDao postDao;

	private PostDao() {

	}

	public static PostDao getInstance() {
		if (postDao == null) {
			postDao = new PostDao();
		}
		return postDao;
	}

	// �Խñ� ������ ����ϴ�.
	public int insertpost(PostVo post, Connection conn) throws Exception {
		int no = 0;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO post (bo_no, po_mb_no, po_subject, po_content, po_tag, po_datetime, po_views, po_comment)  ");
			sql.append("VALUES (?,  ?,  ?,  ?, ? , NOW(), 0, 0 )");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, post.getBoardNo());
			pstmt.setInt(2, post.getWriterNo());
			pstmt.setString(3, post.getSubject());
			pstmt.setString(4, post.getContent());
			pstmt.setString(5, post.getTag());
			
			pstmt.executeUpdate();
			pstmt.close();

			// DB�� ����� �Խñ��� �Խñ� ��ȣ�� ���Ѵ�.
			stmt = conn.createStatement();

			sql.delete(0, sql.length());
			sql.append("SELECT LAST_INSERT_ID()");
			rs = stmt.executeQuery(sql.toString());

			if (rs.next()) {
				no = rs.getInt(1);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return no;
	}

	
	// �Խñ� �˻� ����� ��ȸ�ϴ�. - �ۼ���
	public ArrayList<PostVo> selectPostSearchList(HashMap<String,String> map, int startRow, int postSize) throws Exception {
		ArrayList<PostVo> posts = new ArrayList<PostVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = DBConn.getConnection();	
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT post_no, po_mb_no, po_subject,                                            ");
			sql.append("DATE_FORMAT(po_datetime, '%Y/%m/%d %H:%i:%s') as writedate, po_views,mb_name     ");
			sql.append("FROM post JOIN member ON post.po_mb_no=member.mb_no                                                              			");
			
			
			sql.append("WHERE bo_no = ?					");
			
			sql.append("ORDER BY writedate DESC                                               			 ");
			sql.append("LIMIT ? OFFSET ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, Integer.parseInt(map.get("boardNo")));
			pstmt.setInt(2, postSize);
			pstmt.setInt(3, startRow);
			
			rs = pstmt.executeQuery();			

			while (rs.next()) {
				int no = rs.getInt(1);
				int writerNo = rs.getInt(2);
				String title = rs.getString(3);
				String createDate = rs.getString(4);
				int views = rs.getInt(5);

				String writerName = rs.getString(6);
				posts.add(new PostVo(no, writerNo, writerName, title, createDate, views));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return posts;
	}

	// ��ȸ���� �����ϴ�.
	public void upHitcount(int no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE post                   ");
			sql.append("SET po_views = po_views + 1    ");
			sql.append("WHERE post_no = ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, no);

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

	// �Խñ� ��ȣ�� �ش��ϴ� �Խñ� �������� ��ȸ�ϴ�.
	public PostVo selectPost(int no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostVo post = new PostVo();
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT p.post_no, p.po_subject, p.po_content, p.po_mb_no, p.po_tag, p.po_views,                ");
			sql.append("date_format(p.po_datetime, '%Y/%m/%d %h:%i:%s') as writedate, p.bo_no ,									");
			sql.append("a.atta_realname, a.atta_sysname, a.atta_size, a.atta_no, m.mb_name,                        ");
			sql.append("roo_no, re_rate_loc, re_rate_clean, re_rate_comu, re_rate_chip, re_vsdate, re_push_pl, re_push_npl                        ");
			sql.append("FROM post as p LEFT JOIN attach as a ON p.post_no = a.post_no                             ");
			sql.append("INNER JOIN member AS m ON m.mb_no = p.po_mb_no                                            ");
			sql.append("LEFT JOIN review AS r ON p.post_no = r.post_no                                               ");
			sql.append("WHERE p.post_no = ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			boolean isFirst = true;
			while (rs.next()) {
				if (isFirst) {
					post.setNo(rs.getInt(1));
					post.setSubject(rs.getString(2));
					post.setContent(rs.getString(3));					
					post.setWriterNo(rs.getInt(4));
					post.setTag(rs.getString(5));
					post.setViews(rs.getInt(6));
					post.setCreateDate(rs.getString(7));
					post.setBoardNo(rs.getInt(8));
					post.setWriterName(rs.getString(13));
					isFirst = false;
				}
				if (rs.getString(9) != null) {
					AttachVo attach = new AttachVo();
					attach.setOriginalFileName(rs.getString(9));
					attach.setSystemFileName(rs.getString(10));
					attach.setFileSize(rs.getInt(11));
					attach.setNo(rs.getInt(12));
					post.addAttach(attach);
				}
				if (rs.getString(14) != null) {
					ReviewVo review = new ReviewVo();

					review.setNo(rs.getInt(1));
					review.setRoomNo(rs.getInt(14));
					review.setRate_loc(rs.getInt(15));
					review.setRate_clean(rs.getInt(16));
					review.setRate_comu(rs.getInt(17));
					review.setRate_chip(rs.getInt(18));
					review.setVisitDate(rs.getString(19));
					review.setRecommendPlace(rs.getString(20));
					review.setNotRecommendPerson(rs.getString(21));
					post.setReview(review);
				}
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
		return post;
	}

	//�Խñ� ������ �����ϴ�.
	public void updatePost(PostVo post, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE post                                 ");
			sql.append("SET bo_no = ?, po_subject = ?, po_content = ?,  po_tag = ?      ");
			sql.append("WHERE post_no = ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, post.getBoardNo());
			pstmt.setString(2, post.getSubject());
			pstmt.setString(3, post.getContent());
			pstmt.setString(4, post.getTag());
			pstmt.setInt(5, post.getNo());

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

	// �Խñ� ������ �����ϴ�.
	public void deletePost(int postNo, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM post                                 ");
			sql.append("WHERE post_no = ?  ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, postNo);
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
	
	//�� �Խñ� ���� ���Ѵ�.
	public int selectTotalPostCount(HashMap<String,String> map) throws Exception {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*)  	 ");
			sql.append("FROM post 	");
			sql.append("WHERE bo_no = ?					");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, Integer.parseInt(map.get("boardNo")));
			
			rs = pstmt.executeQuery();
			System.out.println("here");
			
			if(rs.next()) {
				count = rs.getInt(1);
			}			
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}		
		return count;		
	}

}
