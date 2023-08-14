package com.skyserver.service.serviceImpl;

import com.skycommon.constant.StatusConstant;
import com.skypojo.dto.OrdersReportDTO;
import com.skypojo.dto.UserReportDTO;
import com.skypojo.entity.Orders;
import com.skypojo.vo.BusinessDataVO;
import com.skypojo.vo.DishOverViewVO;
import com.skypojo.vo.OrdersOverViewVO;
import com.skypojo.vo.SetmealOverViewVO;
import com.skyserver.mapper.DishMapper;
import com.skyserver.mapper.OrdersMapper;
import com.skyserver.mapper.SetmealMapper;
import com.skyserver.mapper.UserMapper;
import com.skyserver.service.WorkSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName WorkSpaceServiceImpl
 * @Author shuai
 * @create 2023/8/14 14:57
 * @Instruction
 */
@Service
public class WorkSpaceServiceImpl implements WorkSpaceService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private DishMapper dishMapper;

    @Override
    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {
        //1.获取begin-end营业额
        Double turnover = ordersMapper.selectTurnover(begin, end);

        //2.获取有效订单数目和全部订单数
        Integer validOrderCount = ordersMapper.selectOrderCount(begin, end, 5);
        Integer totalOrderCount = ordersMapper.selectOrderCount(begin, end, null);

        //3.获取新增用户
        Integer newUser = userMapper.selectUserCount(begin, end);


        //4.计算相关参数。要进行判空操作，否则报错
        Double orderCompletionRate = 0.0;
        Double unitPrice = 0.0;
        if (totalOrderCount != 0) {
            orderCompletionRate = ((double) validOrderCount / totalOrderCount);
        }
        if (validOrderCount != 0) {
            unitPrice = turnover / validOrderCount;
        }

        return new BusinessDataVO(newUser, orderCompletionRate, turnover, unitPrice, validOrderCount);

    }

    @Override
    public SetmealOverViewVO getSetmealOverView() {
        /**
         * 查询套餐停售起售数量
         */
        Integer disableCount = setmealMapper.countByStatus(StatusConstant.DISABLE);
        Integer enableCount = setmealMapper.countByStatus(StatusConstant.ENABLE);
        return new SetmealOverViewVO(disableCount, enableCount);
    }

    @Override
    public DishOverViewVO getDishOverView() {
        /**
         * 查询菜品停售起售数量
         */
        Integer disableCount = dishMapper.countByStatus(StatusConstant.DISABLE);
        Integer enableCount = dishMapper.countByStatus(StatusConstant.ENABLE);
        return new DishOverViewVO(disableCount, enableCount);
    }

    @Override
    public OrdersOverViewVO getOrderOverView() {
        /**
         * 查询订单状态数量
         */
        Integer allOrders = ordersMapper.countByStatus(null);
        Integer cancelledOrders= ordersMapper.countByStatus(Orders.CANCELLED);
        Integer completedOrders = ordersMapper.countByStatus(Orders.COMPLETED);
        Integer deliveredOrders = ordersMapper.countByStatus(Orders.CONFIRMED);
        Integer waitingOrders = ordersMapper.countByStatus(Orders.TO_BE_CONFIRMED);
        return new OrdersOverViewVO(allOrders,cancelledOrders,completedOrders,deliveredOrders,waitingOrders);
    }
}
