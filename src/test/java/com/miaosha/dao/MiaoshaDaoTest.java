package com.miaosha.dao;

import com.miaosha.entity.Miaosha;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    @Resource
    private MiaoshaDao miaoshaDao;

    @Test
    public void reduceNumber() throws Exception {
        Date miaoshaTime = new Date();
        int re = miaoshaDao.reduceNumber(1005, miaoshaTime);
        System.out.println(re);

    }

    @Test
    public void queryById() throws Exception {

        long id = 1005;
        Miaosha miaosha = miaoshaDao.queryById(id);
        System.out.println(miaosha.getName());
        System.out.println(miaosha);
    }

    @Test
    public void queryAll() throws Exception {
        int offset = 0;
        int limit = 1000;
        List<Miaosha> list = miaoshaDao.queryAll(offset, limit);
        for (Miaosha m : list) {
            System.out.println(m);
        }

    }

}