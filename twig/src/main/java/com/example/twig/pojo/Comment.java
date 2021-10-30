package com.example.twig.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String commentId;//评论表主键
    private String createTime;//评论的时间
    private String userId;//评论用户的id
    private String commentType;//0是服务下的评论，1是评论下的回复
    private String serviceId;//如果是服务下的评论，是服务的ID
    private String ccId;//如果是评论下的回复，是评论的ID
    private String likes;//该评论的点赞数
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
}
