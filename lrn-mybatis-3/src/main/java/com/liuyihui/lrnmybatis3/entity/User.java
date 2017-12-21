package com.liuyihui.lrnmybatis3.entity;

/**
 * 用户实体类
 * @author liuyh
 */
public class User {
	/** 用户id */
	private int id;
    /** 用户名字 */
    private String userName;
    /** 用户年龄 */
    private String userAge;
    /** 用户住址 */
    private String userAddress;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserAge() {
        return userAge;
    }
    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
    public String getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
