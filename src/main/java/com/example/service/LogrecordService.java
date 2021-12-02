package com.example.service;

import java.util.ArrayList;

import domain.LogrecordVo;
import model.dao.statistic.LogrecordDao;

public class LogrecordService {
	
	private static LogrecordService service;
	
	private LogrecordService() {
		
	}
	
	public static LogrecordService getInstance() {
		if(service == null) {
			service = new LogrecordService();
		}
		
		return service;
	}
	
	//�α� ����Ʈ ��ȸ
	public ArrayList<LogrecordVo> retrieveMemberLogList(int startRow, int postSize) throws Exception{
		LogrecordDao logrecordDao = LogrecordDao.getInstance();
		return logrecordDao.selectMemberLogRecord(startRow, postSize);
	}
	
	//��ü �α� ��� ���� ��ȸ
	public int retrieveTotalLogrecordCount() throws Exception{
		LogrecordDao logrecordDao = LogrecordDao.getInstance();
		return logrecordDao.selectTotalLogrecordCount();
	}
	
	// ���� �α� ��� ���� ��ȸ
	public int retrieveTodayLogrecordCount() throws Exception{
		LogrecordDao logrecordDao = LogrecordDao.getInstance();
		return logrecordDao.selectTodayLogrecordCount();
	}
	
	//�׷��� ������ �α� ��� ���� ��ȸ
	public ArrayList<Integer> retrieveTypeOfGraphLogrecordCount(int typeOfGraphNumber) throws Exception{
		ArrayList<Integer> typeOfGraphLecordCounts = new ArrayList<Integer>();
		if(typeOfGraphNumber == 7) {
			LogrecordDao logrecordDao = LogrecordDao.getInstance();
			typeOfGraphLecordCounts = logrecordDao.selectWeeklyLogrecordCount();
		} else if(typeOfGraphNumber == 30) {
			LogrecordDao logrecordDao = LogrecordDao.getInstance();
			typeOfGraphLecordCounts = logrecordDao.selectMonthlyLogrecordCount();
		}
		return typeOfGraphLecordCounts;
	}
	
	//�׷��� ������ �Խñ� ���� ��ȸ
	public ArrayList<Integer> retrieveTypeOfGraphPostCount(int typeOfGraphNumber) throws Exception{
		ArrayList<Integer> typeOfGraphPostCounts = new ArrayList<Integer>();
		if(typeOfGraphNumber == 7) {
			LogrecordDao logrecordDao = LogrecordDao.getInstance();
			typeOfGraphPostCounts = logrecordDao.selectWeeklyPostCount();
		} else if(typeOfGraphNumber == 30) {
			LogrecordDao logrecordDao = LogrecordDao.getInstance();
			typeOfGraphPostCounts = logrecordDao.selectMonthlyPostCount();
		}
		return typeOfGraphPostCounts;
	}
	
	public void registerLogrecord(String userIp, String userId) throws Exception {
		LogrecordDao logrecordDao = LogrecordDao.getInstance();
		logrecordDao.insertLogrecord(userIp, userId);
	}
}











