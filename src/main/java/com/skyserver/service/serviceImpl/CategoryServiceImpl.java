package com.skyserver.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skycommon.constant.StatusConstant;
import com.skycommon.exception.DataException;
import com.skycommon.result.PageResult;
import com.skypojo.dto.CategoryDTO;
import com.skypojo.dto.CategoryPageQueryDTO;
import com.skypojo.entity.Category;
import com.skyserver.mapper.CategoryMapper;
import com.skyserver.mapper.DishMapper;
import com.skyserver.mapper.SetmealMapper;
import com.skyserver.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Author shuai
 * @create 2023/7/31 9:33
 * @Instruction 分类管理业务逻辑实现
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public PageResult page(CategoryPageQueryDTO categoryPageQueryDTO) {

        //设置属性
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());

        //分页条件查询
        List<Category> categoryList= categoryMapper.list(categoryPageQueryDTO);

        //封装查询的结果
        Page<Category> page=(Page<Category>) categoryList;
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setStatus(StatusConstant.ENABLE);
//        category.setCreateTime(LocalDateTime.now());
//        category.setUpdateTime(LocalDateTime.now());
//        category.setCreateUser((long) (int) ThreadLocalUtil.get());
//        category.setUpdateUser((long) (int) ThreadLocalUtil.get());
        categoryMapper.insert(category);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {

        /**
         * 修改：name,sort,create_user,creat_time
         */
        Category category = Category.builder().id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .sort(categoryDTO.getSort())
//                .updateUser((long) (int) ThreadLocalUtil.get())
//                .updateTime(LocalDateTime.now())
                .build();
        categoryMapper.update(category);
    }

    @Override
    public void enableAndDisable(int status, long id) {

        /**
         * 将id的状态改成status
         */
        Category category = Category.builder()
                .id(id)
                .status(status)
//                .updateUser((long) (int) ThreadLocalUtil.get())
//                .updateTime(LocalDateTime.now())
                .build();
        categoryMapper.update(category);

    }

    @Override
    public void delete(long categoryId) {
        /**
         * 删除id为id的分类
         */

        //判断分类中有没有关联的菜品等，需要判断，没有才可以删除

        //根据category查询是否有关联的dish
        Long dishCount=dishMapper.countByCategoryId(categoryId);
        Long setmealCount=setmealMapper.countByCategoryId(categoryId);
        System.out.println(dishCount);
        System.out.println(setmealCount);

        if(dishCount+setmealCount!=0){
            throw new DataException("该分类有关联的菜品或套餐，无法删除");
        }
        categoryMapper.delete(categoryId);
    }

    @Override
    public List<Category> list(Integer type) {
        return categoryMapper.selectByType(type);
    }

    @Override
    public List<Category> listAll() {
        /**
         * 查询所有的分类，除了status=0的
         */
        List<Category> categories=categoryMapper.listAll();
        return categories;
    }
}
