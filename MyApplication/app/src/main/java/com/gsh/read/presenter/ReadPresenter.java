package com.gsh.read.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gsh.read.common.vo.request.LoginVo;
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
        LoginVo vo=new LoginVo("admin","123");
        try {
            HttpRequestImpl.getInstance().httpLogin(vo, new HttpCallback() {
                @Override
                public void onSuccess(Object o) {
                    mvpView.showMessage("登录成功...");
                    List<JSON> mData=new ArrayList<JSON>();
                    for(int i=0;i<20;i++){
                        JSONObject obj=new JSONObject();
                        obj.put("name","张三");
                        mData.add(obj);
                    }
                    mvpView.setData(mData);
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
