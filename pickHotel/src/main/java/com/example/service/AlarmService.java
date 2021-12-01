package com.example.service;

import java.sql.Connection;
import java.util.List;

import domain.AlarmVo;
import util.DBConn;
import model.dao.alarm.AlarmDao;

public class AlarmService {

	private static AlarmService alarmService;

	private AlarmService() {

	}

	public static AlarmService getInstance() {
		if (alarmService == null) {
			alarmService = new AlarmService();
		}
		return alarmService;
	}

	// �˸��� �߼��Ѵ�
	public void registerAlarm(List<AlarmVo> alarm) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;

		try {
			conn = DBConn.getConnection();

			// Ŀ���� �������� �����Ұ��̱⿡ false�� �����Ѵ�. (Ʈ�������� ������)
			conn.setAutoCommit(false);

			// 1. �Խñ� ������ ����Ѵ�.
			AlarmDao alarmDao = AlarmDao.getInstance();
			// Ŀ�ؼ��� �����ؾ��� Ʈ������ ������ �����ϱ� ������, insertBoard�� conn�� ��������
			alarmDao.insertAlarm(alarm, conn);

			// ���� �ڵ忡 ������ ���ٸ� �Ʒ� �ڵ���� ����Ǿ� isSuccess = true�� �ȴ�.
			// ���� ������ �־��ٸ� try�������� �ٷ� �������� ����
			isSuccess = true;

		} catch (Exception e) {

			throw e;

		} finally {

			try {
				// ������ ������ ���
				if (conn != null) {
					// ���� �ڵ尡 ������ ����Ǿ� upload�� ���������� �̷�����ٸ�
					if (isSuccess) {
						// sql�ڵ带 Ŀ���Ѵ�.
						conn.commit();
					} else {
						// sql�ڵ带 �ѹ��Ѵ�.
						conn.rollback();
					}
					conn.close();
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// ȸ���� �˸� ����� ��ȸ�Ѵ�.
	public List<AlarmVo> retrievAlarmList(int mbNo) throws Exception {
		AlarmDao alarmDao= AlarmDao.getInstance();
		
		//ȸ���� �˸� ����� ��ü ��ȸ�Ѵ�.
		List<AlarmVo> alarms = alarmDao.selectAlarmList(mbNo);
		
		//��ȸ�� ���ÿ� �˸� ���� ó���� �ȴ�.
		alarmDao.updateReadAlarmList(mbNo);
		
		return alarms;

	}
	
	// ȸ���� �̿��� ������ �˸��� ������ ��ȸ�Ѵ�.
	public int retrieveNoReadAlarmCount(int mbNo) throws Exception {
		return AlarmDao.getInstance().selectNoReadAlarmCount(mbNo);
	}

	// ���ǿ� �ش��ϴ� �˸��� �����Ѵ�.
	public void removeAlarm(int alarmNo) throws Exception {
		AlarmDao.getInstance().delectAlarm(alarmNo);
	}

	// ���ǿ� �ش��ϴ� �˸��� ��ü �����Ѵ�.
	public void removeAllAlarm(int memNo) throws Exception {
		AlarmDao.getInstance().delectAllAlarm(memNo);
	}

}
