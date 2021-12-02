package com.example.service;

import java.util.ArrayList;

import domain.MemberStateVo;
import domain.MemberVo;
import model.dao.member.MemberDao;
import model.dao.member.MemberStateDao;

public class MemberStateService {
private static MemberStateService service;
	
	private MemberStateService(){
	}
	
	public static MemberStateService getInstance() {
		if (service == null) {
			service = new MemberStateService();
		}
		return service;
	}
		
	public void registerMemberState(MemberStateVo memberStateVo) throws Exception{
		MemberStateDao memberStateDao  = MemberStateDao.getInstance();
		memberStateDao.insertMemberState(memberStateVo);
	}
	
	public void reviseMemberState(MemberStateVo memberStateVo) throws Exception{
		MemberStateDao memberStateDao  = MemberStateDao.getInstance();
		memberStateDao.updateMemberState(memberStateVo);
	}
	
	public ArrayList<MemberStateVo> retrieveMemberStateList(int startRow, int postSize) throws Exception{
		MemberStateDao memberstateDao = MemberStateDao.getInstance();
		return memberstateDao.selectMemberStateList(startRow, postSize);
			
	}
	
	public int retrieveTotalMemberStateCount() throws Exception {
		return MemberStateDao.getInstance().selectTotalMemberStateCount();
	}
}