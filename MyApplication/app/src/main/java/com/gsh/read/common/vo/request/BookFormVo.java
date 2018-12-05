package com.gsh.read.common.vo.request;

/**
 * 表册用户查询VO
 */
public class BookFormVo {
    //表册ID
    private String id;
    //抄表状态
    private String readFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }
}
