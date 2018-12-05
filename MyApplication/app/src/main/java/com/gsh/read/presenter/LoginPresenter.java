package com.gsh.read.presenter;

import com.gsh.read.common.vo.request.LoginVo;
import com.gsh.read.common.vo.response.ResultVo;
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
        mvpView.showLoading();
        LoginVo vo=new LoginVo("admin","123");
        try {
            HttpRequestImpl.getInstance().login(vo, new HttpCallback<ResultVo>() {
                @Override
                public void onSuccess(ResultVo vo) {
                    mvpView.startMainActivity();
                    /**
                    if(vo.getCode().equals("0")){
                        mvpView.showMessage("登录成功...");
                        mvpView.startMainActivity();
                    }else{
                        mvpView.showMessage("登录失败,错误码："+vo.getCode());
                    }**/
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    mvpView.showMessage("onError...");
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
