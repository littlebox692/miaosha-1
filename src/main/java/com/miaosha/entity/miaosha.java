package com.miaosha.entity;

import java.util.Date;

/**
 * Created by Yan on 2016/7/10.
 */
public class Miaosha {
    private long miaoshaId;
    private String name;
    private int number;
    private Date createTime;
    private Date startTime;
    private Date endTime;

    public Miaosha() {
    }

    public long getMiaoshaId() {
        return miaoshaId;
    }

    public void setMiaoshaId(long miaoshaId) {
        this.miaoshaId = miaoshaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "SuccessInfo{" +
                "miaoshaId=" + miaoshaId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
