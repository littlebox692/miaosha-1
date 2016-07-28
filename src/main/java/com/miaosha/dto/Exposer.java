package com.miaosha.dto;

/**
 * dto表示传输层数据对象，因为和dao对象不相关，多以单独用dto包来管理
 * Created by Yan on 2016/7/12.
 */

import java.util.Date;

/**
 * Exposer类是对要暴露的秒杀url以及其他相关信息的封装
 */
public class Exposer {

    // 指示是否已开启秒杀系统，若开启则为true，否则为false
    private boolean flag;//isExposed
    // 秒杀商品ID
    private long miaoshaId;
    // 在执行秒杀时，应该将这个md5值传递给执行函数，用于验证
    private String md5;
    // 当前时间
    private Date currentTime;
    //秒杀开始时间
    private Date startTime;
    //秒杀结束时间
    private Date endTime;

    public Exposer(boolean isExposed, String md5, long miaoshaId) {
        this.flag = isExposed;
        this.md5 = md5;
        this.miaoshaId = miaoshaId;
    }


    public Exposer(boolean isExposed, long miaoshaId, Date currentTime, Date startTime, Date endTime) {
        this.flag = isExposed;
        this.miaoshaId = miaoshaId;
        this.currentTime = currentTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Exposer(boolean isExposed, long miaoshaId) {
        this.flag = isExposed;
        this.miaoshaId = miaoshaId;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public long getMiaoshaId() {
        return miaoshaId;
    }

    public void setMiaoshaId(long miaoshaId) {
        this.miaoshaId = miaoshaId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
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
        return "Exposer{" +
                "isExposed=" + flag +
                ", miaoshaId=" + miaoshaId +
                ", md5='" + md5 + '\'' +
                ", currentTime=" + currentTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
