package com.skyserver.controller.user;

import com.skycommon.result.PageResult;
import com.skycommon.result.Result;
import com.skypojo.dto.OrdersPageQueryDTO;
import com.skypojo.dto.OrdersPaymentDTO;
import com.skypojo.dto.OrdersSubmitDTO;
import com.skypojo.vo.OrderSubmitVO;
import com.skyserver.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @ClassName OrdersController
 * @Author shuai
 * @create 2023/8/6 10:42
 * @Instruction 订单接口
 */
@Slf4j
@RestController
@RequestMapping("/user/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 提交订单
     * @return
     */
    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        log.info("用户下单：{}",ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO=ordersService.submit(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /**
     * 支付订单
     */
    @PutMapping("/payment")
    public Result payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws IOException {
        log.info("支付订单：{}",ordersPaymentDTO);
        ordersService.pay(ordersPaymentDTO);
        return Result.success();
    }

    /**
     * 查询历史订单
     */
    @GetMapping("/historyOrders")
    public Result<PageResult> page(OrdersPageQueryDTO ordersPageQueryDTO){
        log.info("查询历史订单：{}",ordersPageQueryDTO);
        PageResult page = ordersService.page(ordersPageQueryDTO);
        return Result.success(page);
    }


    /**
     * 再来一单
     */
    @PostMapping("/repetition/{id}")
    public Result repetition(@PathVariable Long id){
        log.info("再来一单：{}",id);

        //todo 再来一单业务逻辑
        ordersService.repetition(id);
        return Result.success();
    }

    /**
     * 催单
     */
    @GetMapping("/reminder/{id}")
    public Result reminder(@PathVariable Long id) throws IOException {
        log.info("{}催单",id);
        ordersService.reminder(id);
        return Result.success();
    }
}
