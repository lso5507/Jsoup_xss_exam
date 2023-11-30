package com.example.jsoup_xss.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.jsoup_xss.dto.Member;
import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {
	@GetMapping("")
	public String test(){
		return "hello";
	}
	@PostMapping("/test/reqParm")
	public Map<String,Object> formTest(@ModelAttribute Member member){
		Map map = new HashMap<String,Object>();
		map.put("memberName",member.getName());
		return map;
	}
	@PostMapping("/test/reqBody")
	public Map<String,Object> formTest(@RequestBody Map<String,Object>map){
		return map;
	}
}
