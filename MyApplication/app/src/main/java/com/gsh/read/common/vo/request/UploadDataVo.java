package com.gsh.read.common.vo.request;

/**
 * 上传用水信息VO
 */
public class UploadDataVo extends BaseVo{
    /**
     * 抄表员编号
     */
    private String userNo;
    /**
     * 用水户编号
     */
    private String consNo;
    /**
     * 止码
     */
    private String endCode;

    public UploadDataVo(String servCode) {
        super(servCode);
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getConsNo() {
        return consNo;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
    }

    public String getEndCode() {
        return endCode;
    }

    public void setEndCode(String endCode) {
        this.endCode = endCode;
    }
}
