package com.skyserver.controller.admin;

import com.skycommon.constant.JwtClaimConstant;
import com.skycommon.result.PageResult;
import com.skycommon.result.Result;
import com.skycommon.utils.JwtUtil;
import com.skypojo.dto.EmployeeDTO;
import com.skypojo.dto.EmployeeLoginDTO;
import com.skypojo.dto.EmployeePageQueryDTO;
import com.skypojo.entity.Employee;
import com.skypojo.vo.EmployeeLoginVO;
import com.skyserver.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @ClassName EmployeeController
 * @Author shuai
 * @create 2023/7/28 20:36
 * @Instruction
 */

@Slf4j
@RestController
@RequestMapping("admin/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Environment environment;

    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登录，{}",employeeLoginDTO);

        //封装结果并返回，生成jwt，jwt中只应该存放唯一标识id
        Employee employee=employeeService.login(employeeLoginDTO);//这里不可能为空，因为空异常早就被捕获了
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimConstant.EMP_ID,employee.getId());
        String token = JwtUtil.createJWT(environment.getProperty("sky.jwt.admin-secret-key"), Long.parseLong(environment.getProperty("sky.jwt.admin-ttl")), claims);

//        EmployeeLoginVO employeeLoginVO = new EmployeeLoginVO(employee.getId(),employee.getUsername(),employee.getName(),token);
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder().id(employee.getId())
                .username(employee.getUsername())
                .name(employee.getName())
                .token(token).build();

        return Result.success(employeeLoginVO);

    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("logout")
    public Result logout(){
        return Result.success();
    }


    /**
     * 添加员工信息
     */
    @PostMapping
    public Result save(@RequestBody EmployeeDTO employeeDTO){
        log.info("添加员工：{}",employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }


    /**
     * 条件分页查询，其中条件不是必须的，page和pageSize必须
     * @param employeePageQueryDTO 封装前端数据
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){//name,page,pageSize
        log.info("条件分页查询：{}",employeePageQueryDTO);

        PageResult pageResult= employeeService.page(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改状态信息，还要修改员工的updateTime,updateUser
     * @param status 需要修改成的状态
     * @param id 员工id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result status(@PathVariable int status, long id){
        log.info("修改员工禁用启用状态：id:{},状态:{}",id,status);
        employeeService.changeStatus(status,id);
        return Result.success();
    }


    /**
     * 修改员工信息前，要将原始信息返回前端
     */
    @GetMapping("/{id}")
    public Result<EmployeeDTO> get(@PathVariable Long id){
        log.info("获取员工id：{}",id);
        EmployeeDTO employeeDTO=employeeService.getById(id);
        return Result.success(employeeDTO);
    }

}
