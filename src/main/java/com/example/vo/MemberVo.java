package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVo {
	private int memNo;
	private String id;
	private String pwd;
	private String name;
	private String nick;
	private String gender;
	private String hp;
	private String birth;
	private String joinDate;
	private String state;
	private int boardCount;
	private int commentCount;
	private int visitCount;
	private int grade;
}