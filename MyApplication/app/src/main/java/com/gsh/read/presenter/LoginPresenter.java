package com.gsh.read.presenter;

import com.gsh.read.common.vo.request.LoginVo;
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
        LoginVo vo=new LoginVo("admin","123");
        try {
            HttpRequestImpl.getInstance().httpLogin(vo, new HttpCallback() {
                @Override
                public void onSuccess(Object o) {
                    mvpView.showMessage("登录成功...");
                    mvpView.startMainActivity();
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
