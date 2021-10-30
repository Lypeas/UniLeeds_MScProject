package com.example.twig.mapper;

import com.example.twig.pojo.Comment;
import com.example.twig.pojo.Service;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    //通过服务的Id查询全部的评论你
    List<Comment> findAllCommentsByService(@Param("id")String id);

    //通过ID查询当前评论信息
    Comment findCommentById(@Param("commentId") String commentId);

    //更新数据库的喜欢数目
    void addPa(Comment comment);
}
