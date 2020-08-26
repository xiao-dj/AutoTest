package com.muke.testng.groups;

import org.testng.annotations.Test;

public class GroupsOnMethod {

    @Test(groups = "server")
    public void test1(){

        System.out.println("服务端组的测试方法111111");

    }

    @Test(groups = "server")
    public void test2(){

        System.out.println("服务端组的测试方法222222");

    }
    @Test(groups = "client")
    public void test3(){

        System.out.println("服务端组的测试方法33333");

    }
    @Test(groups = "client")
    public void test4(){

        System.out.println("服务端组的测试方法4444444");

    }
}
