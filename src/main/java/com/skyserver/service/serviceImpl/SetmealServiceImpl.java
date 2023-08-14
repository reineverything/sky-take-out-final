package com.skyserver.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skycommon.constant.MessageConstant;
import com.skycommon.exception.BusinessException;
import com.skycommon.result.PageResult;
import com.skypojo.dto.SetmealDTO;
import com.skypojo.dto.SetmealPageQueryDTO;
import com.skypojo.entity.Category;
import com.skypojo.entity.Setmeal;
import com.skypojo.entity.SetmealDish;
import com.skypojo.vo.SetmealDishVO;
import com.skypojo.vo.SetmealVO;
import com.skyserver.mapper.CategoryMapper;
import com.skyserver.mapper.SetmealDishMapper;
import com.skyserver.mapper.SetmealMapper;
import com.skyserver.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ConnectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SetmealServiceImpl
 * @Author shuai
 * @create 2023/8/3 16:13
 * @Instruction
 */

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    public PageResult page(SetmealPageQueryDTO setmealPageQueryDTO) {
        /**
         * 根据name,categoryId,status条件查询
         */
        //1设置分页参数
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());

        //2查询数据库
        List<SetmealVO> setmealVO=setmealMapper.list(setmealPageQueryDTO);
        Page page=(Page) setmealVO;


        //3返回数据
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Transactional
    @Override
    public void save(SetmealDTO setmealDTO) {

        /**
         * 新增套餐，并且将status设置为0
         */

        //新增套餐
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmeal.setStatus(0);
        setmealMapper.save(setmeal);

        //主键返回设置套餐id，插入套餐菜品关联表(批量插入)
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        for (SetmealDish setmealDish:setmealDishes
             ) {
            setmealDish.setSetmealId(setmeal.getId());
        }
        setmealDishMapper.save(setmealDishes);
    }

    @Override
    public SetmealVO getById(Long id) {

        /**
         * 根据id查询套餐信息
         */
        //查询setmeal表
        SetmealVO setmealVO = new SetmealVO();//需要categoryName
        Setmeal setmeal=setmealMapper.getById(id);
        BeanUtils.copyProperties(setmeal,setmealVO);
        Category category=categoryMapper.getById(setmeal.getCategoryId());
        setmealVO.setCategoryName(category.getName());

        //根据id获取对应套餐的菜品
        List<SetmealDish> setmealDishList=setmealDishMapper.getBySetmealId(id);
        setmealVO.setSetmealDishes(setmealDishList);
        return setmealVO;
    }

    @Override
    public void status(Integer status,Long id) {
        /**
         * 将id套餐的status改成status
         */
        Setmeal setmeal = Setmeal.builder()
                .id(id)
                .status(status)
                .build();
        setmealMapper.update(setmeal);
    }

    @Override
    public void delete(List<Long> ids) {
        /**
         * 删除多个套餐
         * 在售状态不能删除
         */
        //判断是否存在在售的套餐
        int count=setmealMapper.countOnSale(ids);

        if(count>0){
            throw new BusinessException(MessageConstant.SETMEAL_ON_SALE);
        }
        //删除套餐
        setmealMapper.delete(ids);

        //批量删除对应菜品
        setmealDishMapper.deleteBySetmealId(ids);
    }

    @Override
    @Transactional
    public void update(SetmealDTO setmealDTO) {
        /**
         * 修改套餐和对应的菜品
         */
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmealMapper.update(setmeal);

        //删除套餐对应的菜品
        List<Long> ids=new ArrayList<>();
        ids.add(setmealDTO.getId());
        setmealDishMapper.deleteBySetmealId(ids);

        //将dto中的菜品添加到关联表
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        for (SetmealDish setmealDish: setmealDishes
             ) {
            setmealDish.setSetmealId(setmealDTO.getId());
        }
        setmealDishMapper.save(setmealDishes);
    }

    @Override
    public List<Setmeal> getByCategoryId(Long categoryId) {
        /**
         * 根据分类查询对应的套餐，并且status=1
         */
        return setmealMapper.getByCategoryId(categoryId);
    }

    @Override
    public List<SetmealDishVO> getDishById(Long id) {

        return setmealDishMapper.getUserDishBySetmealId(id);//包含name，copies，image，description

    }
}
