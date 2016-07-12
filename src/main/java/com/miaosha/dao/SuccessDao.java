package com.miaosha.dao;

import com.miaosha.entity.Miaosha;
import com.miaosha.entity.SuccessInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by Yan on 2016/7/10.
 */
public interface SuccessDao {

    /**
     * 将成功秒杀的记录插入success_info表中
     *
     * @param miaoshaId
     * @param userPhone
     * @return 插入行数
     */
    int insertSuccess(@Param("miaoshaId") long miaoshaId, @Param("userPhone") long userPhone);

    /**
     * 通过手机号码和秒杀商品对象联合查询 秒杀成功信息
     *
     * @param miaoshaId
     * @return 秒杀成功信息
     */
    SuccessInfo querySuccess(@Param("miaoshaId") long miaoshaId, @Param("userPhone") long userPhone);
}
