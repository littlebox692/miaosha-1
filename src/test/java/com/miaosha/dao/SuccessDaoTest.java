package com.miaosha.dao;

import com.miaosha.entity.SuccessInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Yan on 2016/7/12.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessDaoTest {

    @Resource
    private SuccessDao successDao;

    @Test
    public void insertSuccess() throws Exception {
        int insertCount = successDao.insertSuccess(1006L, 10086L);
        System.out.println(insertCount);
    }

    @Test
    public void querySuccess() throws Exception {
        SuccessInfo successInfo = successDao.querySuccess(1006L, 10086L);
        System.out.println(successInfo);
        System.out.println(successInfo.getMiaosha());


    }


}