package com.example.dao.grade;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.vo.GradeUpVo;

@Repository("gradeUpDao")
public class GradeUpDaoImpl implements GradeUpDao {

	@Autowired
	private SqlSession sqlSession;

	// 등업신청 ( 회원 )
	@Override
	public void insertGradeUp(GradeUpVo gradeup) {
		this.sqlSession.insert("GradeUpDao.insertGradeUp", gradeup);
	}

	// 등업상세조회
	@Override
	public GradeUpVo selectOneGradeUp(int gradeno) {
		return this.sqlSession.selectOne("GradeUpDao.selectOneGradeUp", gradeno);
	}

	// 등업목록조회 ( 회원 )
	@Override
	public List<GradeUpVo> selectGradeUp(int memNo) {
		return this.sqlSession.selectList("GradeUpDao.selectGradeUp", memNo);
	}

	// 등업목록전체조회 (관리자)
	@Override
	public List<GradeUpVo> selectAllGradeUp() {
		return this.sqlSession.selectList("GradeUpDao.selectAllGradeUplist");
	}

	// 등업목록삭제 (회원)
	@Override
	public void deleteGradeUp(int gradeno) {
		this.sqlSession.delete("GradeUpDao.deleteGradeUp", gradeno);
	}

	// 등업신청 가능여부 체크
	public int checkedGradeUp(int memNo) {
		return this.sqlSession.selectOne("GradeUpDao.selectGradeUpYetCount", memNo);
	}

	public void acceptGradeUp(int gradeno) {
		this.sqlSession.delete("GradeUpDao.acceptGradeUp", gradeno);
	}

	public void resetGradeUp(int gradeno) {
		this.sqlSession.delete("GradeUpDao.resetGradeUp", gradeno);
	}
}
