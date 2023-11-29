package com.example.jsoup_xss.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {
	@GetMapping("/test")
	public String test(){
		return "hello";
	}
	@PostMapping("/test")
	public String formTest(@RequestBody Map<String,Object> map){

		return "test";
	}
}
