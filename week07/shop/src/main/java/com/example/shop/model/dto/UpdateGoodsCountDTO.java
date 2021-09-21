package com.example.shop.model.dto;

import java.util.Date;

public class UpdateGoodsCountDTO {
    private Long goodsId;
    private Integer count;
    private Date time;
    private Long userId;

    public UpdateGoodsCountDTO(Long goodsId, Integer count, Date time, Long userId) {
        this.goodsId = goodsId;
        this.count = count;
        this.time = time;
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
