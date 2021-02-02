package com.studymyself.dao.impl;

import com.studymyself.dao.UserDao;
import com.studymyself.entity.User;
import com.studymyself.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getAll() {

        //获取sqlSession对象
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        //mapper文件中某条sql语句的唯一标识
        String selectId = "com.studymyself.dao.UserDao.getAll";
        //执行sql语句
        List<User> users = sqlSession.selectList(selectId);
        //关闭资源
        MyBatisUtil.close(sqlSession);
        //将存储查询结果集映射到User对象的User对象的集合返回
        return users;

    }

    @Override
    public int addUser(User user) {
        //获取sqlSession对象
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //mapper文件中某条sql语句的唯一标识
        String selectId = "com.studymyself.dao.UserDao.addUser";
        //执行sql语句
        int count = sqlSession.insert(selectId,user);
        //提交事务
        sqlSession.commit();
        //关闭资源
        MyBatisUtil.close(sqlSession);
        return count;
    }
}
