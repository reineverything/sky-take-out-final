package com.skyserver.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skycommon.constant.MessageConstant;
import com.skycommon.constant.StatusConstant;
import com.skycommon.exception.BusinessException;
import com.skycommon.result.PageResult;
import com.skypojo.dto.DishDTO;
import com.skypojo.dto.DishPageQueryDTO;
import com.skypojo.entity.Dish;
import com.skypojo.entity.DishFlavor;
import com.skypojo.vo.DishVO;
import com.skyserver.mapper.DishFlavorMapper;
import com.skyserver.mapper.DishMapper;
import com.skyserver.mapper.SetmealDishMapper;
import com.skyserver.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName DishServiceImpl
 * @Author shuai
 * @create 2023/8/1 12:23
 * @Instruction
 */

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Transactional//事务注解，同时操作多张表时
    @Override
    public void save(DishDTO dishDTO) {
        //需要对菜品和菜品口味两张表记录
        //1.先保存菜品基本信息，因为口味表对应菜品id
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dish.setStatus(StatusConstant.DISABLE);//新建菜品默认停售
        dishMapper.insert(dish);//主键返回后 dish.id已经被赋值了

        //2.保存菜品口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors.forEach(dishFlavor -> {
            dishFlavor.setDishId(dish.getId());
        });

        dishFlavorMapper.insertBatch(flavors);
        cleanCache(dishDTO.getCategoryId().toString());
    }

    @Override
    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {
        //根据dish的name，categoryId，status等信息，返回
        //查询两张表，菜品和分类名称
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());

        //查询数据库
        List<DishVO> dishVOS=dishMapper.list(dishPageQueryDTO);

        //封装成pageResult类型
        Page<DishVO> page=(Page<DishVO>) dishVOS;
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void status(Integer status, Long id) {
        /**
         * 将id菜品状态改成status
         */
        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();
        dishMapper.update(dish);

        cleanCache("*");

    }

    @Transactional
    @Override
    public void delete(List<Long> ids) {
        /**
         * 在售状态不能删除
         * 被套餐关联不能删除
         * 批量删除id，极其对应的菜品口味
         */
        //判断菜品的状态，不能删除提示错误信息
        Long count=dishMapper.countEnableDishByIds(ids);

        if(count>0){
            //包含起售菜品
            throw new BusinessException(MessageConstant.DISH_ON_SALE);
        }

        //判断菜品是否关联套餐，不能删除，提示错误信息
        List<Long> setmealIds=setmealDishMapper.getSetmealIdsByDishIds(ids);
        if(!CollectionUtils.isEmpty(setmealIds)){
            throw new BusinessException(MessageConstant.Dish_IN_SETMEAL);
        }

        //删除菜品及其口味
        dishMapper.deleteByIds(ids);
        dishFlavorMapper.deleteByDishIds(ids);

        cleanCache("*");
    }

    //清理缓存方法
    private void cleanCache(String suffix) {
        log.info("清理缓存：{}",suffix);
        Set<Object> keys = redisTemplate.keys("dish:cache:"+suffix);//拿到以dish:cache:开头的键
        redisTemplate.delete(keys);

    }

    @Transactional
    @Override
    public DishVO get(Long id) {
        /**
         * 根据dish的id返回dishDTO信息，包括菜品口味
         */
        //先查询基本信息
        Dish dish = dishMapper.getById(id);

        //再查询口味信息
        List<DishFlavor> dishFlavor=dishFlavorMapper.getByDishId(id);

        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish,dishVO);//只有categoryName和flavor没有赋值

        dishVO.setFlavors(dishFlavor);

        //组装信息
        return dishVO;
    }


    @Transactional
    @Override
    public void update(DishDTO dishDTO) {
        //1.修改dish表信息
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.update(dish);

        //2. 修改dish_flavor信息。采用先删后插入
        List<Long> dishId=new ArrayList<>();
        dishId.add(dish.getId());
        dishFlavorMapper.deleteByDishIds(dishId);

        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors.forEach(dishFlavor -> {
            dishFlavor.setDishId(dish.getId());
        });

        dishFlavorMapper.insertBatch(flavors);
        cleanCache("*");


    }

    @Override
    public List<DishVO> getByCategoryId(Long categoryId) {
        /**
         * 根据分类找到该分类下所有菜品的信息包括口味，status=1
         */
        String redisDishKey="dish:cache:"+categoryId;

        //先查缓存，如果缓存中有数据，直接返回。否则查询数据库，然后将数据库结果添加到缓存中。
        //同时管理端修改删除起售等操作时，都要删除对应缓存，否则一直是老的数据
        List<DishVO> dishVOList = (List<DishVO>)redisTemplate.opsForValue().get(redisDishKey);
        if(!org.springframework.util.CollectionUtils.isEmpty(dishVOList)){
            log.info("查询redis缓存命中数据");
            return dishVOList;
        }
        //1.根据分类找到菜品信息
        List<Dish> dishList=dishMapper.listByCategoryId(categoryId);

        //2. 根据菜品找到对应的口味信息，同时封装数据
        dishVOList=new ArrayList<>();

        for (Dish dish:dishList
             ) {
            DishVO dishVO = new DishVO();
            Long dishId=dish.getId();
            List<DishFlavor> dishFlavorList = dishFlavorMapper.getByDishId(dishId);
            BeanUtils.copyProperties(dish,dishVO);
            dishVO.setFlavors(dishFlavorList);
            dishVOList.add(dishVO);
        }

        redisTemplate.opsForValue().set(redisDishKey,dishVOList);//能够放入缓存一定是序列化的，实现implements
        //3.返回
        log.info("将查询到的数据缓存在redis中");
        return dishVOList;
    }
}
