package com.miaosha.service;

import com.miaosha.dto.Exposer;
import com.miaosha.dto.MiaoshaExecution;
import com.miaosha.entity.Miaosha;
import com.miaosha.enums.MiaoshaStateEnum;
import com.miaosha.exception.MiaoshaCloseException;
import com.miaosha.exception.MiaoshaException;
import com.miaosha.exception.RepeatMiaoshaException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Yan on 2016/7/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class MiaoshaServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MiaoshaService miaoshaService;
//    @Autowired
//    private MiaoshaStateEnum miaoshaStateEnum;

    @Test
    public void getMiaoshaList() throws Exception {
        List<Miaosha> miaoshas = miaoshaService.getMiaoshaList();
        logger.info("miaoshaList={}", miaoshas);
    }

    @Test
    public void getMiaoshaById() throws Exception {
        long miaoshaId = 1005L;
        Miaosha miaosha = miaoshaService.getMiaoshaById(miaoshaId);
        logger.info("miaoshaId={}", miaosha);
    }

    @Test
    public void exposeMiaoshaUrl() throws Exception {
        long miaoshaId = 1006L;
        Exposer exposer = miaoshaService.exposeMiaoshaUrl(miaoshaId);
        logger.info("exposer={}", exposer);
    }

    @Test
    public void executeMiaosha() throws Exception {
        // md5='2afaadbeabf688c14e312a7901f0ae67'
        long miaoshaId = 1006L;
        long userPhone = 10087L;
        String md5 = "2afaadbeabf688c14e312a7901f0ae67";
        try {
            MiaoshaExecution miaoshaExecution = miaoshaService.executeMiaosha(miaoshaId, userPhone, md5);
            logger.info("秒杀执行结果={}", miaoshaExecution);
        } catch (RepeatMiaoshaException e) {
            logger.debug(e.getMessage());
        } catch (MiaoshaCloseException e) {
            logger.debug(e.getMessage());
        } catch (MiaoshaException e) {
            logger.debug(e.getMessage());
        }

    }
}
