package com.skyserver.controller.admin;

import com.skycommon.result.PageResult;
import com.skycommon.result.Result;
import com.skypojo.dto.SetmealDTO;
import com.skypojo.dto.SetmealPageQueryDTO;
import com.skypojo.vo.SetmealVO;
import com.skyserver.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @ClassName SetmealController
 * @Author shuai
 * @create 2023/8/3 16:10
 * @Instruction 管理端套餐接口
 */

@RestController("adminSetmealController")
@Slf4j
@RequestMapping("/admin/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 条件分页查询
     */
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("套餐分页查询：{}",setmealPageQueryDTO);
        PageResult pageResult=setmealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 新建套餐
     */
    @PostMapping
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("要新建的套餐是：{}",setmealDTO);
        setmealService.save(setmealDTO);
        return Result.success();
    }

    /**
     * 根据id查询套餐信息，修改前页面回显
     */
    @GetMapping("/{id}")
    public Result<SetmealVO> getInfo(@PathVariable Long id){
        log.info("id:{}",id);
        SetmealVO setmealVO=setmealService.getById(id);
        return Result.success(setmealVO);
    }

    /**
     * 修改套餐状态
     */
    @PostMapping("/status/{status}")
    public Result status(@PathVariable Integer status, Long id){
        log.info("修改套餐id:{},状态：{}",id,status);
        setmealService.status(status,id);
        return Result.success();
    }

    /**
     * 删除套餐
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        log.info("删除套餐：{}",ids);
        setmealService.delete(ids);
        return Result.success();
    }

    /**
     * 修改套餐
     */
    @PutMapping
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改成：{}",setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }
}
