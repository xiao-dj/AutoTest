package com.muke.entity;

import lombok.Data;

import java.util.Date;


@Data
public class UserInfo {

    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private Date create_time;
    private Date update_time;
}
