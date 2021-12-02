package com.example.service;

import java.util.ArrayList;
import java.util.List;

import domain.GradeUpVo;
import domain.MemberGradeUpVo;
import model.dao.grade.GradeUpDao;

public class gradeUpService {

	private static gradeUpService gradeUpService;

	private gradeUpService() {

	}

	public static gradeUpService getInstance() {
		if (gradeUpService == null) {
			gradeUpService = new gradeUpService();
		}
		return gradeUpService;
	}

	// 등업신청 정보를 등록한다.
	public void registerGradeUp(GradeUpVo GradeUp) throws Exception {
		GradeUpDao.getInstance().insertGradeUp(GradeUp);
	}

	// 등업신청 목록을 조회한다.
	public List<MemberGradeUpVo> retrieveGradeUp(GradeUpVo gradeupVo ) throws Exception {
		List<MemberGradeUpVo> MemberGradeUpList = new ArrayList<MemberGradeUpVo>();
		return MemberGradeUpList;
	}

	// 등업신청 목록을 일괄 승인한다.
	public void approvereviseGradeUp(ArrayList<GradeUpVo> gradeUpVo) throws Exception {
		GradeUpDao.getInstance().approvereviseGradeUp(gradeUpVo);
	}
	// 등업신청 목록을 일괄 반려한다.
		public void rejectreviseGradeUp(ArrayList<GradeUpVo> gradeUpVo) throws Exception {
			GradeUpDao.getInstance().rejectreviseGradeUp(gradeUpVo);
		}
    // 등업신청 목록을 취소한다. - 등업 취소 ( 사용자 )
	public void cancelreviseGradeUp(GradeUpVo gradeUp) throws Exception {
		GradeUpDao.getInstance().cancelreviseGradeUp(gradeUp);
	}

	public void approvereviseGradeUp() {
		// TODO Auto-generated method stub
		
	}
}

