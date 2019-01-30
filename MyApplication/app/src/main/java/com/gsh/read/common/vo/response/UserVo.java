package com.gsh.read.common.vo.response;

/**
 * 用户登录返回VO
 */
public class UserVo {
    private String userNo;
    private String userName;
    private String token;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
