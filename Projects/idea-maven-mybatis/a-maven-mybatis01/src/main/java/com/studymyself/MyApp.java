package com.studymyself;

import com.studymyself.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class MyApp {

    public static void main(String[] args) {

        SqlSession sqlSession = null;

        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis.xml"));
            sqlSession = sqlSessionFactory.openSession();

            //将UserDao.xml文件中namespace的值放进一个变量中
            String userId = "com.studymyself.dao.UserDao";

            //查询t_user表中的所有数据
            List<User> users = sqlSession.selectList(userId+".getAll");

            for (User user:
                 users) {
                System.out.println(user);
            }

//            //插入数据
//            User user1 = new User();
//            user1.setLoginName("12306");
//            user1.setLoginPwd("sss123456789");
//            user1.setRealName("铁路总局");
//            int count = sqlSession.insert(userId+".addUser",user1);
//            System.out.println("添加到数据库记录条数："+count);

            sqlSession.commit();
        } catch (IOException e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

}
