package com.gsh.read.model.http.impl;

import com.alibaba.fastjson.JSONObject;
import com.gsh.read.common.consts.HttpConst;
import com.gsh.read.common.utils.HttpUtils;
import com.gsh.read.common.vo.request.LoginVo;
import com.gsh.read.model.http.HttpCallback;
import com.gsh.read.model.http.IHttpRequest;
import com.gsh.read.model.http.MyRequestParams;

public class HttpRequestImpl implements IHttpRequest {

    private static HttpRequestImpl singleton;

    private HttpRequestImpl() {}

    public static HttpRequestImpl getInstance() {
        if (singleton == null) {
            synchronized (HttpRequestImpl.class) {
                singleton = new HttpRequestImpl();
            }
        }
        return singleton;
    }


    @Override
    public void httpLogin(LoginVo vo,HttpCallback callback) throws Exception {
        MyRequestParams params=new MyRequestParams(HttpConst.URL_LOGIN);
        HttpUtils.post(params, callback);
    }
}
