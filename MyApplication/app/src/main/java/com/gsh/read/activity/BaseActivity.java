package com.gsh.read.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gsh.read.MyApplication;
import com.gsh.read.R;
import com.gsh.read.view.IBaseMvpView;

import org.xutils.x;

public class BaseActivity extends AppCompatActivity implements IBaseMvpView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initComponent();
    }

    private void initComponent(){

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(String data) {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
