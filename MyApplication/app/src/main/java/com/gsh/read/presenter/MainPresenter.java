package com.gsh.read.presenter;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gsh.read.common.consts.HttpConst;
import com.gsh.read.common.vo.request.LoginVo;
import com.gsh.read.common.vo.request.SelectBookFormsVo;
import com.gsh.read.common.vo.response.BookFormVo;
import com.gsh.read.common.vo.response.ResultVo;
import com.gsh.read.model.database.MyDbManager;
import com.gsh.read.model.http.HttpCallback;
import com.gsh.read.model.http.impl.HttpRequestImpl;
import com.gsh.read.view.IBaseMvpView;
import com.gsh.read.view.IMainMvpView;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BaseMvpPresenter {

    private IMainMvpView mvpView;
    public MainPresenter(IBaseMvpView mvpView) {
        super(mvpView);
        this.mvpView=(IMainMvpView)mvpView;
    }


    public void queryListFromNet(){
        SelectBookFormsVo vo=new SelectBookFormsVo("004");
        vo.setUserNo("110207");

        try {
            HttpRequestImpl.getInstance().selectBookforms(vo, new HttpCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    ResultVo<JSONArray> resultVo = JSON.parseObject(result,ResultVo.class);
                    if(resultVo.getRtnCode().equals(HttpConst.SUCCESS)){
                        mvpView.showMessage("请求成功...");
                        querySuccess(JSON.toJSONString(resultVo.getRtnData()));
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
                    mvpView.setData(new ArrayList<BookFormVo>());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void queryListFromDb(final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<BookFormVo> dbList = new ArrayList<BookFormVo>();
                try {
                    dbList = MyDbManager.getInstance().findAll(BookFormVo.class);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                Message msg=new Message();
                if (null==dbList||dbList.size()==0){
                    msg.what=0;
                }else{
                    msg.what=1;
                    msg.getData().putString("mData",JSON.toJSONString(dbList));
                }
                handler.sendMessage(msg);
            }
        }).start();
    }

    public void querySuccess(String result){
        try {
            List<BookFormVo> mData = JSON.parseArray(result,BookFormVo.class);
            mvpView.setData(mData);
            MyDbManager.getInstance().saveOrUpdate(mData);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
