package com.muke.testng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamterTest {

    @Test
    @Parameters({"name","age"})
    public void paramTest1(String name, int age){
        System.out.println();
        System.out.print("name: " + name + "\n" + "age: " +age);
    }
}
