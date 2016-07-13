package com.miaosha.dto;

import com.miaosha.entity.Miaosha;
import com.miaosha.entity.SuccessInfo;
import com.miaosha.enums.MiaoshaStateEnum;

/**
 * 封装秒杀执行后得到的结果
 * Created by Yan on 2016/7/12.
 */
public class MiaoshaExecution {
    private long miaoshaId;
    int state;
    String stateInfo;
    SuccessInfo successInfo;

    public MiaoshaExecution(long miaoshaId, MiaoshaStateEnum stateEnum, SuccessInfo successInfo) {
        this.miaoshaId = miaoshaId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successInfo = successInfo;
    }

    public MiaoshaExecution(long miaoshaId, MiaoshaStateEnum stateEnum) {
        this.miaoshaId = miaoshaId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getMiaoshaId() {
        return miaoshaId;
    }

    public void setMiaoshaId(long miaoshaId) {
        this.miaoshaId = miaoshaId;
    }

    public int getState() {
        return state;
    }

    public void setState(MiaoshaStateEnum stateEnum) {
        this.state = stateEnum.getState();
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(MiaoshaStateEnum stateEnum) {
        this.stateInfo = stateEnum.getStateInfo();
    }

    public SuccessInfo getSuccessInfo() {
        return successInfo;
    }

    public void setSuccessInfo(SuccessInfo successInfo) {
        this.successInfo = successInfo;
    }
}
