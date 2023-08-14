package com.skyserver.service.serviceImpl;

import com.skycommon.utils.ThreadLocalUtil;
import com.skypojo.dto.ShoppingCartDTO;
import com.skypojo.entity.Dish;
import com.skypojo.entity.Setmeal;
import com.skypojo.entity.ShoppingCart;
import com.skypojo.vo.SetmealVO;
import com.skyserver.mapper.DishMapper;
import com.skyserver.mapper.SetmealMapper;
import com.skyserver.mapper.ShoppingCartMapper;
import com.skyserver.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName ShoppingCartServiceImpl
 * @Author shuai
 * @create 2023/8/5 20:19
 * @Instruction
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void add(ShoppingCartDTO shoppingCartDTO) {

        /**
         * 1。购物车逻辑：查询当前用户购物车是否存在指定商品
         */
        Long currentId = (long)(int) ThreadLocalUtil.get();
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(currentId);
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);//这里采用集合是为了增加拓展性

        //2.如果已经存在，数量加一
        if (!org.springframework.util.CollectionUtils.isEmpty(shoppingCartList)) {
            ShoppingCart cart = shoppingCartList.get(0);//其实只会有一条数据
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.updateNumberById(cart);
        }else{
            //3.不存在则添加新的到购物车。需要判断是菜品还是套餐，分支处理
            Long dishId = shoppingCart.getDishId();
            if(dishId!=null){
                //菜品。此时shoppingCart中有dishId,dishFlavor,userId
                Dish dish = dishMapper.getById(dishId);
                shoppingCart.setAmount(dish.getPrice());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setName(dish.getName());
            }else{
                //套餐
                Long setmealId = shoppingCart.getSetmealId();
                Setmeal setmeal = setmealMapper.getById(setmealId);
                shoppingCart.setAmount(setmeal.getPrice());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setName(setmeal.getName());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());

            shoppingCartMapper.insert(shoppingCart);

        }

    }

    @Override
    public List<ShoppingCart> list() {
        Long currentId=(long)(int) ThreadLocalUtil.get();
        return shoppingCartMapper.listByUserId(currentId);
    }

    @Override
    public void clean() {
        Long currentId=(long)(int) ThreadLocalUtil.get();
        shoppingCartMapper.clean(currentId);
    }

    @Override
    public void sub(ShoppingCartDTO shoppingCartDTO) {//dishId（dishFlavor）或者setmealId

        /**
         * 需要比较商品的数量，数量>1修改number，否则直接删除该商品
         */
        //查询对应商品数量
        ShoppingCart shoppingCart=shoppingCartMapper.get(shoppingCartDTO);
        if(shoppingCart.getNumber()>1){
            shoppingCart.setNumber(shoppingCart.getNumber()-1);
            shoppingCartMapper.updateNumberById(shoppingCart);
        }else{
            //删除该商品
            shoppingCartMapper.delete(shoppingCart);
        }


    }
}
