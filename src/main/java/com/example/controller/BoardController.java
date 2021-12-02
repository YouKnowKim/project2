package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/post_detail")
	public String postDetail() {
		return "page/post_detail";
	}
	
	@GetMapping("/post_list")
	public String postList() {
		return "page/post_list";
	}
	
	@GetMapping("/post_modify")
	public String postModify() {
		return "page/post_modify";
	}
	
	@GetMapping("/post_write")
	public String postWrite() {
		return "page/post_write";
	}
	
}
