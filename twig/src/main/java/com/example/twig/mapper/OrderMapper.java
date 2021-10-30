package com.example.twig.mapper;

import com.example.twig.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    //生成订单信息
    void insertOrderInfo(Order order);

    //删除订单
    void deleteOrderById(@Param("orderId") String orderId);

    //查看全部的order
    List<Order> findAllOrder(@Param("userId") String userId);
}
