package com.example.shop.mapper;

import com.example.shop.model.entity.GoodsRelSku;
import com.example.shop.model.entity.GoodsRelSkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsRelSkuMapper {
    long countByExample(GoodsRelSkuExample example);

    int deleteByExample(GoodsRelSkuExample example);

    int insert(GoodsRelSku record);

    int insertSelective(GoodsRelSku record);

    List<GoodsRelSku> selectByExample(GoodsRelSkuExample example);

    int updateByExampleSelective(@Param("record") GoodsRelSku record, @Param("example") GoodsRelSkuExample example);

    int updateByExample(@Param("record") GoodsRelSku record, @Param("example") GoodsRelSkuExample example);
}