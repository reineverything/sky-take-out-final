package com.skyserver.mapper;

import com.skycommon.enumeration.OperationType;
import com.skypojo.dto.CategoryPageQueryDTO;
import com.skypojo.entity.Category;
import com.skyserver.annotation.AutoFill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName CategoryMapper
 * @Author shuai
 * @create 2023/7/31 9:34
 * @Instruction 分类管理数据访问层
 */

@Mapper
public interface CategoryMapper {
    List<Category> list(CategoryPageQueryDTO categoryPageQueryDTO);

    @AutoFill(OperationType.INSERT)
    void insert(Category category);

    @AutoFill(OperationType.UPDATE)
    void update(Category category);

    void delete(long id);

    List<Category> selectByType(Integer type);

    List<Category> listAll();

    Category getById(Long categoryId);
}
