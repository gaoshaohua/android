package com.gsh.read.common.vo.response;

import java.util.List;
import java.util.Map;

/**
 * 请求返回的VO
 * @param <T>
 */
public class ResultVo<T> {
    private String servCode;
    private String rtnCode;
    private String consNo;
    private T rtnData;


    public String getServCode() {
        return servCode;
    }

    public void setServCode(String servCode) {
        this.servCode = servCode;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getConsNo() {
        return consNo;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
    }

    public T getRtnData() {
        return rtnData;
    }

    public void setRtnData(T rtnData) {
        this.rtnData = rtnData;
    }
}
