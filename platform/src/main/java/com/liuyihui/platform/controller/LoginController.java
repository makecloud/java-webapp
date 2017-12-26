package com.liuyihui.platform.controller;


import com.liuyihui.platform.entity.ApiResponse;
import com.liuyihui.platform.entity.User;
import com.liuyihui.platform.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 登录controller
 *
 * @author liuyi
 */
@SessionAttributes("User")
@Controller(value = "loginController")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 返回登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/doLogin")
    public @ResponseBody
    ApiResponse doLogin(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            ModelMap modelMap) {

        //定义结果
        ApiResponse apiResponse = new ApiResponse();

        //通过session中有没有user,判断是否已登录
        /*if (user.getUserName() != null) {
            apiResponse.setCode(0);
            apiResponse.setMessage("已登录过");
            System.out.println(user);
            return apiResponse;
        }*/
        if (modelMap.containsAttribute("User")) {
            User user = (User) modelMap.get("User");
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
            modelMap.addAttribute("User", user);
        }

        return apiResponse;
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {
        if (model.containsAttribute("User")) {
            System.out.println("model Contains User");
            User user = (User) model.asMap().get("User");
            System.out.println(user);
        }

        if (session.getAttribute("User") != null) {
            session.removeAttribute("User");
            System.out.println("移除Session中的User");
        }
        return "home";
    }

}
