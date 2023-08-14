package com.skyserver.service;

import com.skycommon.result.PageResult;
import com.skypojo.dto.*;
import com.skypojo.vo.OrderStatisticsVO;
import com.skypojo.vo.OrderSubmitVO;
import com.skypojo.vo.OrdersVO;

import java.io.IOException;

/**
 * @ClassName OrdersService
 * @Author shuai
 * @create 2023/8/6 10:41
 * @Instruction
 */
public interface OrdersService {
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);

    PageResult page(OrdersPageQueryDTO ordersPageQueryDTO);

    OrderStatisticsVO getStatistics();

    OrdersVO getDetail(Long id);

    void cancel(OrdersCancelDTO ordersCancelDTO);

    void reject(OrdersRejectionDTO ordersRejectionDTO);

    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    void delivery(Long id);

    void complete(Long id);

    void pay(OrdersPaymentDTO ordersPaymentDTO) throws IOException;

    void reminder(Long id) throws IOException;

    void repetition(Long id);
}
