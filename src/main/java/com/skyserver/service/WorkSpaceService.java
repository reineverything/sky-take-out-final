package com.skyserver.service;

import com.skypojo.vo.BusinessDataVO;
import com.skypojo.vo.DishOverViewVO;
import com.skypojo.vo.OrdersOverViewVO;
import com.skypojo.vo.SetmealOverViewVO;

import java.time.LocalDateTime;

/**
 * @ClassName WorkSpaceService
 * @Author shuai
 * @create 2023/8/14 14:57
 * @Instruction
 */
public interface WorkSpaceService {
    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);

    SetmealOverViewVO getSetmealOverView();

    DishOverViewVO getDishOverView();

    OrdersOverViewVO getOrderOverView();
}
