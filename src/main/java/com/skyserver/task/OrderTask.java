package com.skyserver.task;

import com.skypojo.entity.Orders;
import com.skyserver.mapper.OrdersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName OrderTask
 * @Author shuai
 * @create 2023/8/10 10:29
 * @Instruction 订单定时任务
 */
@Slf4j
@Component
public class OrderTask {

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 定时关闭超时未支付订单
     */
    @Scheduled(cron = "0/30 * * * * ?") //每隔30秒执行一次
    public void cancelOrder(){
        //1.查询符合条件订单（待支付，15分钟之前）
        LocalDateTime before = LocalDateTime.now().minusMinutes(15);//拿到15分钟前的时间
        List<Orders> ordersList=ordersMapper.selectByStatusAndLtTime(Orders.PENDING_PAYMENT,before);

        //2.存在这样的订单就取消
        if(!org.springframework.util.CollectionUtils.isEmpty(ordersList)){

            ordersList.stream().forEach(orders -> {
                //取消订单
                log.info("定时取消订单：{}",orders);
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelTime(LocalDateTime.now());
                orders.setCancelReason("支付超时，系统自动取消");
                ordersMapper.update(orders);
            });
        }
    }

    /**
     * 定时完成订单
     */
    @Scheduled(cron = "0 0 1 * * ?")//每天凌晨1点执行一次
    public void completeOrder(){
        //1查询派送中两小时之前下的订单
        LocalDateTime before = LocalDateTime.now().minusHours(2);
        List<Orders> ordersList=ordersMapper.selectByStatusAndLtTime(Orders.DELIVERY_IN_PROGRESS,before);

        //2.存在这样的订单就完成
        if(!org.springframework.util.CollectionUtils.isEmpty(ordersList)){

            ordersList.stream().forEach(orders -> {
                //取消订单
                log.info("定时完成订单：{}",orders);
                orders.setStatus(Orders.COMPLETED);
                orders.setDeliveryTime(LocalDateTime.now());
                ordersMapper.update(orders);
            });
        }
    }
}
