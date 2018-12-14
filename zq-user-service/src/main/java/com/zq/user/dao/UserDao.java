package com.zq.user.dao;

import com.zq.user.entity.User;

public interface UserDao {

    User selectByLoginName(String loginName);
    User selectByPhoneNo(String phoneNo);

    User getUserInfo(String loginUser);
}
