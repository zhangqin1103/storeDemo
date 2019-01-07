package com.zq.user.service;

import com.zq.user.entity.User;

public interface UserService {

    User  selectByPassword(String loginName,String password);
    void selectByPhoneNo(String phoneNo);
    Boolean loginByVcode(String vCode);
    User getUserInfo(String loginUser);

}
