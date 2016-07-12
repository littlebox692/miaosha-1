package com.miaosha.service.impl;

import com.miaosha.dto.Exposer;
import com.miaosha.dto.MiaoshaExecution;
import com.miaosha.entity.Miaosha;
import com.miaosha.exception.MiaoshaCloseException;
import com.miaosha.exception.MiaoshaException;
import com.miaosha.exception.RepeatMiaoshaException;
import com.miaosha.service.MiaoshaService;

import java.util.List;

/**
 * Created by Yan on 2016/7/12.
 */
public class MiaoshaServiceImpl implements MiaoshaService {
    @Override
    public List<Miaosha> getMiaoshaList() {
        return null;
    }

    @Override
    public Miaosha getMiaoshaById(long miaoshaId) {
        return null;
    }

    @Override
    public Exposer exposeMiaoshaUrl(long miaoshaId) {
        return null;
    }

    @Override
    public MiaoshaExecution executeMiaosha(long miaoshaId, long userPhone, String md5) throws MiaoshaException {
        return null;
    }
}
