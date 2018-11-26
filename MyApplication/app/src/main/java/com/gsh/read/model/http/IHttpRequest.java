package com.gsh.read.model.http;

import com.alibaba.fastjson.JSONObject;
import com.gsh.read.common.vo.request.LoginVo;

/**
 * http请求接口
 */
public interface IHttpRequest {
    public void httpLogin(LoginVo vo,HttpCallback callback) throws Exception;
}
