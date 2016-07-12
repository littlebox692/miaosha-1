package com.miaosha.dao;

import com.miaosha.entity.Miaosha;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Yan on 2016/7/10.
 */
public interface MiaoshaDao {
    /**
     * 减少库存商品数
     *
     * @param miaoshaId
     * @param miaoshaTime
     * @return 更新的记录行数，结果应>=1
     */
    int reduceNumber(@Param("miaoshaId") long miaoshaId, @Param("miaoshaTime") Date miaoshaTime);

    /**
     * 通过miaoshaId查询秒杀商品信息
     *
     * @param miaoshaId
     * @return 查询的秒杀商品信息
     */
    Miaosha queryById(long miaoshaId);

    /**
     * 通过偏移量和limit查询一组秒杀商品信息。
     *
     * @param offset
     * @param limit
     * @return 查询的秒杀商品列表
     */
    List<Miaosha> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
