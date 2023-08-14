package com.skyserver.mapper;

import com.skypojo.dto.ShoppingCartDTO;
import com.skypojo.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName ShoppingCartMapper
 * @Author shuai
 * @create 2023/8/5 20:20
 * @Instruction
 */
@Mapper
public interface ShoppingCartMapper {
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    void updateNumberById(ShoppingCart cart);

    void insert(ShoppingCart shoppingCart);

    List<ShoppingCart> listByUserId(Long currentId);

    void clean(Long currentId);

    ShoppingCart get(ShoppingCartDTO shoppingCartDTO);

    void delete(ShoppingCart shoppingCart);
}
