package com.miaosha.enums;

/**
 * Created by Yan on 2016/7/13.
 */
public enum MiaoshaStateEnum {
    SUCCESS(1, "miaosha success"),
    END(0, "miaosha end"),
    REPEAT_MIAOSHA(-1, "miaosha repeat"),
    INNER_ERROR(-2, "inner error"),
    DATA_REWRITE(-3, "data rewrite"),
    NOT_LOGIN(-4, "not login");
    private int state;
    private String stateInfo;

    MiaoshaStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static MiaoshaStateEnum valueof(int state) {
        for (MiaoshaStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }
}
