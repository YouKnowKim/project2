package com.example.dao.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.vo.MemberVo;

@Repository("memberDao")
public class MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 로그인
	public MemberVo Selectlogin(Map map) {
		return this.sqlSessionTemplate.selectOne("MemberVo.Selectlogin", map);
	}
	
	// 탈퇴회원 조회
	public String SelectWithDraw(String id) {
		return this.sqlSessionTemplate.selectOne("MemberVo.SelectWithDraw", id);
	}

	// 아이디 찾기
	public List<MemberVo> selectNBMemberList(Map map) {
		return this.sqlSessionTemplate.selectList("MemberVo.SelectNBMemberList", map);
	}

	// 비밀번호 변경
	public void UpdatePwMember(Map map) {
		this.sqlSessionTemplate.update("MemberVo.UpdatePwMember", map);
	}

	// 회원가입
	public void insertMember(Map map) {
		this.sqlSessionTemplate.insert("MemberVo.InsertMember", map);
	}

	// 아이디 중복체크
	public String selectIdCheck(String id) {
		return this.sqlSessionTemplate.selectOne("MemberVo.selectIdCheck", id);
	}

	// 닉네임 중복체크
	public String selectNickCheck(String nick) {
		return this.sqlSessionTemplate.selectOne("MemberVo.selectNickCheck", nick);
	}
}