package com.example.twig.controller;

import com.example.twig.mapper.CommentMapper;
import com.example.twig.mapper.OrderMapper;
import com.example.twig.mapper.ServiceMapper;
import com.example.twig.mapper.UserMapper;
import com.example.twig.pojo.Comment;
import com.example.twig.pojo.Order;
import com.example.twig.pojo.Service;
import com.example.twig.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Controller
public class ServiceController {
    @Autowired
    ServiceMapper serviceMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    Order order;
    @Autowired
    OrderMapper orderMapper;

    @RequestMapping("/index")
    String toMainPage(){
        return "merchant-order";
    }

    //Click book now button
    @RequestMapping("/toOrder")
    String toOrderDetail(@RequestParam("userId") String userId, HttpSession session){
        //Merchant information of the service
        User merchantInfo = userMapper.findUserInfoById(userId);
        session.setAttribute("merchantInfo", merchantInfo);
        //Generate order  //order id
        String orderId = UUID.randomUUID().toString().substring(0,15);
        order.setOrderId(orderId);

        //Merchant
        User user = (User)session.getAttribute("merchantInfo");
        String userId2 = user.getUserId();
        order.setUserId2(userId2);
        //Customer
        user = (User)session.getAttribute("userInfo");
        String userId1 = user.getUserId();
        order.setUserId1(userId1);

        //Estimated prices and delivery prices
        Service service = (Service) session.getAttribute("serviceInfo");
        String estimatedPrice = service.getRemark2();
        String deliveryFees = service.getRemark3();
        int estimatedPriceInt = Integer.parseInt(estimatedPrice);
        int deliveryFeesInt = Integer.parseInt(deliveryFees);
        int remark1Int = estimatedPriceInt + deliveryFeesInt;//total price
        String remark1 = remark1Int+"";
        order.setRemark1(remark1);
        order.setServiceId(service.getId());

        //Generate time of placing order
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = tempDate.format(new java.util.Date());
        order.setCreateTime(createDate);
        //Set order status
        order.setOrderStatus("0");
        order.setOrderSuccess("0");

        //set database
        orderMapper.insertOrderInfo(order);

        session.setAttribute("orderInfo", order);
        return "customer-order";
    }

    //跳转到上传服务信息页面
    @RequestMapping("/toUploadService")
    String toUploadSer(){
        return "upload-service";
    }

    //跳转到上传技能信息页面
    @RequestMapping("/toUploadSkill")
    String toUploadSki(){
        return "upload-skill";
    }

    //跳转到contact us页面
    @RequestMapping("/toContactUs")
    String toContactUs(){
        return "contact-us";
    }

    @RequestMapping("/findAllService")
    String findAllService(HttpSession session, @RequestParam("title") String title){

        List<Service> serviceList = serviceMapper.findServiceList(title);
        session.setAttribute("serviceList", serviceList);
        return "service-found";
    }

    @RequestMapping("/toDetail")
    String toDetailPage(@RequestParam("id") String id, HttpSession session){
        //查询服务详情
        Service serviceInfo = serviceMapper.findServiceDetail(id);
        session.setAttribute("serviceInfo",serviceInfo);

        //查询评论
        List<Comment> commentList = commentMapper.findAllCommentsByService(id);
        session.setAttribute("commentList",commentList);


        return "service-detail";
    }

    @RequestMapping("/subPa")
    @ResponseBody
    String subPa(@RequestParam("commentId") String commentId){
   //     Service service = serviceMapper.addPaInfo(id);
        Comment comment = commentMapper.findCommentById(commentId);

        int re = Integer.parseInt(comment.getLikes());
        re = re - 1;
        String reStr = re + "";
        comment.setCommentId(commentId);
        comment.setLikes(reStr);

        //更新数据库的喜欢数目
        commentMapper.addPa(comment);

        //回掉函数
        String msg = "Success";

        String likes = comment.getLikes();

        //Succrss-101
        msg = msg + "-" +likes;

        return msg;
    }

    @RequestMapping("/addPa")
    @ResponseBody
    String addPa(@RequestParam("commentId") String commentId){
        Comment comment = commentMapper.findCommentById(commentId);
        int re = Integer.parseInt(comment.getLikes());
        re = re + 1;
        String reStr = re + "";
        comment.setCommentId(commentId);
        comment.setLikes(reStr);
        //Update the number of likes in the database
        commentMapper.addPa(comment);
        //callback function
        String msg = "Success";
        String likes = comment.getLikes();
        //data type for example "Success-100"
        msg = msg + "-" +likes;
        return msg;
    }
}
