package com.skyserver.service;

import com.skycommon.result.PageResult;
import com.skypojo.dto.CategoryDTO;
import com.skypojo.dto.CategoryPageQueryDTO;
import com.skypojo.entity.Category;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Author shuai
 * @create 2023/7/31 9:33
 * @Instruction 分类管理业务接口
 */
public interface CategoryService {
    PageResult page(CategoryPageQueryDTO categoryPageQueryDTO);

    void save(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    void enableAndDisable(int status, long id);

    void delete(long categoryId);

    List<Category> list(Integer type);

    List<Category> listAll();
}
