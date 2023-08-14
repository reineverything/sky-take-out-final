package com.skyserver.mapper;

import com.skypojo.dto.GoodSalesDTO;
import com.skypojo.dto.OrdersPageQueryDTO;
import com.skypojo.dto.OrdersReportDTO;
import com.skypojo.dto.TurnoverReportDTO;
import com.skypojo.entity.Orders;
import com.skypojo.vo.OrdersVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName OrdersMapper
 * @Author shuai
 * @create 2023/8/6 10:40
 * @Instruction
 */
@Mapper
public interface OrdersMapper {

    void insert(Orders orders);

    List<OrdersVO> page(OrdersPageQueryDTO ordersPageQueryDTO);

    List<Orders> list();

    Orders getById(Long id);

    void update(Orders orders);

    @Select("select * from orders where status =#{status} and order_time<#{before}")
    List<Orders> selectByStatusAndLtTime(Integer status, LocalDateTime before);

    Orders getByOrderNumber(String orderNumber);

    List<TurnoverReportDTO> selectTurnoverList(LocalDateTime beginTime, LocalDateTime endTime, Integer completed);

    List<OrdersReportDTO> selectOrderList(LocalDateTime beginTime, LocalDateTime endTime, Integer status);

    List<GoodSalesDTO> selectTop10(LocalDateTime beginTime, LocalDateTime endTime);

    Double selectTurnover(LocalDateTime beginTime, LocalDateTime endTime);

    Integer selectOrderCount(LocalDateTime begin, LocalDateTime end, Integer status);

    Integer countByStatus(Integer status);
}
