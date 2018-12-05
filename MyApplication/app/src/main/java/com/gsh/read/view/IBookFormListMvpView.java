package com.gsh.read.view;

import com.alibaba.fastjson.JSON;
import com.gsh.read.common.utils.PageUtils;

import java.util.List;

public interface IBookFormListMvpView extends IBaseMvpView {

     PageUtils getPageUtils();

     void setData(List<JSON> mData);

     void finishRefresh();

    void finishLoadMore();
}
