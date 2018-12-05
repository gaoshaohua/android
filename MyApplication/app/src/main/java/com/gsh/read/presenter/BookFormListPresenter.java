package com.gsh.read.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gsh.read.common.vo.request.BookFormVo;
import com.gsh.read.common.vo.request.LoginVo;
import com.gsh.read.common.vo.response.ResultVo;
import com.gsh.read.model.http.HttpCallback;
import com.gsh.read.model.http.impl.HttpRequestImpl;
import com.gsh.read.view.IBaseMvpView;
import com.gsh.read.view.IBookFormListMvpView;
import com.gsh.read.view.IMainMvpView;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

public class BookFormListPresenter extends BaseMvpPresenter {

    private IBookFormListMvpView mvpView;
    public BookFormListPresenter(IBaseMvpView mvpView) {
        super(mvpView);
        this.mvpView=(IBookFormListMvpView)mvpView;
    }

    public void queryList(){
        BookFormVo vo=new BookFormVo();

        try {
            HttpRequestImpl.getInstance().queryBookFormList(vo, new HttpCallback<ResultVo>() {
                @Override
                public void onSuccess(ResultVo vo) {
                    if(vo.getCode().equals("0")){
                        mvpView.showMessage("操作失败,错误码："+vo.getCode());
                        return;
                    }
                    List<JSON> mData=new ArrayList<JSON>();
                    for(int i=0;i<10;i++){
                        JSONObject obj=new JSONObject();
                        obj.put("name","张三");
                        mData.add(obj);
                    }
                    mvpView.setData(mData);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                }

                @Override
                public void onCancelled(Callback.CancelledException cex) {
                }

                @Override
                public void onFinished() {
                    mvpView.finishRefresh();
                    mvpView.finishLoadMore();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
