package com.muke.test;

import com.muke.dao.UserDao;
import com.muke.entity.User;
import com.muke.entity.UserInfo;
import com.muke.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class UserDaoTest {

    @Test
    public  void testUserInfoDao () throws IOException {

//        String config = "mybatis_config.xml";
//
//        InputStream in = Resources.getResourceAsStream(config);
//
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
//
//        SqlSession sqlSession = factory.openSession();

        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        UserDao dao = sqlSession.getMapper(UserDao.class);

        List<UserInfo> users = dao.selectUserInfoByEmail("18012121212");

        for (UserInfo user : users
             ) {
            System.out.println(user);

        }

        sqlSession.close();


    }


    @Test
    public  void testUserDao () throws IOException {

//        String config = "mybatis_config.xml";
//
//        InputStream in = Resources.getResourceAsStream(config);
//
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
//
//        SqlSession sqlSession = factory.openSession();

        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        UserDao dao = sqlSession.getMapper(UserDao.class);


            User user = new User();

            user.setEmail("liuh@163.com");
            user.setPassword("123456");

//        if(!user.getEmail().equals(null) || !user.getEmail().equals(' ')){
//            if(!user.getPassword().equals(null) || !user.getPassword().equals(' ')){
//
//            }
//
//        }

        try {
            Boolean aBoolean = dao.insertUserByEmail(user);

            sqlSession.commit();


            if (aBoolean) {
                System.out.println("注册成功 请返回登陆页面登陆");
            }
        }catch (Exception e){
            sqlSession.rollback();
            System.out.println("注册失败");
        }
//
//        if(!aBoolean){
//            //System.out.println("注册成功 请返回登陆页面登陆");
//            System.out.println("注册失败");
//        }
//
//        if(aBoolean){
//            System.out.println("注册成功 请返回登陆页面登陆");
//        }


        sqlSession.close();


    }


}
