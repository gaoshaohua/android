package com.gsh.read.common.vo.request;

/**
 * 查询表册列表VO
 */
public class SelectBookFormsVo extends BaseVo{
    /**
     * 抄表员编号
     */
    private String userNo;

    public SelectBookFormsVo(String servCode) {
        super(servCode);
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

}
