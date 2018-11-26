package com.gsh.read.presenter;

import com.gsh.read.view.IBaseMvpView;

public class BaseMvpPresenter {

    private IBaseMvpView mvpView;

    public BaseMvpPresenter(IBaseMvpView mvpView) {
        this.mvpView=mvpView;
    }
}
