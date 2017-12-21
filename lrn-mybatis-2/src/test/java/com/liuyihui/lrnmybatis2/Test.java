package com.liuyihui.lrnmybatis2;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.liuyihui.lrnmybatis2.api.IUser;
import com.liuyihui.lrnmybatis2.entity.User;



public class Test {
	
	/** sqlsession工厂 */
	private static SqlSessionFactory sqlSessionFactory;
	
    /** reader */
    private static Reader reader; 
    
    //初始化属性
    static{
        try{
            reader    = Resources.getResourceAsReader("mybatis-configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取session
     * @return 
     */
    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    
    /**
     * 主方法
     * @param args 
     */
    public static void main(String[] args) {
    	//获取sqlsession实例
    	SqlSession session = sqlSessionFactory.openSession();
        try {
        	//从sqlsesion根据定义的接口，获取mapper实例
            IUser iUserMapper=session.getMapper(IUser.class);
            //调用mapper的方法，查询数据库
            User user = iUserMapper.selectUserByID(1);
            
            //使用接口跟映射xml文件，就如上两行这么简单。
            
            //打印结果
            System.out.println(user.getUserAddress());
            System.out.println(user.getUserName());
        } finally {
            session.close();
        }
    }
}
