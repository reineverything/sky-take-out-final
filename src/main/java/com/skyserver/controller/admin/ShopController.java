package com.skyserver.controller.admin;

import com.skycommon.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

/**
 * @ClassName ShopController
 * @Author shuai
 * @create 2023/8/2 21:49
 * @Instruction 店铺状态设置
 */

@RestController
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {

    public static final String SHOP_STATUS_KEY="SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 修改店铺状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        log.info("要修改成状态：{}",status);
        redisTemplate.opsForValue().set(SHOP_STATUS_KEY,status);
        return Result.success();
    }


    @GetMapping("/status")
    public Result getStatus(){
        log.info("获取店铺状态信息");
        Integer status = (Integer)redisTemplate.opsForValue().get(SHOP_STATUS_KEY);
        return Result.success(status);
    }
}
