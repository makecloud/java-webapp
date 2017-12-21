package com.liuyihui.platform.entity;

/**
 * http响应json数据结构
 *
 * @author liuyi
 */
public class ApiResponse {
    /** 响应码 */
    private int code;
    /** 响应数据 */
    private Object data;
    /** 响应消息 */
    private String message;

    /**
     * 默认构造方法,code默认为9999
     */
    public ApiResponse() {
        this.code = 9999;
    }

    public ApiResponse(int code) {
        this.code = code;
    }

    public ApiResponse(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ApiResponse(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    //getter setter
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
