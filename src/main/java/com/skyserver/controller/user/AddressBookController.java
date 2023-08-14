package com.skyserver.controller.user;

import com.skycommon.result.Result;
import com.skypojo.entity.AddressBook;
import com.skyserver.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AddressBookController
 * @Author shuai
 * @create 2023/8/6 10:52
 * @Instruction 地址接口
 */

@Slf4j
@RestController
@RequestMapping("/user/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增地址
     */
    @PostMapping
    public Result save(@RequestBody AddressBook addressBook){
        log.info("新增地址：{}",addressBook);
        addressBookService.save(addressBook);
        return Result.success();
    }

    /**
     * 查询所有地址信息
     */
    @GetMapping("/list")
    public Result<List<AddressBook>> list(){
        log.info("查询所有地址信息");
        List<AddressBook> addressBookList=addressBookService.list();
        return Result.success(addressBookList);
    }

    /**
     * 查询默认地址
     */
    @GetMapping("/default")
    public Result<AddressBook> getDefault(){
        log.info("查询默认地址信息");
        AddressBook addressBook=addressBookService.getDefault();
        return Result.success(addressBook);
    }

    /**
     * 设置成默认地址
     */
    @PutMapping("/default")
    public Result setDefault(@RequestBody AddressBook addressBook){
        log.info("将{}设置成默认地址",addressBook);
        addressBookService.setDefault(addressBook);
        return Result.success();
    }

    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public Result<AddressBook> getById(@PathVariable Long id){
        log.info("查询id={}的地址",id);
        AddressBook addressBook=addressBookService.getById(id);
        return Result.success(addressBook);
    }

    /**
     * 修改地址
     */
    @PutMapping
    public Result update(@RequestBody AddressBook addressBook){
        log.info("将地址修改成：{}",addressBook);
        addressBookService.update(addressBook);
        return Result.success();
    }

    /**
     * 删除地址
     */
    @DeleteMapping
    public Result delete(Long id){
        log.info("删除地址：{}",id);
        addressBookService.deleteById(id);
        return Result.success();
    }

}
