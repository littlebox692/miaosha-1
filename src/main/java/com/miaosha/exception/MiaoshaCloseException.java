package com.miaosha.exception;

/**
 * Created by Yan on 2016/7/12.
 */
public class MiaoshaCloseException extends MiaoshaException {

    public MiaoshaCloseException(String message) {
        super(message);
    }

    public MiaoshaCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
