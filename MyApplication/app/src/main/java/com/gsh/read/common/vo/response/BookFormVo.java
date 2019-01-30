package com.gsh.read.common.vo.response;

/**
 * 表册返回VO
 */
public class BookFormVo {
    private String userNo;
    private String bcId;
    private String bcName;
    private String consNo;
    private String userName;
    private String sbAddr;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getBcId() {
        return bcId;
    }

    public void setBcId(String bcId) {
        this.bcId = bcId;
    }

    public String getBcName() {
        return bcName;
    }

    public void setBcName(String bcName) {
        this.bcName = bcName;
    }

    public String getConsNo() {
        return consNo;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSbAddr() {
        return sbAddr;
    }

    public void setSbAddr(String sbAddr) {
        this.sbAddr = sbAddr;
    }
}
