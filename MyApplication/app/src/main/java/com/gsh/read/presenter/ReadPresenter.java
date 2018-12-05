package com.gsh.read.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gsh.read.common.vo.request.LoginVo;
import com.gsh.read.common.vo.request.UserVo;
import com.gsh.read.model.http.HttpCallback;
import com.gsh.read.model.http.impl.HttpRequestImpl;
import com.gsh.read.view.IBaseMvpView;
import com.gsh.read.view.IMainMvpView;
import com.gsh.read.view.IReadMvpView;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

public class ReadPresenter extends BaseMvpPresenter {

    private IReadMvpView mvpView;
    public ReadPresenter(IBaseMvpView mvpView) {
        super(mvpView);
        this.mvpView=(IReadMvpView)mvpView;
    }

    public void queryUserByCode(){
        mvpView.showLoading();
        UserVo vo=new UserVo();
        try {
            HttpRequestImpl.getInstance().queryUserByCode(vo, new HttpCallback() {
                @Override
                public void onSuccess(Object o) {

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(Callback.CancelledException cex) {

                }

                @Override
                public void onFinished() {
                    mvpView.hideLoading();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
