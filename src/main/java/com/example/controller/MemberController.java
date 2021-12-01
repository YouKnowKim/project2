package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.service.MemberService;
import com.example.vo.MemberVo;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/findid")
	public String memberFindId() {
		return "page/member_findid";
	}

	@GetMapping("/member/findpw")
	public String memberFindPw() {
		return "page/member_findpw";
	}

	@GetMapping("/member/join")
	public String memberJoin() {
		return "page/member_join";
	}

	@GetMapping("/member/login")
	public String memberLogin() {
		return "page/member_login";
	}
	
//	회원 정보 상세조회
	@GetMapping("/member")
	public String memberDetail(MemberVo member) {
		memberService.reviseMember(member);
		return "page/member_modify";
	}
	
//	회원 정보 수정
	@PostMapping("/member/modify")
	public String memberModity(@ModelAttribute("member") MemberVo member) {
		memberService.reviseMember(member);
		return "redirect:page/member/modify";
	}

	@GetMapping("/member/room")
	public String memberRoom() {
		return "page/member_room";
	}

	@GetMapping("/member/req_list")
	public String memberReqList() {
		return "page/member_req_list";
	}

	@GetMapping("/member/welcome")
	public String memberWelcome() {
		return "page/member_welcome";
	}
}
