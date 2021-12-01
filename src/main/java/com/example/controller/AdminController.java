package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.MemberService;
import com.example.service.MemberStateService;
import com.example.vo.MemberAndMemberStateVo;
import com.example.vo.MemberStateVo;
import com.example.vo.MemberVo;

import lombok.extern.java.Log;

@Log
@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberStateService memberStateService;

	@GetMapping(value = { "/intranet", "/intranet/index" })
	public String intranet() {
		return "page/intranet/index";
	}

	// ######## 회원 목록 ########
	// 회원 목록
	@GetMapping("/intranet/member/list")
	public String memberList(@ModelAttribute("params") MemberVo params, Model model) {
		List<MemberVo> members = memberService.retrieveMemberList(params);
		model.addAttribute("members", members);

		return "page/intranet/member_list";
	}

	// 회원 상세보기
	@GetMapping("/intranet/member/{memNo}")
	public String memberDetail(@PathVariable("memNo") int memNo, Model model) {
		MemberVo member = memberService.retrieveMember(memNo);
		model.addAttribute("member", member);

		return "page/intranet/member_modify";
	}

	// 회원정보 수정하기
	@PostMapping("/intranet/member/modify")
	public String memberModify(@ModelAttribute("member") MemberVo member) {
		memberService.reviseMember(member);

		return "redirect:/intranet/member/" + member.getMemNo();
	}

	// 회원정보 등급 수정하기
	@ResponseBody
	@PostMapping("/intranet/member/grade_modify")
	public List<MemberVo> memberGradeModify(@ModelAttribute("params") MemberVo params, @RequestParam("memNoList") List<Integer> ids, @RequestParam("grade") int grade) {
		
		for (int memNo : ids) {
			memberService.reviseMemberGrade(memNo, grade);
		}
		
		List<MemberVo> list = memberService.retrieveMemberList(params);
		return list;
	}

	// ######## 회원 강퇴 ########
	// 강퇴 회원 목록
	@GetMapping("/intranet/member/block_list")
	public String memberBlockList(@ModelAttribute("params") MemberStateVo params, Model model) {
		List<MemberAndMemberStateVo> members = memberStateService.retrieveMemberBlockList(params);
		model.addAttribute("members", members);
		return "page/intranet/member_block_list";
	}

	// 회원 강퇴 사유 작성
	@RequestMapping(value = "/intranet/member/block_write", method = { RequestMethod.GET, RequestMethod.POST })
	public String memberBlockWrite() {

		return "page/intranet/member_block_write_ajax";
	}

	// 회원 강퇴
	@PostMapping("/intranet/member/block")
	public String memberBlock(@RequestParam("memNoList") List<Integer> ids, @RequestParam("reason") String reason,
			Model model) {
		for (int memNo : ids) {
			// 회원 상태 "강제탈퇴" 변경
			memberService.reviseMemberState(memNo, "3");

			// 강퇴 정보 추가
			MemberStateVo memberState = new MemberStateVo(memNo, reason);
			memberStateService.registerMemberState(memberState);
		}

		return "redirect:/intranet/member/block_list";
	}

	// 회원 강퇴 취소
	@ResponseBody
	@PostMapping("/intranet/member/block_cancel")
	public List<MemberAndMemberStateVo> memberBlockCancel(@ModelAttribute("params") MemberStateVo params,
			@RequestParam("stateNoList") List<Integer> stateNoList, Model model) {
		int memNo = 0;
		for (int stateNo : stateNoList) {

			// 강제 탈퇴 건PK값으로 회원PK 조회
			memNo = memberStateService.retrieveMemberBlock(stateNo).getMemNo();
			System.out.println("memNo" + memNo);
			
			// 회원 상태 "탈퇴" 변경
			memberService.reviseMemberState(memNo, "2");
		}

		List<MemberAndMemberStateVo> list = memberStateService.retrieveMemberBlockList(params);
		return list;
	}

	@GetMapping("/intranet/member/rq_list")
	public String memberRqList() {
		return "page/intranet/member_rq_list";
	}

	@GetMapping("/intranet/boardgroup_list")
	public String boardGroupList() {
		return "page/intranet/boardgroup_list";
	}

	@GetMapping("/intranet/board_list")
	public String boardList() {
		return "page/intranet/board_list";
	}

	@GetMapping("/intranet/post_block_list")
	public String postBlockList() {
		return "page/intranet/post_block_list";
	}

	@GetMapping("/intranet/event_list")
	public String eventList() {
		return "page/intranet/event_list";
	}

	@GetMapping("/intranet/statistics")
	public String statistics() {
		return "page/intranet/statistics";
	}

	@GetMapping("/intranet/grade")
	public String grade() {
		return "page/intranet/grade";
	}

}
