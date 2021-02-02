package com.mybatis.test;

import com.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest01 {

    public static void main(String[] args) {

        SqlSession sqlSession = null;
        List<User> userList = null;

        try {

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //执行下面语句，事务自动提交就关闭了，等同于conn.setAutoCommit(false);
            //然后开启事务
            //SqlSession对象等同看作Connection，是专门用来执行SQL语句的对象
            sqlSession = sqlSessionFactory.openSession();

            //do work(执行核心业务逻辑)
            //获取所有用户信息，返回List集合，集合中存储User
            //sqlSession.selectList()中填的是sql映射文件某条sql语句的唯一标识id的字符串
            userList = sqlSession.selectList("getAll");

            //若没有出现异常，事务结束，提交
            sqlSession.commit();

        } catch (IOException e) {
            //遇到异常，回滚事务,所以sqlSession对象要在try catch外面定义
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        }finally {
            //关闭资源
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        for (User user:
             userList) {
            System.out.println(user);
        }

    }

}
