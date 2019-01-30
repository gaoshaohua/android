package com.gsh.read.common.vo.request;

/**
 * 查询用水户信息VO
 */
public class SelectUserInfoVo extends BaseVo{
    /**
     * 用户号
     */
    private String consNo;

    public SelectUserInfoVo(String servCode) {
        super(servCode);
    }

    public String getConsNo() {
        return consNo;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
    }
}
