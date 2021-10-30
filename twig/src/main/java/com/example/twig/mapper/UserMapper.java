package com.example.twig.mapper;

import com.example.twig.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    //用户登录
    User findUserAccount(@Param("email") String email, @Param("password") String password);
    //用户注册
    void userRegister(User user);
    //用户信息修改
    void updateInfo(User user);
    //查询用户信息通过id
    User findUserInfoById(@Param("userId") String userId);
}
