package com.liuyihui.lrnmybatis4;

import com.liuyihui.lrnmybatis4.api.IUser;
import com.liuyihui.lrnmybatis4.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * mybatis 与spring集成使用 实例！
 *
 * @author liuyh
 */
public class AppTest {
    @Test
    public void test1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-context.xml");
        IUser userMapper = ac.getBean("userMapper", IUser.class);
        User user = userMapper.selectUserByID(2);
        //打印结果
        System.out.println(user.toString());
    }
}
