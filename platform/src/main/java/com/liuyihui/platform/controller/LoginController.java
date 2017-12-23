package com.liuyihui.platform.controller;


import com.liuyihui.platform.entity.ApiResponse;
import com.liuyihui.platform.entity.User;
import com.liuyihui.platform.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 登录controller
 *
 * @author liuyi
 */
@SessionAttributes("user")
@Controller(value = "loginController")
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping("/login")
    public String login(ModelMap modelMap) {
        //
        if (modelMap.containsAttribute("user")) {
            System.out.println("已登录过");
            return "index";
        }
        return "login";
    }


    @RequestMapping("/dologin")
    public @ResponseBody
    ApiResponse login(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            ModelMap modelMap,
            HttpSession session) {

        //定义结果
        ApiResponse apiResponse = new ApiResponse();

        //通过session中有没有user,判断是否已登录
        /*if (user.getUserName() != null) {
            apiResponse.setCode(0);
            apiResponse.setMessage("已登录过");
            System.out.println(user);
            return apiResponse;
        }*/
        if (modelMap.containsAttribute("user")) {
            User user = (User) modelMap.get("user");
            System.out.println("已含有的user:" + user);
            if (user.getUserName() != null) {
                apiResponse.setCode(0);
                apiResponse.setMessage("已登录过");
                return apiResponse;
            }
        }
        //获取用户
        User user = loginService.login(userName, password);

        //校验用户
        if (user == null) {
            apiResponse.setMessage("用户不存在");
        } else if (!password.equals(user.getPassword())) {
            apiResponse.setMessage("密码错误");
        } else {
            apiResponse.setCode(0);
            apiResponse.setMessage("登录成功");
            modelMap.addAttribute("user", user);
        }

        //打印session对象
        System.out.println("session in login:" + session);

        //返回数据
        return apiResponse;
    }

    @RequestMapping("/logout")
    public String logout(@ModelAttribute("user") User user, SessionStatus
            sessionStatus, HttpSession session) {
        /*if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
            System.out.println("移除Session中的user");
            //可能是因为model中还存有User对象, 所以方法执行完返回页面之前,springmvc根据类名的@SessionAttribute注解又将model中的User对象存回了Session中
        }*/
        session.removeAttribute("user");
        sessionStatus.setComplete();//这个方法告诉了springmvc清除session中的所有属性了

        return "index";
    }

}
