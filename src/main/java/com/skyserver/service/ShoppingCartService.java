package com.skyserver.service;

import com.skypojo.dto.ShoppingCartDTO;
import com.skypojo.entity.ShoppingCart;

import java.util.List;

/**
 * @ClassName ShoppingCartService
 * @Author shuai
 * @create 2023/8/5 20:14
 * @Instruction
 */
public interface ShoppingCartService {
    void add(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> list();

    void clean();

    void sub(ShoppingCartDTO shoppingCartDTO);
}
