package com.skyserver.mapper;

import com.skycommon.enumeration.OperationType;
import com.skypojo.dto.DishDTO;
import com.skypojo.dto.DishPageQueryDTO;
import com.skypojo.entity.Dish;
import com.skypojo.vo.DishVO;
import com.skyserver.annotation.AutoFill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * @ClassName DishMapper
 * @Author shuai
 * @create 2023/8/1 12:23
 * @Instruction
 */

@Mapper
public interface DishMapper {

     Long countByCategoryId(long categoryId);

     @AutoFill(OperationType.INSERT)
     @Options(useGeneratedKeys = true,keyProperty = "id")//主键返回注解：插入后将自动生成的主键返回
     void insert(Dish dish);

    List<DishVO> list(DishPageQueryDTO dishPageQueryDTO);

    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    Long countEnableDishByIds(List<Long> ids);

    void deleteByIds(List<Long> ids);

    Dish getById(Long id);

    List<Dish> listByCategoryId(Long categoryId);

    Integer countByStatus(int status);
}
