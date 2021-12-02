package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value =  {"/", "/index"})
	public String index() {
		return "page/index";
	}
	
	@GetMapping("/note_list")
	public String noteList() {
		return "page/note_list";
	}
	
}
