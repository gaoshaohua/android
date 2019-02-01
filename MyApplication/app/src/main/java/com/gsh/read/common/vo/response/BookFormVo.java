package com.gsh.read.common.vo.response;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 表册返回VO
 */
@Table(name="bookforms")
public class BookFormVo {
    @Column(name="userNo")
    private String userNo;
    @Column(name="bcId")
    private String bcId;
    @Column(name="bcName")
    private String bcName;
    @Column(name="consNo", isId = true)
    private String consNo;
    @Column(name="userName")
    private String userName;
    @Column(name="sbAddr")
    private String sbAddr;
    @Column(name="status")
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
