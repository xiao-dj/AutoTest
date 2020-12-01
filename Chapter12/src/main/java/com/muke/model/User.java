package com.muke.model;

import lombok.Data;

@Data
public class User {

    private int goodsid;
    private String goodsName;

    @Override
    public String toString() {
        return "User{" +
                "goodsid=" + goodsid +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }
}
