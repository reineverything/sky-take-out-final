package com.skyserver.controller.admin;

import com.skycommon.result.PageResult;
import com.skycommon.result.Result;
import com.skypojo.dto.OrdersConfirmDTO;
import com.skypojo.dto.OrdersPageQueryDTO;
import com.skypojo.dto.OrdersCancelDTO;
import com.skypojo.dto.OrdersRejectionDTO;
import com.skypojo.vo.OrderStatisticsVO;
import com.skypojo.vo.OrdersVO;
import com.skyserver.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName OrdersController
 * @Author shuai
 * @create 2023/8/8 10:32
 * @Instruction 管理端订单接口
 */

@RestController("adminOrdersController")
@Slf4j
@RequestMapping("/admin/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 条件分页查询
     */
    @GetMapping("/conditionSearch")
    public Result<PageResult> page(OrdersPageQueryDTO ordersPageQueryDTO){

        log.info("查询订单：{}",ordersPageQueryDTO);
        PageResult pageResult=ordersService.page(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 查询订单相关状态
     */
    @GetMapping("/statistics")
    public Result<OrderStatisticsVO> getStatistic(){
        OrderStatisticsVO orderStatisticsVO=ordersService.getStatistics();
        return Result.success(orderStatisticsVO);
    }

    /**
     * 查询订单详情
     */
    @GetMapping("/details/{id}")
    public Result<OrdersVO> getDetails(@PathVariable Long id){
        log.info("查询的订单id是：{}",id);
        OrdersVO ordersVO=ordersService.getDetail(id);
        return Result.success(ordersVO);
    }


    /**
     * 取消订单
     */
    @PutMapping("/cancel")
    public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO){
        log.info("商家取消订单理由：{}", ordersCancelDTO);
        ordersService.cancel(ordersCancelDTO);
        return Result.success();
    }

    /**
     * 拒绝订单
     */
    @PutMapping("/rejection")
    public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO){
        log.info("商家拒单：{}",ordersRejectionDTO);
        ordersService.reject(ordersRejectionDTO);
        return Result.success();
    }

    /**
     * 接单
     */
    @PutMapping("/confirm")
    public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO){
        log.info("商家接单：{}",ordersConfirmDTO);
        ordersService.confirm(ordersConfirmDTO);
        return Result.success();
    }

    /**
     * 派送订单
     */
    @PutMapping("/delivery/{id}")
    public Result delivery(@PathVariable Long id){
        log.info("派送订单：{}",id);
        ordersService.delivery(id);
        return Result.success();
    }

    /**
     * 完成订单
     */
    @PutMapping("complete/{id}")
    public Result complete(@PathVariable Long id){
        log.info("完成订单：{}",id);
        ordersService.complete(id);
        return Result.success();
    }
}
