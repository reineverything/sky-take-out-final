package com.skyserver.controller.admin;

import com.skycommon.result.PageResult;
import com.skycommon.result.Result;
import com.skypojo.dto.DishDTO;
import com.skypojo.dto.DishPageQueryDTO;
import com.skypojo.entity.Dish;
import com.skypojo.vo.DishVO;
import com.skyserver.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName DishController
 * @Author shuai
 * @create 2023/8/1 12:24
 * @Instruction
 */

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;


    /**
     * 新增菜品
     * @return
     */
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}",dishDTO);
        dishService.save(dishDTO);
        return Result.success();
    }

    /**
     * 菜品分页条件查询
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){//当接受参数放在请求路径中，不需要@RequestBody
        log.info("查询请求：{}",dishPageQueryDTO);
        PageResult pageResult=dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 修改状态信息
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result status(@PathVariable Integer status,Long id){
        log.info("修改菜品{}，状态{}",id,status);
        dishService.status(status,id);
        return Result.success();
    }


    /**
     * 删除id菜品
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){//？后的数据，默认封装在数组中，@RequestParam接受
        log.info("删除菜品；{}",ids);
        dishService.delete(ids);
        return Result.success();
    }


    /**
     * 修改前信息回显
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<DishVO> getInfo(@PathVariable Long id){
        log.info("返回菜品id：{}",id);
        DishVO dishVO=dishService.get(id);
        return Result.success(dishVO);
    }


    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品为：{}",dishDTO);
        dishService.update(dishDTO);
        return Result.success();
    }

    /**
     * 根据分类id查询菜品
     */
    @GetMapping("/list")
    public Result<List<DishVO>> list(Long categoryId){
        log.info("查询分类id为{}的菜品",categoryId);
        List<DishVO> dishVOS = dishService.getByCategoryId(categoryId);
        return Result.success(dishVOS);
    }

}
