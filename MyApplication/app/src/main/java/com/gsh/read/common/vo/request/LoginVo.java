package com.gsh.read.common.vo.request;

/**
 * 登录VO
 */
public class LoginVo extends BaseVo{
    /**
     * 抄表员编号
     */
    private String userNo;
    /**
     * 密码
     */
    private String pwd;

    public LoginVo(String servCode) {
        super(servCode);
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
