package com.zq.user.controller;

import com.zq.user.entity.User;
import com.zq.user.service.UserService;
import com.zq.user.utils.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/loginByPwd",method = RequestMethod.POST)
    public Response passwordLogin(@RequestBody User user){
        try{
            String loginName = user.getLoginName();
            String password = user.getPassword();
            String userId =  userService.selectByPassword(loginName,password);
            return new Response().success(userId);
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure("登录失败!");
        }
    }
    @RequestMapping("/getVcode")
    public void getVcode(String phoneNo){
        try{
            userService.selectByPhoneNo(phoneNo);
            String VerificationCode = "1234";
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/loginByVcode")
    public Response register(@RequestParam String vCode){
        try{
            Boolean result = userService.loginByVcode(vCode);
            if(result){
                return new Response().success("success");
            }else {
                return new Response().failure("fail");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure("登录失败!");
        }
    }

    /**
     * 获取用户信息
     * @param loginUser
     * @return
     */
    @RequestMapping("/getUserInfo")
    public Response getUserInfo(@RequestParam(value ="loginUser") String loginUser){
        try{
           User user = userService.getUserInfo(loginUser);
           Thread thread = Thread.currentThread();
           //等待5秒再响应数据
            //thread.sleep(5000);
            return new Response().success(user);
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }


}
