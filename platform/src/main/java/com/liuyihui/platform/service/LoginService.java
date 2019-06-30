package com.liuyihui.platform.service;

import com.liuyihui.platform.entity.User;
import com.liuyihui.platform.repository.IUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录服务类
 */
@Service("loginService")
public class LoginService {

    @Resource(name = "userMapper")
    private IUser userDAO;//使用resource注解注入实例

    /**
     * 登录服务
     *
     * @param userName
     * @param password
     */
    public User login(String userName, String password) {
        //查询数据
        //返回数据
        User user = userDAO.selectUserByName(userName);
        return user;
    }
}
