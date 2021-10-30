package com.example.twig.controller;

import com.example.twig.mapper.UserMapper;
import com.example.twig.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/login")
    String toLoginPage(){
        return "login";
    }

    @RequestMapping("/register")
    String toRegisterPage(){
        return "register";
    }

    @RequestMapping("/toLogin")
    String toLogin(@RequestParam("email") String email,
                    @RequestParam("password") String password,
                   HttpSession session){

        Date date = new Date();
        System.out.println(date);
        //Fri Nov 29 10:05:00 CST 2019

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SS");
        String datef = sdf.format(date);
        System.out.println("Current time："+datef);
        User user = userMapper.findUserAccount(email, password);
        if(user.getUserId()==null&&user.getUserId().equals("")){
            return "login";
        }else{
            if (user.getUserType().equals("1")||user.getUserType().equals("2")||user.getUserType().equals("3")) {
                session.setAttribute("userInfo", user);
                String datef1 = sdf.format(date);
                System.out.println("Current time："+datef1);
                return "index";
            }else{
                session.setAttribute("userInfo", user);
                return "admin-dashboard";
            }
        }
    }

    @RequestMapping("/toRegsiter")
    String toRegsiter(@RequestParam("nick_name") String nickName,
                      @RequestParam("email") String email,
                      @RequestParam("postcode") String postcode,
                      @RequestParam("password") String password,
                      @RequestParam("birthday") String birthday,
                      @RequestParam("phone_number") String phoneNumber,
                      @RequestParam("address") String address)
            {

        User user = new User();
        user.setUserId(UUID.randomUUID().toString().substring(0,15));
        user.setNickName(nickName);
        user.setEmail(email);
        user.setPostcode(postcode);
        user.setPassword(password);
        user.setBirthday(birthday);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setUserType("3");
        userMapper.userRegister(user);

        return "login";
    }

    //跳转个人信息页面
    @RequestMapping("/toUserInfo")
    String toUserInfoPage(){
        return "account-manage";
    }

    @RequestMapping("/toUpdate")
    String toUpdate(@RequestParam("nick_name") String nickName,
                      @RequestParam("email") String email,
                      @RequestParam("postcode") String postcode,
                      @RequestParam("password") String password,
                      @RequestParam("birthday") String birthday,
                      @RequestParam("phone_number") String phoneNumber,
                      @RequestParam("address") String address,
                    HttpSession session)
    {

        User user = new User();
        User userInfo = (User) session.getAttribute("userInfo");

        user.setUserId(userInfo.getUserId());
        user.setNickName(nickName);
        user.setEmail(email);
        user.setPostcode(postcode);
        user.setPassword(password);
        user.setBirthday(birthday);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        userMapper.updateInfo(user);

        session.setAttribute("userInfo",user);

        return "redirect:toUserInfo";
    }
}
