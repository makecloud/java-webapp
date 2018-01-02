package com.liuyihui.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
	@RequestMapping("/world")
	public String hello(){
		return "home";
	}
}
