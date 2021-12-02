package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.member.MemberDao;
import com.example.vo.MemberVo;

@Service("cityService")
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	// 로그인
	public MemberVo loginMember(Map map) {
		return this.memberDao.Selectlogin(map);
	}
	
	// 탈퇴회원 조회
	public String checkWithDraw(String id) {
		return this.memberDao.SelectWithDraw(id);
	}

	// 아이디 찾기
	public List<MemberVo> retrieveMemberId(Map map) {
		return this.memberDao.selectNBMemberList(map);
	}

	// 비밀번호 변경
	public void modifyPw(Map map) {
		this.memberDao.UpdatePwMember(map);
	}

	// 회원가입
	public void registerMember(Map map) {
		this.memberDao.insertMember(map);
	}

	// 아이디 중복체크
	public String retrieveIdCheck(String id) {
		return this.memberDao.selectIdCheck(id);
	}

	// 닉네임 중복체크
	public String retrieveNickCheck(String nick) {
		return this.memberDao.selectNickCheck(nick);
	}
}