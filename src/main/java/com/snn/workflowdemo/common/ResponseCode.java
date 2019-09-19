package com.snn.workflowdemo.common;

/**
 * @enumName ResponseCode
 * @Author lulu
 * @Date 2019-09-10 13:50
 **/
public enum ResponseCode {

    SUCCESS(666, "SUCCESS"),
    ERROR(555, "ERROR"),
    NEED_LOGIN(999, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(111, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }
    public  String getDesc() {
        return  desc;
    }
}
