package com.skyserver.mapper;

import com.skypojo.entity.Dish;
import com.skypojo.entity.SetmealDish;
import com.skypojo.vo.SetmealDishVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName Set
 * @Author shuai
 * @create 2023/8/2 15:01
 * @Instruction
 */

@Mapper
public interface SetmealDishMapper {

    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    void save(List<SetmealDish> setmealDishes);

    List<SetmealDish> getBySetmealId(Long id);

    void deleteBySetmealId(List<Long> ids);

    List<SetmealDishVO> getUserDishBySetmealId(Long id);
}
