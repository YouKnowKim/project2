package com.example.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.MemberStateVo;
import domain.MemberVo;
import util.DBConn;

public class MemberStateDao {
	// singleton pattern
			private static MemberStateDao MemberStateDao;
			
			private MemberStateDao() {
				
			}
			
			public static MemberStateDao getInstance() {
				if (MemberStateDao == null) {
					MemberStateDao = new MemberStateDao();
				}
				return MemberStateDao;
			}
			
			
			
			// 회원 탈퇴 등록
			public void insertMemberState(MemberStateVo memberStateVo) throws Exception {
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				
				
				Statement stmt = null;
				ResultSet rs = null;
				int[] memNos = memberStateVo.getMemNos();
				
				try {
					conn = DBConn.getConnection();
					
					StringBuffer sql = new StringBuffer();
					sql.append("INSERT INTO memberstate (mb_no, ms_reason, ms_datetime) ");
					sql.append("VALUES (?, ?, DATE_FORMAT(now(), '%Y/%m/%d %H:%i:%s')); ");
					
					
					pstmt = conn.prepareStatement(sql.toString());
					
					for (int memNo : memNos) {
			        	 pstmt.setInt(1, memNo);
			        	 pstmt.setString(2, memberStateVo.getReason());
			        	
			        	
			        	 pstmt.addBatch();
			        	 pstmt.clearParameters(); // 파라미터 초기화
			            
			         }
			         pstmt.executeBatch();
			         pstmt.clearParameters(); // Batch 초기화
			         
			        		
	
				} catch (Exception e) {
					throw e;
				} finally {
					try {
						
						if(rs != null) rs.close();
						if(stmt != null) stmt.close();
						if(pstmt != null) pstmt.close();
						
						if(conn != null) conn.close(); 
					} catch (Exception e2) {
						throw e2;
					}
				}
				}
			
			// 회원 탈퇴 상태 변경
			public void updateMemberState(MemberStateVo memberStateVo) throws Exception {
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				int[] memNos = memberStateVo.getMemNos();
				
				try {
					conn = DBConn.getConnection();
					
					StringBuffer sql = new StringBuffer();
					sql.append("UPDATE member SET mb_state = 3  WHERE mb_no = ?; ");			
					pstmt = conn.prepareStatement(sql.toString());
					
					for (int memNo : memNos) {
			        	 pstmt.setInt(1, memNo);
			       
			        	 pstmt.addBatch();
			        	 pstmt.clearParameters(); // 파라미터 초기화
			            
			         }
			         pstmt.executeBatch();
			         pstmt.clearParameters(); // Batch 초기화
			         
			        	
					
				} catch (Exception e) {
					throw e;
				} finally {
					try {
						
						if(rs != null) rs.close();
						
						if(pstmt != null) pstmt.close();
						
						if(conn != null) conn.close(); 
					} catch (Exception e2) {
						throw e2;
					}
				}
				}
			
			// 회원 탈퇴 목록을 조회한다
						public ArrayList<MemberStateVo> selectMemberStateList(int startRow, int postSize) throws Exception {
							ArrayList<MemberStateVo> boardstate = new ArrayList<MemberStateVo>();
							Connection conn = null;
							PreparedStatement pstmt = null;
							ResultSet rs = null;
							try {
								conn = DBConn.getConnection();

								StringBuffer sql = new StringBuffer();
								sql.append("SELECT m.mb_nick, m.mb_state, s.ms_no, s.mb_no, s.ms_reason, s.ms_datetime   ");
								sql.append("FROM member as m left join memberstate as s on m.mb_no = s.mb_no  ");
								sql.append("ORDER BY s.ms_no DESC LIMIT ? OFFSET ?; ");
					
								pstmt = conn.prepareStatement(sql.toString());
								
								pstmt.setInt(1, postSize);
								pstmt.setInt(2, startRow);
								
								rs = pstmt.executeQuery();

								while (rs.next()) {
									
									String nick = rs.getString(1);
									int state = rs.getInt(2);
									int stateNo = rs.getInt(3);
									int memNo = rs.getInt(4);
									String reason = rs.getString(5);
									String withdrawDate = rs.getString(6);
									
									
									boardstate.add(new MemberStateVo(nick, state, stateNo, memNo, reason, withdrawDate));
								}

							} catch (Exception e) {
								throw e;
							} finally {
								try {
									
									if (conn != null) conn.close();
								} catch (Exception e2) {
									throw e2;
								}
							}
							return boardstate;
						}
						
								// 탈퇴 회원 수를 구하다
								public int selectTotalMemberStateCount() throws Exception {
									Connection conn = null;
									Statement stmt = null;
									ResultSet rs = null;
									int count = 0;
									try {
										conn = DBConn.getConnection();
										stmt = conn.createStatement();
										
										StringBuffer sql = new StringBuffer();
										sql.append("select count(*) from memberstate;");
										
										rs = stmt.executeQuery(sql.toString());
										if(rs.next()) {
											count = rs.getInt(1);
										}
									} catch (Exception e) {
										throw e;
									}finally {
										try {				
											if (stmt != null) stmt.close();
											if (conn != null) conn.close();
										} catch (Exception e2) {
											throw e2;
										}
									}	
									
									return count;
								}
}
