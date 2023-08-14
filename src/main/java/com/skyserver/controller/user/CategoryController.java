package com.skyserver.controller.user;

import com.skycommon.result.Result;
import com.skypojo.entity.Category;
import com.skyserver.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CategoryController
 * @Author shuai
 * @create 2023/8/3 14:52
 * @Instruction 用户分类接口
 */

@RestController("userCategoryController")
@Slf4j
@RequestMapping("/user/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 根据type查询分类列表
     * @return
     */
    @GetMapping("/list")
    public Result<List<Category>> listAll(){
        log.info("查询的分类");
        List<Category> categories=categoryService.listAll();
        return Result.success(categories);
    }
}
