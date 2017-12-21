package com.liuyihui.lrnmybatis3;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import com.liuyihui.lrnmybatis3.api.IUser;
import com.liuyihui.lrnmybatis3.entity.User;




/**
 * mybatis 接口和映射文件搭配使用 增删改查数据库示例：
 * @author liuyh
 */
public class TestMybatisUsage {
	
	/** sqlsession工厂 */
	private  SqlSessionFactory sqlSessionFactory;
	
    /** reader */
    private  Reader reader; 
    
    /**
     *  初始化
     */
    @Before
    public void before(){
    	try{
            reader    = Resources.getResourceAsReader("mybatis-configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
    /**
     * 使用mybatis mapper查询方法示例
     */
    @Test
    public void testQuery() {
    	//获取sqlsession实例
    	SqlSession session = sqlSessionFactory.openSession();
        try {
        	//从sqlsesion根据定义的接口，获取mapper实例
            IUser iUserMapper=session.getMapper(IUser.class);
            //调用mapper的方法，查询数据库
            List<User> users = iUserMapper.queryUser2("w%");
            
            //使用接口跟映射xml文件，就如上两行这么简单。
            
            //打印结果
            for(User user:users){
            	System.out.println(user.getUserName());
            }
        } finally {
            session.close();
        }
    }
    
    /**
     *  使用mybatis mapper插入方法示例
     */
    @Test
    public void testAddUser(){
    	//获取sqlsession
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	try{
    		//创建user
    		User user = new User();
    		user.setUserName("liuyihui");
    		user.setUserAge("22");
    		user.setUserAddress("上地");
    		
    		//从session获取mapper实例
    		IUser userMapper = sqlSession.getMapper(IUser.class);
    		
    		//使用mapper实例的插入
    		userMapper.addUser(user);
    		
    		//提交事务，保证插入
    		sqlSession.commit();
    	}finally{
    		sqlSession.close();
    	}
    }
    /**
     *  使用mybatis mapper的更新方法示例
     */
    @Test
    public void testUpdateUser(){
    	//获取sqlsession
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	try{
    		//创建user
    		User user = new User();
    		user.setId(6);
    		user.setUserName("liuyihui");
    		user.setUserAge("22");
    		user.setUserAddress("上地");
    		
    		//从session获取mapper实例
    		IUser userMapper = sqlSession.getMapper(IUser.class);
    		
    		//使用mapper实例的插入
    		userMapper.updateUser(user);
    		
    		//提交事务，保证更新
    		sqlSession.commit();
    	}finally{
    		sqlSession.close();
    	}
    }
    /**
     *  使用mybatis mapper的删除方法示例
     */
    @Test
    public void testDeleteUser(){
    	//获取sqlsession
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	try{
    		
    		//从session获取mapper实例
    		IUser userMapper = sqlSession.getMapper(IUser.class);
    		
    		//使用mapper实例的删除
    		Integer count = userMapper.deleteUser(6);
    		
    		//提交事务，保证更新
    		sqlSession.commit();
    		
    		//打印结果
    		System.out.println("删除："+count);
    	}finally{
    		sqlSession.close();
    	}
    }
}
