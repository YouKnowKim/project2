package com.example.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import domain.ReviewVo;

public class ReviewDao {

	// sigleton pattern
	private static ReviewDao ReviewDao;

	private ReviewDao() {

	}

	public static ReviewDao getInstance() {
		if (ReviewDao == null) {
			ReviewDao = new ReviewDao();
		}
		return ReviewDao;
	}

	// ���並 ����ϴ�.
	public void insertReview(ReviewVo review, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			System.out.println("�����ϱ�???");
			sql.append(
					"INSERT INTO review (post_no, roo_no, re_rate_loc, re_rate_clean, re_rate_comu, re_rate_chip, re_vsdate, re_push_pl, re_push_npl )   ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)	");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, review.getNo());
			pstmt.setInt(2, review.getRoomNo());
			pstmt.setInt(3, review.getRate_loc());
			pstmt.setInt(4, review.getRate_clean());
			pstmt.setInt(5, review.getRate_comu());
			pstmt.setInt(6, review.getRate_chip());
			pstmt.setString(7, review.getVisitDate());
			pstmt.setString(8, review.getRecommendPlace());
			pstmt.setString(9, review.getNotRecommendPerson());
			
			pstmt.executeUpdate();
			System.out.println("������¤�??");
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

	// ���並 ������Ʈ�ϴ�.
	public void updateReview(ReviewVo review, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE review  ");
			sql.append("SET roo_no= ?, re_rate_loc= ?, re_rate_clean= ?, re_rate_comu= ?, re_rate_chip= ?, re_vsdate= ?, re_push_pl= ?, re_push_npl= ?   ");
			sql.append("WHERE post_no = ?");
			pstmt = conn.prepareStatement(sql.toString());

			
			pstmt.setInt(1, review.getRoomNo());
			pstmt.setInt(2, review.getRate_loc());
			pstmt.setInt(3, review.getRate_clean());
			pstmt.setInt(4, review.getRate_comu());
			pstmt.setInt(5, review.getRate_chip());
			pstmt.setString(6, review.getVisitDate());
			pstmt.setString(7, review.getRecommendPlace());
			pstmt.setString(8, review.getNotRecommendPerson());

			
			pstmt.setInt(9, review.getNo());
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

//	// ���並 �����Ѵ�.
//	public void deleteReview(int no, Connection conn) throws Exception {
//		PreparedStatement pstmt = null;
//		try {
//			StringBuffer sql = new StringBuffer();
//			sql.append("DELETE FROM attach    ");
//			sql.append("WHERE atta_no = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//
//			pstmt.setInt(1, no);
//
//			pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//			} catch (Exception e2) {
//				throw e2;
//			}
//		}
//	}

}
