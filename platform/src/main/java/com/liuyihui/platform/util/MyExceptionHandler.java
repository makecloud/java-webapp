package com.liuyihui.platform.util;

import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        //打印异常信息堆栈
//        e.printStackTrace();
        //session异常,跳转到登录页面
        if (e instanceof HttpSessionRequiredException) {
//            return new ModelAndView("login");
            return new ModelAndView("redirect:resources/login/login.html");
        }
        //未分配modelAndView的的异常
        return new ModelAndView("commonErrorPage");
    }
}
