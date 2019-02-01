package com.gsh.read.common.vo.response;

import java.io.Serializable;

/**
 * 上传读数返回VO
 */
public class UploadDataVo implements Serializable {
    private String CODE;
    private String MSG;

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }
}
