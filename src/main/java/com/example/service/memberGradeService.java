package com.example.service;

import java.util.List;

import domain.MemberGradeVo;

import model.dao.grade.MemberGradeDao;

public class memberGradeService {

	private static memberGradeService memberGradeService;

	private memberGradeService() {

	}

	public static memberGradeService getInstance() {
		if (memberGradeService == null) {
			memberGradeService = new memberGradeService();
		}
		return memberGradeService;
	}

	// 멤버등급을 조회 한다.
	public List<MemberGradeVo> retrieveMemberGrade() throws Exception {
		return MemberGradeDao.getInstance().selectMemberGradeList();
	}

	// 멤버의 등업가능한 등급을 조회한다.
	public List<MemberGradeVo> retrievePossibleMemberGrade(int MemNo) throws Exception {
		return MemberGradeDao.getInstance().selectPossibleMemberGrade(MemNo);
	}

	// 멤버 등급을 수정한다.
	public void reviseMemberGrade(MemberGradeVo memberGrade) throws Exception {
		MemberGradeDao.getInstance().reviseMemberGrade(memberGrade);
	}
}
