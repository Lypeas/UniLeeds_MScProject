package com.example.twig.mapper;

import com.example.twig.pojo.Service;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ServiceMapper {

    //根据title查询全部满足条件的服务
    List<Service> findServiceList(@Param("title") String title);

    //查询服务详情
    Service findServiceDetail(@Param("id")String id);

    //查询点赞数
    Service addPaInfo(@Param("id")String id);

    //点赞数加一
    void addPa(Service service);

}
