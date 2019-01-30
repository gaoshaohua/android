package com.gsh.read.presenter;

import com.alibaba.fastjson.JSON;
import com.gsh.read.common.consts.HttpConst;
import com.gsh.read.common.vo.request.LoginVo;
import com.gsh.read.common.vo.response.ResultVo;
import com.gsh.read.common.vo.response.UserVo;
import com.gsh.read.model.http.HttpCallback;
import com.gsh.read.model.http.impl.HttpRequestImpl;
import com.gsh.read.view.IBaseMvpView;
import com.gsh.read.view.ILoginMvpView;

import org.xutils.common.Callback;

public class LoginPresenter extends BaseMvpPresenter {

    private ILoginMvpView mvpView;
    public LoginPresenter(IBaseMvpView mvpView) {
        super(mvpView);
        this.mvpView=(ILoginMvpView)mvpView;
    }

    public void login(){
        LoginVo vo=new LoginVo("002");
        vo.setUserNo("110207");
        try {
            HttpRequestImpl.getInstance().login(vo, new HttpCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    ResultVo<UserVo> resultVo = JSON.parseObject(result,ResultVo.class);
                    if(resultVo.getRtnCode().equals(HttpConst.SUCCESS)){
                        mvpView.showMessage("登录成功...");
                        mvpView.startMainActivity();
                    }else{
                        mvpView.showMessage("登录失败...");
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    mvpView.showMessage("onError...");
                }

                @Override
                public void onCancelled(Callback.CancelledException cex) {
                    mvpView.showMessage("onCancelled...");
                }

                @Override
                public void onFinished() {
                    mvpView.showMessage("onFinished...");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
