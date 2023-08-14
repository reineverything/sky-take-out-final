package com.skyserver.mapper;

import com.skypojo.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName OrderDetailMapper
 * @Author shuai
 * @create 2023/8/6 10:41
 * @Instruction
 */
@Mapper
public interface OrderDetailMapper {
    void insertBatch(List<OrderDetail> orderDetailList);

    List<OrderDetail> getByOrderId(Long orderId);

}
