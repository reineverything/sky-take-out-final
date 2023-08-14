package com.skyserver.controller.admin;

import com.skycommon.result.PageResult;
import com.skycommon.result.Result;
import com.skypojo.dto.CategoryDTO;
import com.skypojo.dto.CategoryPageQueryDTO;
import com.skypojo.entity.Category;
import com.skyserver.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CategoryController
 * @Author shuai
 * @create 2023/7/31 9:30
 * @Instruction 分类接口
 */

@RestController("adminCategoryController")
@Slf4j
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){//GetMapping不支持

        log.info("分类分页查询：{}",categoryPageQueryDTO);
        PageResult pageResult=categoryService.page(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody CategoryDTO categoryDTO){
        log.info("添加分类信息：{}",categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    @PutMapping
    public Result update(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类：{}",categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }


    /**
     * 修改分类的启用禁用信息
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result status(@PathVariable int status,long id){

        log.info("修改启用禁用信息：{}，{}",id,status);
        categoryService.enableAndDisable(status,id);
        return Result.success();
    }


    /**
     * 删除菜品
     * @param id 分类id
     * @return
     */
    @DeleteMapping
    public Result delete(Long id){
        log.info("删除菜品：{}",id);
        categoryService.delete(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> list(Integer type){

        log.info("获取类型：{}",type);
        List<Category> categories=categoryService.list(type);
        return Result.success(categories);
    }


}
