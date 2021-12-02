package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.board.AttachDao;
import com.example.dao.board.PostDao;
import com.example.dao.board.ReviewDao;
import com.example.vo.AttachVo;
import com.example.vo.PostVo;
import com.example.vo.ReviewVo;

@Service("postService")
public class PostService {
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private AttachDao attachDao;
	
	
	// 게시글 정보를 등록하다.
	public void registerPost(PostVo post, ReviewVo review) {
		int no = postDao.insertPost(post);
		
		if(post.getBoardNo() == 1) {
			review.setNo(no);
			reviewDao.insertReview(review);
		}
		
		for (AttachVo attach : post.getAttachList()) {
			attach.setPostNo(no);
			attachDao.insertPostAttach(attach);
		}
	}
	
	//게시글 전체 조회
	public List<PostVo> retrieveAllPosts(int boardNo){
		return postDao.selectAllPosts(boardNo);
	}
	
	// 게시글 상세정보를 조회하다.
	public PostVo retrievePost(int no) {
		postDao.upHitcount(no);
		return postDao.selectPost(no);
	}
	
	//게시글 정보를 변경하다.
	public void modifyPost(PostVo post) {
		postDao.updatePost(post);
		
		if(post.getBoardNo() == 1) {
			reviewDao.updateReview(post.getReview());
		}
		
		for (AttachVo attach : post.getAttachList()) {
			attach.setPostNo(post.getNo());
			attachDao.insertPostAttach(attach);
		}
	}
	
	public void removePostAttach(int attachNo) {
		attachDao.deletePostAttach(attachNo);
	}
	
	public void removePost(int postNo) {
		attachDao.deleteAttachbyPost(postNo);
		postDao.deletePost(postNo);
	}
}






















