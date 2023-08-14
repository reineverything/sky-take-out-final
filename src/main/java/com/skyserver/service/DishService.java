package com.skyserver.service;

import com.skycommon.result.PageResult;
import com.skypojo.dto.DishDTO;
import com.skypojo.dto.DishPageQueryDTO;
import com.skypojo.vo.DishVO;

import java.util.List;

/**
 * @ClassName DishService
 * @Author shuai
 * @create 2023/8/1 12:22
 * @Instruction
 */
public interface DishService {
    void save(DishDTO dishDTO);

    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    void status(Integer status, Long id);

    void delete(List<Long> ids);

    DishVO get(Long id);

    void update(DishDTO dishDTO);

    List<DishVO> getByCategoryId(Long categoryId);
}
