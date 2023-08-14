package com.skyserver.mapper;

import com.skycommon.enumeration.OperationType;
import com.skypojo.entity.Employee;
import com.skyserver.annotation.AutoFill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName EmployeeMapper
 * @Author shuai
 * @create 2023/7/28 21:09
 * @Instruction
 */

@Mapper
public interface EmployeeMapper {

    Employee findByUsername(String username);

    @AutoFill(OperationType.INSERT)
    void insert(@Param("employee") Employee employee);

    //这里是动态条件查询
    List<Employee> list(String name);

    @AutoFill(OperationType.UPDATE)
    void update(Employee employee);

    Employee getById(Long id);
}

