package com.miaosha.dao.cache;

import com.miaosha.dao.MiaoshaDao;
import com.miaosha.entity.Miaosha;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Yan on 2016/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private MiaoshaDao miaoshaDao;

    @Test
    public void testRedis() throws Exception {
        long id = 1005;
        Miaosha miaosha = redisDao.getMiaosha(id);
        if (miaosha == null) {
            Miaosha miaosha1 = miaoshaDao.queryById(id);
            if (miaosha1 != null) {
                String result = redisDao.putMiaosha(miaosha1);
                System.err.println(result);
                Miaosha miaosha2 = redisDao.getMiaosha(id);
                System.out.println(miaosha2);
            }
        }
    }
}