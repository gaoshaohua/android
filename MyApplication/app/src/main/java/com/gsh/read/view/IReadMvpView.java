package com.gsh.read.view;

import com.alibaba.fastjson.JSON;

import java.util.List;

public interface IReadMvpView extends IBaseMvpView {

    public void setData(List<JSON> mData);

}
