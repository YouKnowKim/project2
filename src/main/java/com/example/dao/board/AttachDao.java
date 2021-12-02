package com.example.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import domain.AttachVo;

public class AttachDao {

	// sigleton pattern
	private static AttachDao attachDao;

	private AttachDao() {

	}

	public static AttachDao getInstance() {
		if (attachDao == null) {
			attachDao = new AttachDao();
		}
		return attachDao;
	}

	// ������ ����ϴ�.
	public void insertPostAttach(AttachVo attach, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO attach (post_no, atta_sysname, atta_realname, atta_size, atta_type)  ");
			sql.append("VALUES (?, ?, ?, ?, ?)	");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, attach.getPostNo());
			pstmt.setString(2, attach.getSystemFileName());
			pstmt.setString(3, attach.getOriginalFileName());
			pstmt.setInt(4, attach.getFileSize());
			pstmt.setInt(5, attach.getFileType());

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

	// ������ �����Ѵ�.
	public void deletePostAttach(int attaNo, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM attach    ");
			sql.append("WHERE atta_no = ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, attaNo);

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

	// ������ �����Ѵ�.
	public void deleteAttachbyPost(int poNo, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM attach    ");
			sql.append("WHERE post_no = ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, poNo);

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

}
