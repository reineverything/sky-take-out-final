package com.skyserver.mapper;

import com.skypojo.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName DishFlavorMapper
 * @Author shuai
 * @create 2023/8/1 13:50
 * @Instruction
 */

@Mapper
public interface DishFlavorMapper {
    void insertBatch(List<DishFlavor> flavors);

    void deleteByDishIds(List<Long> ids);

    List<DishFlavor> getByDishId(Long id);
}
