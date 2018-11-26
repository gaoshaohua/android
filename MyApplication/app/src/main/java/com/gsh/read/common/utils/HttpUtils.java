package com.gsh.read.common.utils;

import android.util.Log;

import com.gsh.read.model.http.HttpCallback;
import com.gsh.read.model.http.MyRequestParams;

import org.xutils.common.Callback;
import org.xutils.x;

public class HttpUtils {

    public static void post(MyRequestParams params,final HttpCallback<String> callback){
        Log.d(HttpUtils.class.getSimpleName(),params.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(HttpUtils.class.getSimpleName(),"onSuccess...");
                callback.onSuccess(result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(HttpUtils.class.getSimpleName(),"onError...");
                ex.printStackTrace();
                callback.onError(ex,isOnCallback);
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.d(HttpUtils.class.getSimpleName(),"onCancelled...");
                cex.printStackTrace();
                callback.onCancelled(cex);
            }
            @Override
            public void onFinished() {
                Log.d(HttpUtils.class.getSimpleName(),"onFinished...");
                callback.onFinished();
            }
        });
    }
}
