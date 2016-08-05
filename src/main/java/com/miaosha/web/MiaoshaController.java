package com.miaosha.web;

import com.miaosha.dto.Exposer;
import com.miaosha.dto.MiaoshaExecution;
import com.miaosha.dto.MiaoshaResult;
import com.miaosha.entity.Miaosha;
import com.miaosha.enums.MiaoshaStateEnum;
import com.miaosha.exception.MiaoshaCloseException;
import com.miaosha.exception.MiaoshaException;
import com.miaosha.exception.RepeatMiaoshaException;
import com.miaosha.service.MiaoshaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Yan on 2016/7/17.
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MiaoshaService miaoshaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        // 获取列表页
        List<Miaosha> list = miaoshaService.getMiaoshaList();
        model.addAttribute("list", list);
        //list.jsp+model = ModeAndView
        return "list";
    }

    @RequestMapping(value = "/{miaoshaId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable Long miaoshaId, Model model) {

        if (miaoshaId == null) {
            return "redirect:/miaosha/list";
        }
        Miaosha miaosha = miaoshaService.getMiaoshaById(miaoshaId);
        if (miaosha == null) {
            return "redirect:/miaosha/list";
        }
        model.addAttribute("detail", miaosha);
        return "detail";
    }

    @RequestMapping(value = "/{miaoshaId}/expose",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public MiaoshaResult<Exposer> exposeMiaoshaUrl(@PathVariable Long miaoshaId) {
        MiaoshaResult<Exposer> result = null;
        try {
            Exposer exposer = miaoshaService.exposeMiaoshaUrl(miaoshaId);
            result = new MiaoshaResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new MiaoshaResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{miaoshaId}/{md5}/execute",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public MiaoshaResult<MiaoshaExecution> execute(@PathVariable("miaoshaId") Long miaoshaId,
                                                   @CookieValue(value = "userPhone") Long userPhone,
                                                   @PathVariable("md5") String md5) {
        MiaoshaResult<MiaoshaExecution> result = null;
        if (userPhone == null) {
            return new MiaoshaResult<MiaoshaExecution>(false, "unregister!");
        }
        try {
            MiaoshaExecution execution = miaoshaService.executeMiaosha(miaoshaId, userPhone, md5);
            result = new MiaoshaResult<MiaoshaExecution>(true, execution);
            return result;
        } catch (RepeatMiaoshaException e) {
            MiaoshaExecution execution = new MiaoshaExecution(miaoshaId, MiaoshaStateEnum.REPEAT_MIAOSHA);
            return new MiaoshaResult<MiaoshaExecution>(true, execution);
        } catch (MiaoshaCloseException e) {
            MiaoshaExecution execution = new MiaoshaExecution(miaoshaId, MiaoshaStateEnum.END);
            return new MiaoshaResult<MiaoshaExecution>(true, execution);
        } catch (MiaoshaException e) {
            MiaoshaExecution execution = new MiaoshaExecution(miaoshaId, MiaoshaStateEnum.INNER_ERROR);
            return new MiaoshaResult<MiaoshaExecution>(true, execution);
        }
    }

    @RequestMapping("/time/now")
    @ResponseBody
    public MiaoshaResult<Long> getCurrentTime() {
        MiaoshaResult<Long> time = null;
        Date currentTime = new Date();
        time = new MiaoshaResult<Long>(currentTime.getTime());
        System.out.println("currentTime=" + time.getData());
        return time;
    }
}
