package com.liuyihui.platform.controller;

import com.liuyihui.platform.entity.ApiResponse;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PushMessageController {
    private static final String APP_KEY = "BC-e8b7c7bbd5f5470bae788a6ae0f03ea9";
    private static final String APPLICATION_NAME = "indexMsg";

    @RequestMapping(value = "/pushmsg", method = RequestMethod.GET)
    public @ResponseBody
    ApiResponse pushAMessage(@RequestParam("msg") String message) {
        final ApiResponse apiResponse = new ApiResponse();
        //校验
        if ("".equals(message) || null == message) {
            apiResponse.setMessage("消息不能为空");
            return apiResponse;
        }
        //todo 推送消息
        GoEasy goEasy = new GoEasy(APP_KEY);
        goEasy.publish(APPLICATION_NAME, message, new PublishListener() {
            @Override
            public void onSuccess() {
                super.onSuccess();
                System.out.println("推送成功");
            }

            @Override
            public void onFailed(GoEasyError error) {
                super.onFailed(error);
                System.out.println("推送失败");
                System.out.println(error.getCode() + ":" + error.getContent());
            }
        });

        apiResponse.setCode(0);
        apiResponse.setMessage("已推送");
        return apiResponse;
    }
}
