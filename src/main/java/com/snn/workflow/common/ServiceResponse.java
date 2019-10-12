package com.snn.workflow.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @enumName ResponseCode
 * @Author lulu
 * @Date 2019-09-10 13:50
 **/
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)  // 保证序列化Json的时候，如果是null的对象，key也会消失
public class ServiceResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private ServiceResponse(int status) {
        this.status = status;
    }

    private ServiceResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServiceResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServiceResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore // 使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static <T> ServiceResponse<T> createBySuccess() {
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServiceResponse<T> createBySuccessMessage(String msg) {
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServiceResponse<T> createBySuccess(T data) {
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServiceResponse<T> createBySuccess(String msg, T data) {
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServiceResponse<T> createByError() {
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> ServiceResponse<T> createByErrorMessage(String errorMsg) {
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(), errorMsg);
    }

    public static <T> ServiceResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServiceResponse<T>(errorCode, errorMessage);
    }
}




































