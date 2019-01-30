package com.gsh.read.model.http.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gsh.read.common.consts.HttpConst;
import com.gsh.read.common.utils.HttpUtils;
import com.gsh.read.common.vo.request.LoginVo;
import com.gsh.read.common.vo.request.SelectBookFormsVo;
import com.gsh.read.common.vo.request.SelectUserInfoVo;
import com.gsh.read.common.vo.request.UploadDataVo;
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
    public void login(LoginVo vo,HttpCallback callback) throws Exception {
        vo.setServCode("002");
        MyRequestParams params=new MyRequestParams(HttpConst.URL_LOGIN);
        params.setBodyContent(JSON.toJSONString(vo));
        HttpUtils.post(params, callback);
    }

    @Override
    public void selectBookforms(SelectBookFormsVo vo, HttpCallback callback) throws Exception {
        MyRequestParams params=new MyRequestParams(HttpConst.URL_QUERY_BOOKFORMS);
        params.setBodyContent(JSON.toJSONString(vo));
        HttpUtils.post(params, callback);
    }

    @Override
    public void selectUserInfo(SelectUserInfoVo vo, HttpCallback callback) throws Exception {
        MyRequestParams params=new MyRequestParams(HttpConst.URL_QUERY_USER_INFO);
        params.setBodyContent(JSON.toJSONString(vo));
        HttpUtils.post(params, callback);
    }

    @Override
    public void uploadData(UploadDataVo vo, HttpCallback callback) throws Exception {
        MyRequestParams params=new MyRequestParams(HttpConst.URL_METER_READ);
        params.setBodyContent(JSON.toJSONString(vo));
        HttpUtils.post(params, callback);
    }
}
