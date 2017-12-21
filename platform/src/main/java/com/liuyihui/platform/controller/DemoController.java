package com.liuyihui.platform.controller;

import com.liuyihui.platform.entity.ApiResponse;
import com.liuyihui.platform.entity.Student;
import com.liuyihui.platform.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名上的@sessionAttribute注解和方法参数上的@ModelAttribute注解联合使用,才能实现将session中的user对象注入到参数
 */
@Controller
@RequestMapping(value = "/demo", method = RequestMethod.GET)
public class DemoController {

    @RequestMapping(value = "/getStudent", method = RequestMethod.GET)
    public @ResponseBody
    ApiResponse getData(ModelMap modelMap) {
        ApiResponse apiResponse = new ApiResponse();
        if (!modelMap.containsAttribute("User")) {
            apiResponse.setMessage("未登录");
            return apiResponse;
        }

        User user = (User) modelMap.get("User");
        Student student_xiaoming = new Student();
        student_xiaoming.setId(1);
        student_xiaoming.setAge(28);
        student_xiaoming.setName("小明");
        student_xiaoming.setSex("男");
        student_xiaoming.setGrade(3);
        apiResponse.setCode(0);
        apiResponse.setData(student_xiaoming);
        apiResponse.setMessage("已登录" + user);
        return apiResponse;
    }
}
