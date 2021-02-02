package com.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyBatisParameterMapTest01 {

    public static void main(String[] args) {

        SqlSession sqlSession = null;
        try {

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

            sqlSession = sqlSessionFactory.openSession();

            //insert
            //创建Map集合，往集合中添加数据
            Map<String,String> map = new HashMap<>();
            map.put("loginName","6666666@mail.com");
            map.put("loginPwd","8888888");
            map.put("realName","中国大使馆");
            //然后用该集合往sql语句中传值,执行SQL语句
            int count = sqlSession.insert("save",map);
            System.out.println("往数据库中添加了 "+count+" 条数据");

            sqlSession.commit();
        } catch (IOException e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }

}
