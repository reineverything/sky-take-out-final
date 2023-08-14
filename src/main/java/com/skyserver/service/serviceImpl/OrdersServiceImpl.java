package com.skyserver.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.JsonObject;
import com.skycommon.constant.MessageConstant;
import com.skycommon.exception.BusinessException;
import com.skycommon.result.PageResult;
import com.skycommon.utils.ThreadLocalUtil;
import com.skypojo.dto.*;
import com.skypojo.entity.AddressBook;
import com.skypojo.entity.OrderDetail;
import com.skypojo.entity.Orders;
import com.skypojo.entity.ShoppingCart;
import com.skypojo.vo.OrderStatisticsVO;
import com.skypojo.vo.OrderSubmitVO;
import com.skypojo.vo.OrdersVO;
import com.skyserver.mapper.AddressBookMapper;
import com.skyserver.mapper.OrderDetailMapper;
import com.skyserver.mapper.OrdersMapper;
import com.skyserver.mapper.ShoppingCartMapper;
import com.skyserver.service.OrdersService;
import com.skyserver.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName OrdersServiceImpl
 * @Author shuai
 * @create 2023/8/6 10:42
 * @Instruction
 */
@Service
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO) {

        //0。传递给商家的订单包括两部分，商品备注 和 地址两部分。先查询地址信息
        AddressBook addressBook = addressBookMapper.getById(ordersSubmitDTO.getAddressBookId());
        if(ObjectUtils.isEmpty(addressBook)){
            throw new BusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }

        //0如果购物车数据为空，不能下单
        ShoppingCart shoppingCart = ShoppingCart.builder().userId((long) (int) ThreadLocalUtil.get()).build();
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);
        if(org.springframework.util.CollectionUtils.isEmpty(shoppingCartList)){
            throw new BusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
        }

        //1.保存订单数据
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersSubmitDTO,orders);
        orders.setNumber(String.valueOf(System.nanoTime()));//生成订单号
        orders.setStatus(Orders.PENDING_PAYMENT);//待付款
        orders.setUserId((long)(int)ThreadLocalUtil.get() );
        orders.setOrderTime(LocalDateTime.now());
        orders.setPayStatus(Orders.UN_PAID);//支付状态未支付
        orders.setPhone(addressBook.getPhone());
        orders.setAddress(addressBook.getDetail());//详细收货地址
        orders.setConsignee(addressBook.getConsignee());//收货人
        ordersMapper.insert(orders);


        //2.保存订单明细数据集--购物车
        List<OrderDetail> orderDetailList = shoppingCartList.stream().map(cart -> {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart, orderDetail);

            //赋值订单id
            orderDetail.setOrderId(orders.getId());

            return orderDetail;
        }).collect(Collectors.toList());

        orderDetailMapper.insertBatch(orderDetailList);

        //3.清空当前用户购物车
        shoppingCartMapper.clean((long)(int)ThreadLocalUtil.get());

        //4.组装数据并返回
        OrderSubmitVO orderSubmitVO = OrderSubmitVO.builder()
                .id(orders.getId())
                .orderTime(orders.getOrderTime())
                .orderNumber(orders.getNumber())
                .orderAmount(orders.getAmount())
                .build();
        return orderSubmitVO;
    }

    @Override
    public PageResult page(OrdersPageQueryDTO ordersPageQueryDTO) {
        /**
         * 根据订单号，手机号下单时间，配送状态等信息条件分页查询
         */
        PageHelper.startPage(ordersPageQueryDTO.getPage(),ordersPageQueryDTO.getPageSize());//PageHelper只会对第一个查询结果分页！！！

        //条件查询订单信息
        List<OrdersVO> ordersVOList=ordersMapper.page(ordersPageQueryDTO);

        //查询对应订单的菜品信息并封装
        for (OrdersVO ordersVO:ordersVOList
             ) {
            Long orderId =ordersVO.getId();
            List<OrderDetail> orderDetailList=orderDetailMapper.getByOrderId(orderId);//查询到菜品或者套餐名称

            //拼接字符串
            StringBuilder sb = new StringBuilder();
            for (OrderDetail orderDetail: orderDetailList
                 ) {
                sb.append(orderDetail.getName());
            }
            String orderDishes=sb.toString();
            ordersVO.setOrderDishes(orderDishes);
        }

        Page<OrdersVO> page=(Page<OrdersVO>) ordersVOList;
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public OrderStatisticsVO getStatistics() {
        //统计状态分别为2，3，4 的订单的数量
        //2待接单 3已接单 4派送中
        List<Orders> ordersList=ordersMapper.list();
        Integer confirmed=0,deliveryInProgress=0,toBeConfirmed=0;

        for (Orders order: ordersList
             ) {
            Integer status=order.getStatus();
            if(status==2){
                toBeConfirmed++;
            }else if(status==3){
                confirmed++;
            }else if(status==4){
                deliveryInProgress++;
            }
        }
        OrderStatisticsVO orderStatisticsVO = OrderStatisticsVO.builder()
                .confirmed(confirmed)
                .deliveryInProgress(deliveryInProgress)
                .toBeConfirmed(toBeConfirmed)
                .build();
        return orderStatisticsVO;
    }

    @Override
    public OrdersVO getDetail(Long id) {
        OrdersVO ordersVO = new OrdersVO();
        //1.查询orders表
        Orders orders=ordersMapper.getById(id);
        BeanUtils.copyProperties(orders,ordersVO);

        //2.查询order_detail表，order_dishes和orderDetailList
        List<OrderDetail> orderDetails = orderDetailMapper.getByOrderId(id);
        //拼接菜品或者套餐名称
        StringBuilder sb = new StringBuilder();
        for (OrderDetail orderDetail:orderDetails
             ) {
            sb.append(orderDetail.getName());
        }
        ordersVO.setOrderDishes(sb.toString());
        ordersVO.setOrderDetailList(orderDetails);
        return ordersVO;
    }

    @Override
    public void cancel(OrdersCancelDTO ordersCancelDTO) {

        /**
         * 修改订单状态为已取消，如果已经完成支付，执行退款（只能用日志打印实现）
         */
        //1.根据id查询订单
        Orders orderNew = ordersMapper.getById(ordersCancelDTO.getId());

        if(orderNew.getPayStatus()==Orders.PAID){//已经支付状态
            log.info("申请退款：{}",orderNew.getAmount());
        }

        //2。修改订单状态已取消，订单取消原因。订单取消时间
        Orders orders = new Orders();
        orders.setId(orderNew.getId());
        orders.setStatus(Orders.CANCELLED);
        orders.setCancelReason(ordersCancelDTO.getCancelReason());
        orders.setCancelTime(LocalDateTime.now());
        ordersMapper.update(orders);
    }

    @Override
    public void reject(OrdersRejectionDTO ordersRejectionDTO) {
        /**
         * 拒单：将订单状态修改成已取消。
         * 只有订单处于待接单状态才能拒单，否则报异常
         * 进行退款，修改拒单原因，取消时间
         */
        //1.根据id查询相关订单
        Orders ordersNew = ordersMapper.getById(ordersRejectionDTO.getId());

        if(ordersNew.getPayStatus()==Orders.PAID){
            log.info("申请退款：{}",ordersNew.getAmount());
        }

        //2.修改订单状态
        Orders orders = new Orders();
        orders.setId(ordersNew.getId());
        orders.setStatus(Orders.CANCELLED);
        orders.setRejectionReason(ordersRejectionDTO.getRejectionReason());
        orders.setCancelTime(LocalDateTime.now());
        ordersMapper.update(orders);

    }

    @Override
    public void confirm(OrdersConfirmDTO ordersConfirmDTO) {

        /**
         * 需要将订单状态改成已结单
         */
        Orders orders = new Orders();
        orders.setId(ordersConfirmDTO.getId());
        orders.setStatus(Orders.CONFIRMED);
        ordersMapper.update(orders);
    }

    @Override
    public void delivery(Long id) {
        /**
         * 将状态改成派送中，而且只有已确认的订单才能被派送
         */
        Orders ordersDB = ordersMapper.getById(id);

        if(ordersDB==null || !ordersDB.getStatus().equals(Orders.CONFIRMED)){
            throw new BusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }

        Orders orders = new Orders();
        orders.setId(id);
        orders.setStatus(Orders.DELIVERY_IN_PROGRESS);
        ordersMapper.update(orders);
    }

    @Override
    public void complete(Long id) {
        /**
         * 只有派送中订单才可以完成
         * 状态改成已完成
         */
        Orders ordersDB = ordersMapper.getById(id);
        if(ordersDB==null || !ordersDB.getStatus().equals(Orders.DELIVERY_IN_PROGRESS)){
            throw new BusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }

        Orders orders = new Orders();
        orders.setId(id);
        orders.setStatus(Orders.COMPLETED);
        orders.setDeliveryTime(LocalDateTime.now());
        ordersMapper.update(orders);
    }

    @Override
    public void pay(OrdersPaymentDTO ordersPaymentDTO) throws IOException {
        /**
         * 支付订单只要将订单的支付状态改成1，结账时间修改即可，状态改成待接单
         */
        Orders ordersDB=ordersMapper.getByOrderNumber(ordersPaymentDTO.getOrderNumber());//根据订单号查询订单
        Orders orders = new Orders();
        orders.setId(ordersDB.getId());
        orders.setStatus(Orders.TO_BE_CONFIRMED);
        orders.setPayStatus(Orders.PAID);
        orders.setCheckoutTime(LocalDateTime.now());
        ordersMapper.update(orders);
        /**
         * 支付成功后，需要推送成功给管理端，提醒接单
         */
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("type",1);//提醒新订单
        paramMap.put("orderId",orders.getId());
        paramMap.put("content",ordersDB.getNumber());
        log.info("向客户端推送消息：{}",paramMap);
        webSocketServer.sendMessageToAll(JSONObject.toJSONString(paramMap));//需要将map转成json

    }

    @Override
    public void reminder(Long id) throws IOException {
        /**
         * 用户催单
         */
        Orders orders = ordersMapper.getById(id);
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("type",2);//用户催单
        paramMap.put("orderId",id);
        paramMap.put("content",orders.getNumber());
        webSocketServer.sendMessageToAll(JSONObject.toJSONString(paramMap));//需要将map转成json
    }

    @Override
    public void repetition(Long id) {
        //todo
    }
}
