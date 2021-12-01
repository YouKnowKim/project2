package com.example.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.member.MemberDao;
import com.example.paging.Criteria;
import com.example.paging.PaginationInfo;
import com.example.vo.MemberAndMemberStateVo;
import com.example.vo.MemberStateVo;
import com.example.vo.MemberVo;

@Service("memberService")
public class MemberService {

	@Autowired
	private MemberDao memberDao;

	// 회원 등록
	public void registerMember(MemberVo member) {
		this.memberDao.insertMember(member);
	}

	// 회원 수정
	public void reviseMember(MemberVo member) {
		this.memberDao.updateMember(member);
	}

	// 회원 상태 변경
	public void reviseMemberState(int memNo, String state) {
		this.memberDao.updateMemberState(memNo, state);
	}
	
	// 회원 등급 변경
	public void reviseMemberGrade(int memNo, int grade) {
		this.memberDao.updateMemberGrade(memNo, grade);
	}

	// 회원 조회
	public MemberVo retrieveMember(int memNo) {
		return this.memberDao.selectMember(memNo);
	}

	// 회원 전체조회
	public List<MemberVo> retrieveMemberList(MemberVo params) {
		List<MemberVo> memberList = Collections.emptyList();

		int memberTotalCount = this.memberDao.selectMemberTotalCount(params);
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(memberTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (memberTotalCount > 0) {
			memberList = this.memberDao.selectMemberList(params);
		}

		return memberList;
	}

	// 회원 수 조회
	public int retrieveMemberTotalCount(MemberVo params) {
		return this.memberDao.selectMemberTotalCount(params);
	}
	
	
}