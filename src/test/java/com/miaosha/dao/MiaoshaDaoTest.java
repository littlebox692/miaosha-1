package com.miaosha.dao;

import com.miaosha.entity.Miaosha;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.util.calendar.BaseCalendar;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yan on 2016/7/11.
 */

/**
 * junit和spring整合，在启动junit时加载springIOC容器
 * spring-test，junit
 *
 * @throws Exception
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class MiaoshaDaoTest {
    // 注入dao实现类依赖
    @Autowired
    private MiaoshaDao miaoshaDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void reduceNumber() throws Exception {
        Date miaoshaTime = new Date();
        int re = miaoshaDao.reduceNumber(1005, miaoshaTime);
        logger.info("更新行数={}", re);
    }

    @Test
    public void queryById() throws Exception {

        Miaosha miaosha = miaoshaDao.queryById(1005);
        logger.info("miaoshaId-1005={}", miaosha);
    }

    @Test
    public void queryAll() throws Exception {
        List<Miaosha> list = miaoshaDao.queryAll(0, 1000);
        logger.info("misoshaList={}", list);
    }
}