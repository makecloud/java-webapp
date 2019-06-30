package com.liuyihui.platform.controller.demos;

import com.liuyihui.platform.entity.Course;
import com.liuyihui.platform.service.CourseService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Conventions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * 控制器类示例：课程控制器
 * 类级别的url映射路径 /course
 * 此类的方法的访问url路径都需先加上类映射路径
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    private static Logger logger = LoggerFactory.getLogger(CourseController.class);
    private CourseService courseService;

    /**
     * 注解@RequestParam使用方法示例：
     * 访问路径：项目名/course/view
     *
     * @RequestParam("courseId") 代表：访问路径后加参数?courseId=3，那么3就赋给方法入参courseId model里的对象将被jsp页面以el表达式形式使用
     * return "view_course" 标识返回名为 view_course.jsp的网页
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewCourse(@RequestParam("courseId") int courseId, Model model) {
        Course c = courseService.getCourseById(courseId);
        model.addAttribute(c);
        return "/view/view_course.jsp";
    }

    /**
     * 注解  @PathVariable 的使用方法法示例：
     * 访问路径：项目名/view/5
     * 则5将赋值给方法的参数courseId
     */
    @RequestMapping(value = "/view/{courseId}")
    public String viewCourse2(@PathVariable int courseId, Model model) {
        Course c = courseService.getCourseById(courseId);
        model.addAttribute(c);
        System.out.println("查看：" + Conventions.getVariableName(c));
        return "/view/view_course.jsp";
    }

    /**
     * 请求映射规则示例：
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")//
    public String viewCourse3() {
        return "course_edit/edit";
    }

    /**
     *
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String doSave(Course course) {

        logger.info("info of course:");
        logger.info(ReflectionToStringBuilder.toString(course));
        //
        course.setCourseId(12);

        return "redirect:view/" + course.getCourseId();
    }

    /**
     *
     */
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String initFileUploadPage() {
        return "upload/uploadpage";
    }

    /**
     * 上传文件示例：
     */
    @RequestMapping(value = "/doUpload")
    public String doUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (!file.isEmpty()) {
            logger.info("upload process:{}", file.getOriginalFilename());
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("C://TEMP//", System.currentTimeMillis() + file.getOriginalFilename()));
            model.addAttribute("success");
            return "/upload/upload_result";
        } else {
            model.addAttribute("fail");
            return "/upload/upload_result";
        }
    }

    /**
     * 返回对象，将对象转成json作为http响应，方法示例：
     */
    @RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
    public @ResponseBody
    Course getCourseInJson(@PathVariable("courseId") int courseId) {
        Course c = new Course();
        c.setCourseId(courseId);
        c.setCourseName("111");
        c.setLevel("8");
        return c;
    }

    /**
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/jsontype/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<Course> getCourseInJson2(@PathVariable("courseId") int courseId) {
        Course c = new Course();
        c.setCourseId(courseId);
        c.setCourseName("111");
        c.setLevel("7389274");
        c.setTitle("Japanes");
        return new ResponseEntity<Course>(c, HttpStatus.OK);
    }


    /**
     * 在setter处注解,实现自动装配couuseService
     *
     * @param courseService
     */
    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }


}
