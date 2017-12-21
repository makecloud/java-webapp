package com.liuyihui.platform.controller;


import com.liuyihui.platform.entity.ApiResponse;
import com.liuyihui.platform.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 登录controller
 *
 * @author liuyi
 */
@Controller(value = "loginController")
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping("/login")
    public @ResponseBody
    ApiResponse login(@RequestParam("userName") String userName, @RequestParam("password") String
            password) {

        ApiResponse apiResponse = loginService.login(userName, password);

        return apiResponse;
    }

}
