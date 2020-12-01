package com.muke.dao;

import com.muke.entity.User;
import com.muke.entity.UserInfo;

import java.util.List;

public interface UserDao {

    public List<UserInfo> selectUserInfoByEmail(String email);

    public Boolean insertUserByEmail(User user);


}
