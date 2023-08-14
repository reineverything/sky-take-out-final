package com.skyserver.controller.user;

import com.skycommon.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ShopController
 * @Author shuai
 * @create 2023/8/3 8:50
 * @Instruction 用户端店铺接口
 */

@RestController("userShopController")//扫描bean默认时驼峰小写，这里需要指定bean的名称不能相同！！！
@Slf4j
@RequestMapping("/user/shop")
public class ShopController {

    public static final String SHOP_STATUS_KEY="SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取店铺状态
     * @return
     */
    @GetMapping("/status")
    public Result getStatus(){
        log.info("查询店铺营业状态");
        Integer status = (Integer) redisTemplate.opsForValue().get(SHOP_STATUS_KEY);
        return Result.success(status);
    }
}
