package com.gsh.read.model.http;

import org.xutils.http.RequestParams;

public class MyRequestParams extends RequestParams {

    public MyRequestParams(String uri) {
        super(uri);
        this.addHeader("token","123");
    }
}
