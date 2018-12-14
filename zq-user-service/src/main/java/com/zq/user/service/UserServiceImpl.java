package com.zq.user.service;

import com.zq.user.dao.UserDao;
import com.zq.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public String selectByPassword(String loginName,String password) {
        User user =userDao.selectByLoginName(loginName);
        if(user!=null && user.getPassword().equals(password)){
            return user.getId();
        }
        return "";

    }

    @Override
    public void selectByPhoneNo(String phoneNo) {
        User user = userDao.selectByPhoneNo(phoneNo);
        if(user!=null){
            //生成并发送验证码
            String VerificationCode = randString();
        }
    }

    @Override
    public Boolean loginByVcode(String vCode) {
        String verificationCode = "";
        if(vCode.equals(verificationCode)){
            return true;
        }
        return false;
    }


    //产生6位随机字符串作为验证码
    public static String randString() {
         Random r = new Random();
         String ssource = "0123456789";
         char[] src = ssource.toCharArray();
         char[] buf = new char[6];
        int rnd;
        for (int i = 0; i < buf.length; i++) {
            rnd = Math.abs(r.nextInt()) % src.length;
            buf[i] = src[rnd];
        }
        return new String(buf);
    }

    @Override
    public User getUserInfo(String loginUser) {
        return userDao.getUserInfo(loginUser);
    }
}
