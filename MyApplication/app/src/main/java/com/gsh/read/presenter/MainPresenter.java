package com.gsh.read.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gsh.read.common.vo.request.LoginVo;
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

}
