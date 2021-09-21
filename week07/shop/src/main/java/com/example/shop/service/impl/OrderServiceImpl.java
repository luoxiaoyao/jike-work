package com.example.shop.service.impl;

import com.example.shop.mapper.GoodsMapper;
import com.example.shop.mapper.OrderDetailMapper;
import com.example.shop.mapper.OrderMapper;
import com.example.shop.model.dto.GoodsDTO;
import com.example.shop.model.dto.UpdateGoodsCountDTO;
import com.example.shop.model.entity.Goods;
import com.example.shop.model.entity.Order;
import com.example.shop.model.entity.OrderDetail;
import com.example.shop.model.enums.GoodsStatus;
import com.example.shop.model.enums.OrderStatus;
import com.example.shop.model.ro.order.CreateOrderReq;
import com.example.shop.service.OrderService;
import com.example.shop.util.Arith;
import com.example.shop.util.IdAgent;
import com.example.shop.util.OrderNumUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int createOrder(CreateOrderReq req) throws Exception {
        List<GoodsDTO> goodsList = req.getGoodsList();
        Set<Long> goodsIds = goodsList.stream().map(GoodsDTO::getGoodsId).collect(Collectors.toSet());
        List<Goods> goods = goodsMapper.selectByIds(goodsIds);
        Map<Long, Goods> goodsMap = goods.stream().collect(Collectors.toMap(Goods::getId, p -> p));
        checkGoods(goodsList, goods, goodsMap);
        Long orderId = IdAgent.getIdUtil().getId();
        Order order = new Order();
        BeanUtils.copyProperties(req, order);
        order.setId(orderId);
        order.setCreateTime(new Date());
        order.setCreatorId(req.getUserId());
        order.setOrderNum(OrderNumUtil.getOrderNum());
        AtomicReference<BigDecimal> totalMoney = new AtomicReference<>(BigDecimal.ZERO);
        List<OrderDetail> orderDetailList = goodsList.stream().map(p -> {
            Goods good = goodsMap.get(p.getGoodsId());
            totalMoney.set(Arith.add(totalMoney.get(), Arith.mul(good.getPrice(), p.getCount())));
            return toOrderDetail(p, order, good.getPrice());
        }).collect(Collectors.toList());
        order.setTotalMoney(totalMoney.get());
        order.setDiscountAmount(BigDecimal.ZERO);
        order.setReceivedMoney(BigDecimal.ZERO);
        order.setOrderTime(new Date());
        order.setPaidMoney(BigDecimal.ZERO);
        order.setStatus(OrderStatus.WAITING.getIndex());
        order.setReceivableMoney(Arith.sub(order.getTotalMoney(), order.getDiscountAmount()));
        orderMapper.insert(order);
        orderDetailList.stream().mapToInt(p -> orderDetailMapper.insert(p)).sum();
        goodsList.stream().mapToInt(p -> goodsMapper.updateGoodsCount(new UpdateGoodsCountDTO(p.getGoodsId(), p.getCount(), new Date(), req.getUserId()))).sum();
        return 1;
    }

    private OrderDetail toOrderDetail(GoodsDTO goodsDTO, Order order, BigDecimal price) {
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(goodsDTO, orderDetail);
        orderDetail.setId(IdAgent.getIdUtil().getId());
        orderDetail.setOrderNum(order.getOrderNum());
        orderDetail.setOrderId(order.getId());
        orderDetail.setCreatorId(order.getCreatorId());
        orderDetail.setCreateTime(new Date());
        orderDetail.setOriginalPrice(price);
        return orderDetail;
    }

    private void checkGoods(List<GoodsDTO> goodsList, List<Goods> goods, Map<Long, Goods> goodsMap) throws Exception {

        if (CollectionUtils.isEmpty(goodsList)) {
            throw new Exception("商品不能空");
        }

        if (CollectionUtils.isEmpty(goods)) {
            throw new Exception("有效商品不能空");
        }
        for (GoodsDTO goodsDTO : goodsList) {
            if (goodsMap.get(goodsDTO.getGoodsId()) == null) {
                throw new Exception(goodsDTO.getGoodsName() + "为非有效商品");
            }
            Goods good = goodsMap.get(goodsDTO.getGoodsId());
            if (GoodsStatus.DOWN.getIndex().equals(good.getStatus())) {
                throw new Exception(good.getName() + ":商品已下架");
            }
            if (good.getCount() < goodsDTO.getCount()) {
                throw new Exception(good.getName() + ":该商品库存不足");
            }
            if (good.getCount() <= 0) {
                throw new Exception(good.getName() + ":该商品已售罄");
            }
        }
    }
}
