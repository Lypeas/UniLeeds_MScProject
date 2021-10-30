package com.example.twig.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Order {
    private String orderId;//订单表主键
    private String serviceId;//下订单的服务ID
    private String userId1;//买家
    private String userId2;//卖家
    private String createTime;//创建该订单的时间
    private String completeTime;//完成该订单的时间
    private String orderStatus;//订单状态：0已经被买家发起，1被商家确认即将上门，2商家确认已经完成服务，3买家最终确认确实完成
    private String orderSuccess;//订单是否成功：0是未成功，1是成功
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
}
