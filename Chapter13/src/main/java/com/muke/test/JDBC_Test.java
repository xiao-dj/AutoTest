package com.muke.test;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JDBC_Test {

    public static void main(String[] args) {

        Map<String,String> userLoginInfo = initUI();

        boolean loginSuccess = login(userLoginInfo);

        System.out.println(loginSuccess ? "登陆成功" : "登陆失败");

    }

    /**
     * 用户登陆
     * @param userLoginInfo 用户登陆的信息
     * @return  false表示失败，true表示成功
     */
    private static boolean login(Map<String,String> userLoginInfo) {

        boolean loginSuccess = false;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        //1、加载数据库驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2、获取数据库连接
        conn = DriverManager.getConnection("mysql:jdbc://122.51.54.59:3306", "root", "Ying1997.1210");

        //3、获取数据库操作对象
        String sql = "select * from emp where loginName = ? and loginPwd = ?";

        ps = conn.prepareStatement(sql);

        ps.setString(1,userLoginInfo.get("loginName"));
        ps.setString(1,userLoginInfo.get("loginPwd"));

        rs = ps.executeQuery();


        if (rs.next()){
            loginSuccess = true;

        }
        //4、执行sql
        //5、处理结果集
        //6、释放资源
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(rs !=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps !=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn !=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return loginSuccess;
    }

    /**
     * 初始化用户界面
     * @return 用户输入的用户名和密码等登陆信息
     */
    private static Map<String,String> initUI() {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String loginName = sc.next();
        System.out.println("请输入密码：");
        String loginPwd = sc.next();

        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName",loginName);
        userLoginInfo.put("loginPwd",loginPwd);

        return userLoginInfo;
    }

}
