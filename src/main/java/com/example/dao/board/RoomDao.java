package com.example.dao.board;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.RoomVo;
import util.DBConn;

public class RoomDao {
	// singleton pattern
	private static RoomDao roomDao;

	private RoomDao() {

		}

	public static RoomDao getInstance() {
		if (roomDao == null) {
			roomDao = new RoomDao();
		}
		return roomDao;
	}
	
	// ���� ��ȸ�Ѵ�.
	public ArrayList<RoomVo> selectRoomList() throws Exception {
		
		ArrayList<RoomVo> room = new ArrayList<RoomVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = DBConn.getConnection();	
			stmt = conn.createStatement();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT roo_no, roo_code, roo_address, roo_sysname, roo_realname    ");                                   
			sql.append("FROM room " );                                                        
			rs = stmt.executeQuery(sql.toString());
			
			while (rs.next()) {
				int no = rs.getInt(1);
				int roomCode = rs.getInt(2);
				String roomAddress = rs.getString(3);
				String originalFileName = rs.getString(4);
				String systemFileName = rs.getString(5);
				
				room.add(new RoomVo(no, roomCode, roomAddress, originalFileName, systemFileName));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return room;
	}

}
