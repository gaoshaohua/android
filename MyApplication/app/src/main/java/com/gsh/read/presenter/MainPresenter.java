package com.gsh.read.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gsh.read.common.consts.HttpConst;
import com.gsh.read.common.vo.request.LoginVo;
import com.gsh.read.common.vo.request.SelectBookFormsVo;
import com.gsh.read.common.vo.response.BookFormVo;
import com.gsh.read.common.vo.response.ResultVo;
import com.gsh.read.model.http.HttpCallback;
import com.gsh.read.model.http.impl.HttpRequestImpl;
import com.gsh.read.view.IBaseMvpView;
import com.gsh.read.view.IMainMvpView;
import org.xutils.common.Callback;
import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BaseMvpPresenter {

    private IMainMvpView mvpView;
    public MainPresenter(IBaseMvpView mvpView) {
        super(mvpView);
        this.mvpView=(IMainMvpView)mvpView;
    }

    public void queryList(){
        SelectBookFormsVo vo=new SelectBookFormsVo("004");
        vo.setUserNo("110207");

        try {
            HttpRequestImpl.getInstance().selectBookforms(vo, new HttpCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    ResultVo<JSONArray> resultVo = JSON.parseObject(result,ResultVo.class);
                    if(resultVo.getRtnCode().equals(HttpConst.SUCCESS)){
                        mvpView.showMessage("请求成功...");
                        List<BookFormVo> mData = JSON.parseArray(resultVo.getRtnData().toJSONString(),BookFormVo.class);
                        mvpView.setData(mData);
                    }else{
                        mvpView.showMessage("请求失败...");
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
