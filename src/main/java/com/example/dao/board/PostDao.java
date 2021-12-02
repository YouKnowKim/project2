package com.example.dao.board;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.vo.PostVo;

@Repository("postDao")
public class PostDao {
	@Autowired
	private SqlSession sqlSession;
	
	//게시글 정보를 등록하다.
	public int insertPost(PostVo post) {
		this.sqlSession.insert("PostDao.insertPost",post);
		return this.sqlSession.selectOne("PostDao.selectLastInsertID"); //SELECT LAST_INSERT_ID() 처리 필요
	}
	
	// 게시글 검색 목록을 조회하다.
	
	
	// 전체 게시글 목록 조회
	public List<PostVo> selectAllPosts(int boardNo){
		List<PostVo> lists = this.sqlSession.selectList("PostDao.selectAllPosts", boardNo);
		return lists;
	}
	
	//조회수를 증가하다.
	public void upHitcount(int no) {
		this.sqlSession.update("PostDao.upHitcount", no);
	}
	
	//게시글 번호에 해당하는 게시글 상세정보를 조회하다.
	public PostVo selectPost(int no) {
		PostVo postVo = this.sqlSession.selectOne("PostDao.selectPost",no);
		return postVo;
	}
	
	//게시글 정보를 변경하다.
	public void updatePost(PostVo post) {
		this.sqlSession.update("PostDao.updatePost", post);
	}
	
	//게시글 정보를 삭제하다.
	public void deletePost(int postNo) {
		this.sqlSession.delete("PostDao.deletePost", postNo);
	}
	
	//총 게시글 수를 구한다.
	public int selectTotalPostCount(HashMap<String, String> map) {
		int count = 0 ;
		count = this.sqlSession.selectOne("PostDao.selectTotalPostCount", map);
		return count;
	}
	
	
}














