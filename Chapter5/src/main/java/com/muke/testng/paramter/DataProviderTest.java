package com.muke.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataProviderTest {

    @Test(dataProvider = "data")

    public void testDataProvider(String name, int age){
        System.out.println();
        System.out.println("name" + name + "\n" + "age" + age);
    }

    @DataProvider(name="data")
    public Object[][] providerData(){
        Object[][] o = new Object[][]{
                {"zhangsan",10},
                {"lisi",20},
                {"wangwu",30}
        };
        return o;
    }
}
