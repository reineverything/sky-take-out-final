package com.skyserver.service;

import com.skycommon.result.PageResult;
import com.skycommon.result.Result;
import com.skypojo.dto.EmployeeDTO;
import com.skypojo.dto.EmployeeLoginDTO;
import com.skypojo.dto.EmployeePageQueryDTO;
import com.skypojo.entity.Employee;
/**
 * @ClassName EmployeeService
 * @Author shuai
 * @create 2023/7/28 21:04
 * @Instruction
 */
public interface EmployeeService {
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    void changeStatus(int status, long id);

    EmployeeDTO getById(Long id);
}
