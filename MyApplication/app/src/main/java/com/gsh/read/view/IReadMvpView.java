package com.gsh.read.view;

import com.alibaba.fastjson.JSON;
import com.gsh.read.common.vo.response.CustomerVo;
import com.gsh.read.common.vo.response.ResultVo;

import java.util.List;

public interface IReadMvpView extends IBaseMvpView {

     void setData(ResultVo mData);

    void setHistoryData(List<CustomerVo> mData);

}
