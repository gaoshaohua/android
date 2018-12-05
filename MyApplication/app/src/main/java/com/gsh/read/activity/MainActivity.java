package com.gsh.read.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gsh.read.R;
import com.gsh.read.presenter.LoginPresenter;
import com.gsh.read.presenter.MainPresenter;
import com.gsh.read.view.ILoginMvpView;
import com.gsh.read.view.IMainMvpView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainMvpView {

    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;

    @ViewInject(R.id.imgv_read)
    private ImageView imgvRead;

    @ViewInject(R.id.imgv_change)
    private ImageView imgvChange;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        presenter=new MainPresenter(this);
    }

    @Event({R.id.imgv_read,R.id.imgv_change})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.imgv_read:
                startActivity(new Intent(MainActivity.this,BookFormListActivity.class));
                break;
            case R.id.imgv_change:
                showMessage("该功能暂时不可用");
                break;
        }
    }

}
