package com.gsh.read.view;

import com.alibaba.fastjson.JSON;
import com.gsh.read.common.utils.PageUtils;

import java.util.List;

public interface IMainMvpView extends IBaseMvpView {

    public PageUtils getPageUtils();

    public void setData(List<JSON> mData);

}
