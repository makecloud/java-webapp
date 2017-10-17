package com.liuyihui.platform.controller;

import com.liuyihui.platform.entity.ApiResponse;
import com.liuyihui.platform.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/demo", method = RequestMethod.GET)
public class DemoController {

    @RequestMapping(value = "/getStudent", method = RequestMethod.GET)
    public @ResponseBody
    ApiResponse getData() {
        Student student_xiaoming = new Student();
        student_xiaoming.setId(1);
        student_xiaoming.setAge(28);
        student_xiaoming.setName("小明");
        student_xiaoming.setSex("男");
        student_xiaoming.setGrade(3);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(student_xiaoming);
        return apiResponse;
    }
}
