package com.liuyihui.platform.util;

import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        //session异常,跳转到登录页面
        if (e instanceof HttpSessionRequiredException) {
            return new ModelAndView("login");
        }

        //未分配modelAndView的的异常
        return new ModelAndView("error");
    }
}
