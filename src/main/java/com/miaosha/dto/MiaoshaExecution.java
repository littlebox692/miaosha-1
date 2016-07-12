package com.miaosha.dto;

import com.miaosha.entity.Miaosha;
import com.miaosha.entity.SuccessInfo;

/**
 * 封装秒杀执行后得到的结果
 * Created by Yan on 2016/7/12.
 */
public class MiaoshaExecution {
    private long miaoshaId;
    int state;
    String stateInfo;
    SuccessInfo successInfo;

    public MiaoshaExecution(long miaoshaId, int state, String stateInfo, SuccessInfo successInfo) {
        this.miaoshaId = miaoshaId;
        this.state = state;
        this.stateInfo = stateInfo;
        this.successInfo = successInfo;
    }

    public MiaoshaExecution(long miaoshaId, int state, String stateInfo) {
        this.miaoshaId = miaoshaId;
        this.state = state;
        this.stateInfo = stateInfo;
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

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessInfo getSuccessInfo() {
        return successInfo;
    }

    public void setSuccessInfo(SuccessInfo successInfo) {
        this.successInfo = successInfo;
    }
}
