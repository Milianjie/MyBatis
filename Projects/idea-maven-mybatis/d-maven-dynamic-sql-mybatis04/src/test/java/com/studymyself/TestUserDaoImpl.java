package com.studymyself;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.studymyself.dao.UserDao;
import com.studymyself.entity.User;
import com.studymyself.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestUserDaoImpl {

    @Test
    public void testSelectByIf(){

        //获取SqlSession对象
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        //通过SqlSession对象中的方法获取实现类对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(1);
        user.setRealName("钟荣杰");
        List<User> users = userDao.selectByIf(user);
        for (User user1:
             users) {
            System.out.println(user1);
        }

    }

    @Test
    public void testSelectByWhere(){

        //获取SqlSession对象
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        //通过SqlSession对象中的方法获取实现类对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
//        user.setId(1);
//        user.setRealName("钟荣杰");
        List<User> users = userDao.selectByWhere(user);
        for (User user1:
                users) {
            System.out.println(user1);
        }

    }

    @Test
    public void testSelectForPageHelper(){

        //获取SqlSession对象
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        //通过SqlSession对象中的方法获取实现类对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        //设置查询的页码和每页的记录条数
        PageHelper.startPage(2,3);

        //上面设置后存到list集合的就只有三条数据了
        List<User> users = userDao.selectForPageHelper();

        for (User user:
             users) {
            System.out.println(user);
        }

    }

}
