package com.miaosha.service.impl;

import com.miaosha.dao.MiaoshaDao;
import com.miaosha.dao.SuccessInfoDao;
import com.miaosha.dao.cache.RedisDao;
import com.miaosha.dto.Exposer;
import com.miaosha.dto.MiaoshaExecution;
import com.miaosha.entity.Miaosha;
import com.miaosha.enums.MiaoshaStateEnum;
import com.miaosha.exception.MiaoshaCloseException;
import com.miaosha.exception.MiaoshaException;
import com.miaosha.exception.RepeatMiaoshaException;
import com.miaosha.service.MiaoshaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Yan on 2016/7/12.
 */

//
@Service
// 用注解的方式声明该方法为需要事务处理方法。
public class MiaoshaServiceImpl implements MiaoshaService {

    // spring容器会自动查找miaoshaDao的实例，并注入到这个service类中
    @Autowired
    private MiaoshaDao miaoshaDao;
    // spring会在IOC容器中自动查找successInfo对象，并自动注入到这个service类中
    @Autowired
    private SuccessInfoDao successInfoDao;
    private String salt = "sfhsfksjdfh&!%&^!%$(*&";
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisDao redisDao;


    @Override
    public List<Miaosha> getMiaoshaList() {
        List<Miaosha> miaoshaList = miaoshaDao.queryAll(0, 1000);
        return miaoshaList;
    }

    @Override
    public Miaosha getMiaoshaById(long miaoshaId) {
        Miaosha m = miaoshaDao.queryById(miaoshaId);
        return m;
    }

    @Override
    public Exposer exposeMiaoshaUrl(long miaoshaId) {

        /**
         * 使用redis对数据库查询进行缓存
         * get from cache
         * if null
         *      访问数据库
         *      写入redis缓存
         * else
         *      logic
         */
        Miaosha m = redisDao.getMiaosha(miaoshaId);
        if (m == null) {
            m = miaoshaDao.queryById(miaoshaId);
            if (m == null) {
                return new Exposer(false, miaoshaId);
            } else {
                String redisPutResult = redisDao.putMiaosha(m);
            }
        }
        Date startTime = m.getStartTime();
        Date endTime = m.getEndTime();
        Date currentTime = new Date();
        //若当前时间小于开始时间，或者当前时间大于结束时间，则isExposed为false
        if (currentTime.getTime() < startTime.getTime() || currentTime.getTime() > endTime.getTime()) {
            return new Exposer(false, miaoshaId, currentTime, startTime, endTime);
        }
        //若开始时间 < 当前时间 < 结束时间，则获取md5并设置isExposed为true
        String md5 = getMD5(miaoshaId);
        Exposer exposer = new Exposer(true, md5, miaoshaId);
        exposer.setStartTime(startTime);
        exposer.setEndTime(endTime);
        exposer.setCurrentTime(currentTime);
        System.err.println(exposer);
        System.err.println(exposer.isFlag());
        return exposer;
    }

    private String getMD5(long miaoshaId) {
        String base = miaoshaId + "." + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional
    public MiaoshaExecution executeMiaosha(long miaoshaId, long userPhone, String md5)
            throws MiaoshaException {

        if (md5 == null || !md5.equals(getMD5(miaoshaId))) {
            throw new MiaoshaException("miaosha rewrite error.");
        }
        try {
            int insertCount = successInfoDao.insertSuccess(miaoshaId, userPhone);
            if (insertCount <= 0) {
                throw new RepeatMiaoshaException("miaosha repeat error.");
            } else {
                Date currentTime = new Date();
                int updateCount = miaoshaDao.reduceNumber(miaoshaId, currentTime);
                if (updateCount <= 0) {
                    throw new MiaoshaCloseException(MiaoshaStateEnum.END.getStateInfo());
                } else {
                    return new MiaoshaExecution(miaoshaId, MiaoshaStateEnum.SUCCESS, successInfoDao.querySuccess(miaoshaId, userPhone));
                }
            }
        } catch (MiaoshaCloseException e1) {
            throw e1;
        } catch (RepeatMiaoshaException e2) {
            throw e2;
        } catch (MiaoshaException e3) {
            logger.error(e3.getMessage(), e3);
            throw new MiaoshaException("miaosha error", e3);
        }
    }
}
