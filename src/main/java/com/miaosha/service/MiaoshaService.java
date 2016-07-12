package com.miaosha.service;

import com.miaosha.dto.Exposer;
import com.miaosha.dto.MiaoshaExecution;
import com.miaosha.entity.Miaosha;
import com.miaosha.exception.MiaoshaCloseException;
import com.miaosha.exception.MiaoshaException;
import com.miaosha.exception.RepeatMiaoshaException;

import java.util.List;

/**
 * Created by Yan on 2016/7/12.
 */

/*
 * 站在"使用者"的角度去设计业务层的接口，
 * 注意接口的粒度，不能太抽象也不能太具体。
 * 定义接口需要考虑的三个因素：粒度、参数、返回类型（或返回异常）
 */
public interface MiaoshaService {

    /**
     * 查询所有的秒杀记录
     *
     * @return
     */
    List<Miaosha> getMiaoshaList();

    /**
     * 根据id查询单个记录
     *
     * @param miaoshaId
     * @return
     */
    Miaosha getMiaoshaById(long miaoshaId);

    /**
     * 用来暴露秒杀url和秒杀系统开闭时间等信息
     * 秒杀系统开启时，放回秒杀页面url；未开启时，返回当前时间和秒杀开启时间
     *
     * @param miaoshaId
     * @return
     */
    Exposer exposeMiaoshaUrl(long miaoshaId);

    /**
     * 该接口用来执行秒杀操作，此处的md5为上面的exposeMiaoshaUrl方法所暴露的md5信息，此处传进来用于验证
     *
     * @param miaoshaId
     * @param userPhone
     * @param md5
     * @return
     */
    MiaoshaExecution executeMiaosha(long miaoshaId, long userPhone, String md5)
            throws MiaoshaException;


}
