package com.gsh.read.common.vo.response;

/**
 * 用水户返回VO
 */
public class CustomerVo {
    private String consNo;
    private String startCode;
    private String endCode;
    private String dateYM;
    private String num;

    public String getConsNo() {
        return consNo;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
    }

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public String getEndCode() {
        return endCode;
    }

    public void setEndCode(String endCode) {
        this.endCode = endCode;
    }

    public String getDateYM() {
        return dateYM;
    }

    public void setDateYM(String dateYM) {
        this.dateYM = dateYM;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
