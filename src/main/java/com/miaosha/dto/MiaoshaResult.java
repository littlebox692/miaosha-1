package com.miaosha.dto;

/**
 * Created by Yan on 2016/7/17.
 */

//这个类用于封装json数据 T为json数据对象
public class MiaoshaResult<T> {
    //声明一个boolean类型的变量用于判断是否成功
    private boolean success;
    //json类型的数据
    private T data;
    //若失败，error用于指示错误信息
    private String error;

    public MiaoshaResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public MiaoshaResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public MiaoshaResult(T data) {
        this.data = data;
        this.success = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
