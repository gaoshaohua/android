package com.gsh.read.common.vo.response;

import java.util.List;
import java.util.Map;

/**
 * 请求返回的VO
 */
public class ResultVo {
    private String code;
    private String msg;
    private Map<String,Object> result;


    public static ResultVo getSuccess(){
        ResultVo vo=new ResultVo();
        vo.setCode("0");
        vo.setMsg("操作成功");
        return vo;
    }

    public static ResultVo getFail(){
        ResultVo vo=new ResultVo();
        vo.setCode("500");
        vo.setMsg("操作失败");
        return vo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
