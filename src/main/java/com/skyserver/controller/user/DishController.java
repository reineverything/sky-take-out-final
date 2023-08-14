package com.skyserver.controller.user;

import com.skycommon.result.Result;
import com.skypojo.vo.DishVO;
import com.skyserver.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName DishController
 * @Author shuai
 * @create 2023/8/3 14:50
 * @Instruction 用户端菜品接口
 */

@RestController("userDishController")
@Slf4j
@RequestMapping("/user/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/list")
    public Result<List<DishVO>> list(Long categoryId){
        log.info("查询的分类是：{}",categoryId);
        List<DishVO> dishVOList=dishService.getByCategoryId(categoryId);
        return Result.success(dishVOList);
    }


}
