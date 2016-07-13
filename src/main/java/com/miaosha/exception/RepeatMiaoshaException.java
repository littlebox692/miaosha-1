package com.miaosha.exception;

/**
 * Created by Yan on 2016/7/12.
 */
// TODO: 2016/7/12
public class RepeatMiaoshaException extends MiaoshaException {
    public RepeatMiaoshaException(String message) {
        super(message);
    }

    public RepeatMiaoshaException(String message, Throwable cause) {
        super(message, cause);
    }
}
