package com.skyserver.service;

import com.skycommon.result.PageResult;
import com.skypojo.dto.SetmealDTO;
import com.skypojo.dto.SetmealPageQueryDTO;
import com.skypojo.entity.Setmeal;
import com.skypojo.entity.SetmealDish;
import com.skypojo.vo.SetmealDishVO;
import com.skypojo.vo.SetmealVO;

import java.util.List;

/**
 * @ClassName SetmealService
 * @Author shuai
 * @create 2023/8/3 16:13
 * @Instruction
 */
public interface SetmealService {
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    void save(SetmealDTO setmealDTO);

    SetmealVO getById(Long id);

    void status(Integer status,Long id);

    void delete(List<Long> ids);

    void update(SetmealDTO setmealDTO);

    List<Setmeal> getByCategoryId(Long categoryId);

    List<SetmealDishVO> getDishById(Long id);
}
