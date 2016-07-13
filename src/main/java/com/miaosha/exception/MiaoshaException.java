package com.miaosha.exception;

import com.miaosha.entity.SuccessInfo;
import com.miaosha.enums.MiaoshaStateEnum;

/**
 * Created by Yan on 2016/7/12.
 */
public class MiaoshaException extends RuntimeException {
    public MiaoshaException(String message) {
        super(message);
    }

    public MiaoshaException(String message, Throwable cause) {
        super(message, cause);
    }
}
