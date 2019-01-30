package com.gsh.read.common.vo.request;

public class BaseVo {
    /**
     * 服务编码
     */
    private String servCode;

    public BaseVo(String servCode) {
        this.servCode = servCode;
    }

    public String getServCode() {
        return servCode;
    }

    public void setServCode(String servCode) {
        this.servCode = servCode;
    }
}
