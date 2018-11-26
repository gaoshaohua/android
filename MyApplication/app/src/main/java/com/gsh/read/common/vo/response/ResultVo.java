package com.gsh.read.common.vo.response;

import java.util.List;
import java.util.Map;

/**
 * 请求返回的VO
 * @param <T>
 */
public class ResultVo<T> {
    private String code;
    private String msg;
    private T data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
