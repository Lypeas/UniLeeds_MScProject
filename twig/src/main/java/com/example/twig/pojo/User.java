package com.example.twig.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;//用户表主键
    private String userType;//0管理员，1是商家，2个体户，3普通用户
    private String password;//密码
    private String nickName;//网名
    private String userSkill;//如果是商家和个体户这一栏是主营业务
    private String birthday;//生日
    private String phoneNumber;//电话
    private String email;//邮件
    private String address;//地址
    private String postcode;//邮编
    private String remark1;
    private String remark2;
}
