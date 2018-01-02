package com.liuyihui.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * 控制器学习示例二：
 * <p>
 * 下列方法全是提取HTTP请求项目的示范：
 *
 * @author liuyh
 */
@Controller
@RequestMapping("/controller2")
public class DemoController2 {

    /**
     * 入参可以随意使用HttpServletRequest和HttpServletResponse
     * <p>
     * 用request对象获取请求的头、内容。
     *
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/request2", method = RequestMethod.POST)
    public @ResponseBody
    String request2(HttpServletRequest request) throws IOException {

        //获取内容区，并打印出来
        InputStream is = request.getInputStream();
        byte[] bytes = new byte[request.getContentLength()];
        is.read(bytes);
        String contentJson = new String(bytes, request.getCharacterEncoding());
        System.out.println(contentJson);

        //返回
        return "success";
    }

}
