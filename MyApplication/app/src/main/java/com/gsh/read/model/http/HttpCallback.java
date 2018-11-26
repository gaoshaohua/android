package com.gsh.read.model.http;

import org.xutils.common.Callback;

public interface HttpCallback<T> {

    void onSuccess(T t);

    void onError(Throwable ex, boolean isOnCallback);

    void onCancelled(Callback.CancelledException cex);

    void onFinished();
}
