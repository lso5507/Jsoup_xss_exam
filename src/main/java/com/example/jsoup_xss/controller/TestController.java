package com.example.jsoup_xss.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	@GetMapping("")
	public String test(){
		return "hello";
	}
	@PostMapping("")
	public String formTest(@ModelAttribute Map<String,Object> map){

		return "test";
	}
}
