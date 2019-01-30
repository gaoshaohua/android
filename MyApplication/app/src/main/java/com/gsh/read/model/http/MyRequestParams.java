package com.gsh.read.model.http;

import org.xutils.http.RequestParams;

public class MyRequestParams extends RequestParams {

    public MyRequestParams(String uri) {
        super(uri);
        this.addHeader("content-Type","application/json");
        this.addHeader("token","123");
        // 将post请求的body参数以json形式提交
        this.setAsJsonContent(true);
    }
}
