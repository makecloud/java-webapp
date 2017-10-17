package com.liuyihui.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloWorld {
	@RequestMapping("/world")
	public String hello(){
		return "home";
	}
}
