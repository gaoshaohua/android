package com.gsh.read.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gsh.read.common.consts.HttpConst;
import com.gsh.read.common.vo.request.LoginVo;
import com.gsh.read.common.vo.request.SelectUserInfoVo;
import com.gsh.read.common.vo.request.UploadDataVo;
import com.gsh.read.common.vo.response.BookFormVo;
import com.gsh.read.common.vo.response.CustomerVo;
import com.gsh.read.common.vo.response.ResultVo;
import com.gsh.read.model.database.MyDbManager;
import com.gsh.read.model.http.HttpCallback;
import com.gsh.read.model.http.impl.HttpRequestImpl;
import com.gsh.read.view.IBaseMvpView;
import com.gsh.read.view.IMainMvpView;
import com.gsh.read.view.IReadMvpView;

import org.xutils.common.Callback;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

public class ReadPresenter extends BaseMvpPresenter {

    private IReadMvpView mvpView;
    public ReadPresenter(IBaseMvpView mvpView) {
        super(mvpView);
        this.mvpView=(IReadMvpView)mvpView;
    }

    public void queryUserByCode(String consNo){
        SelectUserInfoVo vo=new SelectUserInfoVo("003");
        vo.setConsNo(consNo);
        try {
            HttpRequestImpl.getInstance().selectUserInfo(vo, new HttpCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    ResultVo<JSONArray> resultVo = JSON.parseObject(result,ResultVo.class);
                    if(resultVo.getRtnCode().equals(HttpConst.SUCCESS)){
                        mvpView.showMessage("请求成功...");
                        List<CustomerVo> mData = JSON.parseArray(resultVo.getRtnData().toJSONString(),CustomerVo.class);
                        mvpView.setData(resultVo);
                        mvpView.setHistoryData(mData);
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

    public void uploadMetaRead(String userNo, final String consNo, String endCode){
        UploadDataVo vo=new UploadDataVo("005");
        vo.setUserNo(userNo);
        vo.setConsNo(consNo);
        vo.setEndCode(endCode);
        try {
            HttpRequestImpl.getInstance().uploadData(vo, new HttpCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    ResultVo<com.gsh.read.common.vo.response.UploadDataVo> resultVo = JSON.parseObject(result,ResultVo.class);
                    if(resultVo.getRtnCode().equals(HttpConst.SUCCESS)){
                        com.gsh.read.common.vo.response.UploadDataVo dataVo=JSON.parseObject(JSON.toJSONString(resultVo.getRtnData()),com.gsh.read.common.vo.response.UploadDataVo.class);
                        mvpView.showMessage(dataVo.getMSG());
                        if(dataVo.getCODE().equals("0")||dataVo.getCODE().equals("3")){
                            BookFormVo bfVo=new BookFormVo();
                            bfVo.setConsNo(consNo);
                            bfVo.setStatus("1");
                            try {
                                MyDbManager.getInstance().update(bfVo,"status");
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }
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
                }

                @Override
                public void onFinished() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
