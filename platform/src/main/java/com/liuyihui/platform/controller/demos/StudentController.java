package com.liuyihui.platform.controller.demos;

import com.liuyihui.platform.entity.ApiResponse;
import com.liuyihui.platform.entity.Student;
import com.liuyihui.platform.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 用来测试学习登录功能
 * <p>
 * 类名上的@sessionAttribute注解和方法参数上的@ModelAttribute注解联合使用,才能实现单向地将session中的user对象注入到user参数
 */
@Controller
@SessionAttributes("user")
@RequestMapping(value = "/student", method = RequestMethod.GET)
public class StudentController {

    /**
     * 自动创建一个user对象,赋值给user参数,并且这个user对像也放到了model中
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/getStudent", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getData(@ModelAttribute("user") User user, HttpSession session) {

        //打印user
        ApiResponse apiResponse = new ApiResponse();

        //校验登录
        if (user.getUserName() == null) {
            apiResponse.setMessage("未登录");
//            System.out.println(modelMap.containsAttribute("jkjfkdj"));
//            System.out.println(modelMap.containsAttribute("user"));
//            System.out.println(modelMap.containsAttribute("User"));
            return apiResponse;
        }


        Student student_xiaoming = new Student();
        student_xiaoming.setId(1);
        student_xiaoming.setAge(28);
        student_xiaoming.setName("小明");
        student_xiaoming.setSex("男");
        student_xiaoming.setGrade(3);
        apiResponse.setCode(0);
        apiResponse.setData(student_xiaoming);
        apiResponse.setMessage("已登录的用户名" + user);
        return apiResponse;
    }
}
