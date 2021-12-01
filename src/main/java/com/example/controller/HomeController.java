package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value =  {"/", "/index"})
	public String index() {
		return "page/index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "page/member_login";
	}
	
	@GetMapping("/denine")
	public String denine() {
		return "page/member_denine";
	}
	
	@GetMapping("/join")
	public String join() {
		return "page/member_join";
	}

	@GetMapping("/note_list")
	public String noteList() {
		return "page/note_list";
	}
}
