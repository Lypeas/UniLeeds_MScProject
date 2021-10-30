package com.example.twig.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private String id;
    private String title;
    private String contractType;
    private String typeDetail;
    private String address;
    private String openTime;
    private String closeTime;
    private String contentPic0;
    private String contentPic1;
    private String contentPic2;
    private String contentWord;
    private String remark1; //user_id 用户表主键 不为空
    private String remark2; //服务预估价格 不为空
    private String remark3; //上门或配送服务费用 不为空
    private String remark4;
}
