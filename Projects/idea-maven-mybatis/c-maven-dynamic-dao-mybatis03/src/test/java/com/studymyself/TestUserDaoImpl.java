package com.studymyself;

import com.studymyself.dao.UserDao;
import com.studymyself.entity.User;
import com.studymyself.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestUserDaoImpl {

    /**
     * 测试接口中的getAll方法
     */
    @Test
    public void testGetAll(){

        //获取SqlSession对象
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        //通过SqlSession对象中的方法获取实现类对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //调用查询方法，返回list集合
        List<User> users = userDao.getAll();
        //遍历集合
        for (User user:
             users) {
            System.out.println(user);
        }
    }

    /**
     * 测试接口中添加数据的方法
     */
    @Test
    public void testAddUser(){

        //获取SqlSession对象
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        //通过SqlSession对象中的方法获取实现类对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //创建User对象，赋值
        User user = new User();
        user.setLoginName("10086");
        user.setLoginPwd("10086zzz");
        user.setRealName("中国移动");
        //调用添加方法，返回新增的记录条数
        int count = userDao.addUser(user);
        //提交事务
        sqlSession.commit();
        System.out.println("新增记录条数"+count);

    }

    @Test
    public void testSelectById(){

        //获取SqlSession对象
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        //通过SqlSession对象中的方法获取实现类对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.selectById(1);
        System.out.println(user);

    }
}
