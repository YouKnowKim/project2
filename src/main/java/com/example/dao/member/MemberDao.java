package com.example.dao.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.vo.MemberAndMemberStateVo;
import com.example.vo.MemberVo;

@Repository("memberDao")
public class MemberDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 회원 목록을 조회한다
	public List<MemberVo> selectMemberList(MemberVo params) {
		return this.sqlSessionTemplate.selectList("Member.selectMemberList", params);
	}

	// 회원수를 조회한다
	public int selectMemberTotalCount(MemberVo params) {
		return this.sqlSessionTemplate.selectOne("Member.selectMemberTotalCount", params);
	}

	// 회원 상세조회
	public MemberVo selectMember(int memNo) {
		System.out.println("selectMember 실행");
		return this.sqlSessionTemplate.selectOne("Member.selectMember", memNo);
	}

	// 회원 상세조회
	public void updateMemberBlock(int memNo) {
		this.sqlSessionTemplate.update("Member.updateMemberBlock", memNo);
	}

	// 회원 등록
	public void insertMember(MemberVo member) {
		this.sqlSessionTemplate.insert("Member.insertMember", member);
	}

	// 회원 수정
	public void updateMember(MemberVo member) {
		this.sqlSessionTemplate.update("Member.updateMember", member);
	}

	// 회원 상태 변경
	public void updateMemberState(int memNo, String state) {
		MemberVo memberVo = new MemberVo(memNo, state);
		this.sqlSessionTemplate.update("Member.updateMemberState", memberVo);
	}

	// 회원 등급 변경
	public void updateMemberGrade(int memNo, int grade) {
		MemberVo memberVo = new MemberVo(memNo, grade);
		this.sqlSessionTemplate.update("Member.updateMemberGrade", memberVo);
	}
}