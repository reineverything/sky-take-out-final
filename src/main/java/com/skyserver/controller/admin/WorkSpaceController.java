package com.skyserver.controller.admin;

import com.skycommon.result.Result;
import com.skypojo.vo.BusinessDataVO;
import com.skypojo.vo.DishOverViewVO;
import com.skypojo.vo.OrdersOverViewVO;
import com.skypojo.vo.SetmealOverViewVO;
import com.skyserver.service.WorkSpaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @ClassName WorkSpaceController
 * @Author shuai
 * @create 2023/8/14 14:36
 * @Instruction 工作台接口
 */
@RestController
@Slf4j
@RequestMapping("/admin/workspace")
public class WorkSpaceController {

    @Autowired
    private WorkSpaceService workSpaceService;

    /**
     * 获取当天概览数据
     * @return
     */
    @GetMapping("/businessData")
    public Result<BusinessDataVO> getBusinessData(){
        log.info("获取当天概览信息");
         //获取当天时间
        LocalDateTime begin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        BusinessDataVO businessDataVO=workSpaceService.getBusinessData(begin,end);
        return Result.success(businessDataVO);
    }


    /**
     * 查询套餐总览
     */
    @GetMapping("/overviewSetmeals")
    public Result<SetmealOverViewVO> overviewSetmeals(){
        log.info("查询当前套餐基本信息");
        SetmealOverViewVO setmealOverViewVO=workSpaceService.getSetmealOverView();
        return Result.success(setmealOverViewVO);
    }

    /**
     * 查询菜品总览
     */
    @GetMapping("/overviewDishes")
    public Result<DishOverViewVO> overviewDishes(){
        log.info("查询当前菜品基本信息");
        DishOverViewVO dishOverViewVO=workSpaceService.getDishOverView();
        return Result.success(dishOverViewVO);
    }

    /**
     * 查询订单信息
     */
    @GetMapping("/overviewOrders")
    public Result<OrdersOverViewVO> overviewOrders(){
        log.info("查询订单基本信息");
        OrdersOverViewVO ordersOverViewVO=workSpaceService.getOrderOverView();
        return Result.success(ordersOverViewVO);
    }

}
