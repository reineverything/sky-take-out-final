package com.skyserver.controller.user;

import com.skycommon.result.Result;
import com.skypojo.entity.Setmeal;
import com.skypojo.entity.SetmealDish;
import com.skypojo.vo.SetmealDishVO;
import com.skyserver.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SeatmealController
 * @Author shuai
 * @create 2023/8/3 16:06
 * @Instruction 用户套餐接口
 */

@RestController("userSetmealController")
@Slf4j
@RequestMapping("/user/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据categoryId查询对应套餐列表
     */
    @GetMapping("/list")
    public Result<List<Setmeal>> List(Long categoryId){
        log.info("categoryId:{}",categoryId);
        List<Setmeal> setmeals=setmealService.getByCategoryId(categoryId);
        return Result.success(setmeals);
    }


    /**
     * 根据套餐id查询包含的菜品
     */
    @GetMapping("/dish/{id}")
    public Result<List<SetmealDishVO>> getDishById(@PathVariable Long id){
        log.info("setmealId:{}",id);
        List<SetmealDishVO> setmealDishes=setmealService.getDishById(id);
        return Result.success(setmealDishes);
    }

}
