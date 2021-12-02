package com.example.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import domain.AttachVo;
import domain.PostVo;
import domain.ReviewVo;
import model.dao.board.AttachDao;
import model.dao.board.PostDao;
import model.dao.board.ReviewDao;
import util.DBConn;

public class PostService {

	// single pattern
	private static PostService service;

	private PostService() {

	}

	public static PostService getInstance() {
		if (service == null) {
			service = new PostService();
		}
		return service;
	}

	// �Խñ� ������ ����ϴ�.
	public void registerPost(PostVo post, ReviewVo review) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;
		try {
			conn = DBConn.getConnection();
			// tx.begin
			conn.setAutoCommit(false);

			// 1.�Խñ� ������ ����Ѵ�.
			PostDao postDao = PostDao.getInstance();
			int no = postDao.insertpost(post, conn);
			
			
			if(post.getBoardNo()==1) {
				// ���� ������ ����Ѵ�.
				review.setNo(no);
				ReviewDao reviewDao = ReviewDao.getInstance();
				reviewDao.insertReview(review, conn);
				System.out.println("��������������");
				
			}

			// 2.���� ������ ����ϴ�.
			AttachDao attachDao = AttachDao.getInstance();

			for (AttachVo attach : post.getAttachList()) {
				attach.setPostNo(no);
				attachDao.insertPostAttach(attach, conn);
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
			} catch (Exception ex) {
				throw ex;
			}
		}
	}
//	
//
	// �Խñ� �������� ��ȸ�ϴ�.
	public PostVo retrievePost(int no) throws Exception {
		
		PostDao postDao = PostDao.getInstance();
		postDao.upHitcount(no);
		return postDao.selectPost(no);
	}
	
	// �Խñ������� �����ϴ�.
	public void modifyPost(PostVo post) throws Exception {
		// Ʈ����� ó��
		// �Խñ� ������ �����ϴ�.
		// ÷�ε� ������ �����ϴ� ��� ������ ����Ѵ�.
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);

			PostDao postDao = PostDao.getInstance();
			postDao.updatePost(post, conn);	
			
			if(post.getBoardNo()==1) {
				// ���� ������ �����Ѵ�.
				ReviewDao reviewDao = ReviewDao.getInstance();
				reviewDao.updateReview(post.getReview(), conn);
				
			}

			AttachDao attachDao = AttachDao.getInstance();
			
			for (AttachVo attach : post.getAttachList()) {
				attach.setPostNo(post.getNo());
				attachDao.insertPostAttach(attach, conn);
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
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	
	public void removePostAttach(int attachNo) throws Exception {

		// DB���� �ش��ϴ� ÷�� ������ �����Ѵ�.
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);

			AttachDao attachDao = AttachDao.getInstance();
			attachDao.deletePostAttach(attachNo, conn);
			
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
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// �Խñ� ������ �����ϴ�.
	public void removePost(int postNo) throws Exception {
		// Ʈ����� ó��
		// DB���� �Խñۿ� �ش��ϴ� ���� ������ �����Ѵ�.
		// DB���� �Խñ� ������ �����Ѵ�.
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);

			AttachDao attachDao = AttachDao.getInstance();
			attachDao.deleteAttachbyPost(postNo, conn);

			PostDao postDao = PostDao.getInstance();
			postDao.deletePost(postNo, conn);

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
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}
//	
	
	//�Խñ� ����� ��ȸ�ϴ�.
	public ArrayList<PostVo> retrievePostSearchList(HashMap<String,String> map, int startRow, int postSize) throws Exception {
		PostDao postDao = PostDao.getInstance();
		return postDao.selectPostSearchList(map,startRow, postSize);		
	}
	
	//�� �Խñ� ���� ���ϴ�.
	public int retrievePostSearchListCount(HashMap<String,String> map) throws Exception {
		PostDao postDao = PostDao.getInstance();
		return postDao.selectTotalPostCount(map);
	}	

}

















