package com.liuyihui.platform.controller;

import com.liuyihui.platform.entity.ApiResponse;
import com.liuyihui.platform.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "test")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/returnJson1")
    public ApiResponse returnJson1() {

        ApiResponse apiResponse = new ApiResponse();
        List<Student> studentList = new ArrayList<>();

        Student student1 = new Student();
        student1.setAge(10);
        student1.setName("pig");
        student1.setId(1);
        student1.setSex("女");
        studentList.add(student1);


        student1 = new Student();
        student1.setAge(30);
        student1.setName("fish");
        student1.setId(2);
        student1.setSex("男");
        studentList.add(student1);

        apiResponse.setData(studentList);

        //
        return apiResponse;
    }
}
