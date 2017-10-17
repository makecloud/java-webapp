package com.liuyihui.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 控制器学习示例一：
 * 
 * 下列方法全是url映射到控制器方法的各种规则实例
 * @author liuyh
 */
@Controller
@RequestMapping("/controller1")
public class Controller1 {
	
	/**
	 * {×××}占位符， 请求的URL可以是 “/users/123456”或 “/users/abcd” ，通过@PathVariable可以提取 URI模板模式中的{×××}中的×××变量。 
	 */
	@RequestMapping(value="/users/{userId}")
	public void method1(@PathVariable("userId") int userId){
		//省略业务代码
	}
	/**
	 * 这样 也 是 可 以 的 ，请 求的 URL 可 以是“/users/123/create”
	 */
	@RequestMapping(value="/users/{userId}/create")
	public void method2(@PathVariable("userId") int userId){
		//省略业务代码
	}
	/**
	 * 这样也是可以的，请求的 URL 可以是“/users/123/topics/123”
	 */
	@RequestMapping(value="/users/{userId}/topics/{topicId}")
	public void method3(@PathVariable("userId")int userId,@PathVariable("topicId")String topicId){
		//省略业务代码
	}
	//-----------------------------------//
	 /**
	 * 限制请求方法为http get
	 */
	@RequestMapping(value="/create", method = RequestMethod.GET)
	 public void method4(){
		//省略业务代码
	 }
	/**
	 * 限制请求方法为http get或post
	 */
	@RequestMapping(value="/create",  method = {RequestMethod.POST, RequestMethod.GET})
	public void method5(){
		//省略业务代码
	}
	//---------------匹配请求参数的映射写法，5种情形--------------//
	/**
	 * 以下注解表示请求中有“create”的参数名且请求方法为“GET”即可匹配，如可匹配的请求URL“http://×××/parameter1?create”
	 */
	@RequestMapping(params="create", method=RequestMethod.GET)  
    public void mehtod6() { 
		//省略业务代码        
    }
	/**
	 * 以下注解表示请求中没有 “create”参数名且请求方法为“GET”即可匹配，如可匹配的请求URL“http://×××/parameter1?abc”
	 */
	@RequestMapping(params="!create", method=RequestMethod.GET)
	public void mehtod7() { 
		//省略业务代码        
	}
	/**
	 * 以下注解表 示 请求 中 有“submitFlag=create”请求参数且请求 方法 为 “ GET” 即 可 匹 配，如请求URL为 http://×××/parameter2?submitFlag=create
	 */
	@RequestMapping(params="submitFlag=create", method=RequestMethod.GET)   
	public void mehtod8() { 
		//省略业务代码        
	}
	/**
	 * 以下注解表示请求中的参数“submitFlag!=create”且请求方法为“GET”即可匹配，如可匹配的请求URL“http://×××/parameter1?submitFlag=abc”
	 */
	@RequestMapping(params="submitFlag!=create", method=RequestMethod.GET)   
	public void mehtod9() { 
		//省略业务代码        
	}
	/**
	 * 以下注解表示请求中的有“test1”参数名 且 有 “test2=create”参数即可匹配，如可匹配的请求URL“http://×××/parameter3?test1&test2=create
	 */
	@RequestMapping(params={"test1", "test2=create"})
	public void mehtod10() { 
		//省略业务代码        
	}
	
	 
}
