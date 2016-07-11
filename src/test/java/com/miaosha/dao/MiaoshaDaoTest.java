package com.miaosha.dao;

import com.miaosha.entity.Miaosha;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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

    }

}