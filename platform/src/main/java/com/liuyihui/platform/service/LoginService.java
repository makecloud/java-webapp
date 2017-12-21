package com.liuyihui.platform.service;

import com.liuyihui.platform.entity.ApiResponse;
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
    private IUser userDAO;

    /**
     * 登录服务
     *
     * @param userName
     * @param password
     */
    public ApiResponse login(String userName, String password) {
        //定义结果
        ApiResponse apiResponse = new ApiResponse();

        //查询数据
        User user = userDAO.selectUserByName(userName);

        //校验数据
        if (user == null) {
            apiResponse.setMessage("用户不存在");
        } else if (!password.equals(user.getPassword())) {
            apiResponse.setMessage("密码错误");
        } else {
            apiResponse.setCode(0);
        }

        //返回数据
        return apiResponse;
    }
}
