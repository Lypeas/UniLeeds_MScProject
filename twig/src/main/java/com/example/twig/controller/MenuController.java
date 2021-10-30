package com.example.twig.controller;

import com.example.twig.mapper.OrderMapper;
import com.example.twig.pojo.Order;
import com.example.twig.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    OrderMapper orderMapper;

    @RequestMapping("/toHome")
    String toHome(){return "index";}

    @RequestMapping("/toOrderHistory")
    String toOrderHistory(HttpSession session){

        User user = (User)session.getAttribute("userInfo");
        String userId = user.getUserId();
        List<Order> orderList = orderMapper.findAllOrder(userId);

        for(int i=0;i<orderList.size();i++){
            String orderStatus = orderList.get(i).getOrderStatus();
            String completeTime = orderList.get(i).getCompleteTime();
            if(orderStatus.equals("0")){
                orderStatus = "Customer has booked";
            }else if(orderStatus.equals("1")){
                orderStatus = "Merchant has confirmed";
            }else if(orderStatus.equals("2")){
                orderStatus = "Merchant has completed the service";
            }else{
                orderStatus = "Order completed";
            }
            orderList.get(i).setOrderStatus(orderStatus);

            if(completeTime.equals("")){
                completeTime = "Incomplete!";
            }
            orderList.get(i).setCompleteTime(completeTime);
        }


        session.setAttribute("orderList", orderList);

        return "order-history";

    }
}
