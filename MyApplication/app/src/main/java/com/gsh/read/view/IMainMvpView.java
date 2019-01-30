package com.gsh.read.view;

import com.gsh.read.common.utils.PageUtils;
import com.gsh.read.common.vo.response.BookFormVo;

import java.util.List;

public interface IMainMvpView extends IBaseMvpView {

    PageUtils getPageUtils();

    void setData(List<BookFormVo> mData);

}
