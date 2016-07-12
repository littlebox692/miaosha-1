package com.miaosha.entity;

import java.util.Date;

/**
 * Created by Yan on 2016/7/10.
 */
public class SuccessInfo {
    private String successId;
    private long userPhone;
    private short state;
    private Date createTime;

    // 持有一个miaosha对象,指定某个商品被成功秒杀
    private Miaosha miaosha;

    public Miaosha getMiaosha() {
        return miaosha;
    }

    public void setMiaosha(Miaosha miaosha) {
        this.miaosha = miaosha;
    }

    public SuccessInfo() {
    }

    public String getMiaoshaId() {
        return successId;
    }

    public void setMiaoshaId(String miaoshaId) {
        this.successId = miaoshaId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreatTime() {
        return createTime;
    }

    public void setCreatTime(Date creatTime) {
        this.createTime = creatTime;
    }

    @Override
    public String toString() {
        return "SuccessInfo{" +
                "miaoshaId='" + successId + '\'' +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
