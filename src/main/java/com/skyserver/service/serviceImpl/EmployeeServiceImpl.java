package com.skyserver.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skycommon.constant.MessageConstant;
import com.skycommon.constant.PasswordConstant;
import com.skycommon.constant.StatusConstant;
import com.skycommon.exception.BusinessException;
import com.skycommon.exception.DataException;
import com.skycommon.result.PageResult;
import com.skypojo.dto.EmployeeDTO;
import com.skypojo.dto.EmployeeLoginDTO;
import com.skypojo.dto.EmployeePageQueryDTO;
import com.skypojo.entity.Employee;
import com.skyserver.mapper.EmployeeMapper;
import com.skyserver.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName EmployeeServiceImpl
 * @Author shuai
 * @create 2023/7/28 21:04
 * @Instruction
 */

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    //定义两个常量，保存错误时和被锁定时的标识
    private final String LOGIN_ERROR_KEY="login";
    private final String LOCK_ERROR_KEY="lock";

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {

        //校验员工账号是否被锁定
        if(redisTemplate.hasKey(LOCK_ERROR_KEY+employeeLoginDTO.getUsername())){
            //说明限制还没有解除
            throw new BusinessException(MessageConstant.LOGIN_LOCK_ERROR);
        }


        //1。调用mapper，查询数据
        Employee employee = employeeMapper.findByUsername(employeeLoginDTO.getUsername());

        //2。判断员工是否存在，不存在返回错误信息
        if(employee==null){
            log.info("查询到员工信息为空，返回错误信息");
            throw new DataException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //3。校验密码正确性
        //密码加密
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(employeeLoginDTO.getPassword().getBytes());//将用户输入的密码通过md5加密
        if(!md5DigestAsHex.equals(employee.getPassword())){
            log.info("输入密码错误，返回错误信息");

            //业务需求：5分钟内，密码输错5次，1小时内账号禁止登录
            //3.1 记录密码错误标记，有效期设置5分钟，要求标记不能重复，这里用时间来标识
            Calendar calendar = Calendar.getInstance();
            String now=new SimpleDateFormat("HHmmss").format(calendar.getTime());
            String key=LOGIN_ERROR_KEY+ employeeLoginDTO.getUsername()+now;
            redisTemplate.opsForValue().set(key,"-",5, TimeUnit.MINUTES);


            //3.2 获取该员工密码错误标记，如果标记数量>=5，设置账号锁定标记
            int size = redisTemplate.keys(LOGIN_ERROR_KEY + employeeLoginDTO.getUsername() + "*").size();
            if(size>=5){
                log.info("5分钟内输错5次密码，锁定账号1小时");
                redisTemplate.opsForValue().set(LOCK_ERROR_KEY+employeeLoginDTO.getUsername(),"-",1,TimeUnit.HOURS);
                throw new BusinessException(MessageConstant.LOGIN_LOCK_ERROR);
            }
            throw new BusinessException(MessageConstant.PASSWORD_ERROR);
        }

        //4。判断员工status，禁用则返回错误信息
        if(employee.getStatus()== StatusConstant.DISABLE){
            log.info("用户{}账号被禁用中，禁止登录",employeeLoginDTO.getUsername());
            throw new BusinessException(MessageConstant.ACCOUNT_LOCKED);
        }
        return employee;
    }

    @Override
    public void save(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);//快速将公共属性赋值
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        employee.setStatus(StatusConstant.ENABLE);
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());

        //需要更新为当前登录用户
        //可以通过获取请求头中的id！！!，取用ThreadLocal中的数据
//        employee.setCreateUser((long) (int) ThreadLocalUtil.get());
//        employee.setUpdateUser((long) (int) ThreadLocalUtil.get());
        employeeMapper.insert(employee);
    }


    /**
     *  以前只写过mp的分页查询，这次使用PageHelper进行分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @Override
    public PageResult page(EmployeePageQueryDTO employeePageQueryDTO) {

        //1。设置分页参数
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        //2.执行条件查询
        List<Employee> employeelist= employeeMapper.list(employeePageQueryDTO.getName());

        //3.解析封装结果
        Page<Employee> page = (Page<Employee>) employeelist;//将查询的list强转成page类型

        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public void changeStatus(int status, long id) {
        Employee employee = Employee.builder()
                .id(id)
                .status(status)
//                .updateTime(LocalDateTime.now())
//                .updateUser((long) (int) ThreadLocalUtil.get())
                .build();

        employeeMapper.update(employee);
    }

    @Override
    public EmployeeDTO getById(Long id) {
        Employee employee=employeeMapper.getById(id);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee,employeeDTO);
        return employeeDTO;
    }

}
