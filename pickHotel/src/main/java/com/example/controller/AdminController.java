package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping(value =  {"/intranet", "/intranet/index"})
	public String intranet() {
		return "page/intranet/index";
	}
	
	@GetMapping("/intranet/member_list")
	public String memberList() {
		return "page/intranet/member_list";
	}
	
	@GetMapping("/intranet/member_rq_list")
	public String memberRqList() {
		return "page/intranet/member_rq_list";
	}
	
	@GetMapping("/intranet/member_block_list")
	public String memberBlockList() {
		return "page/intranet/member_block_list";
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
