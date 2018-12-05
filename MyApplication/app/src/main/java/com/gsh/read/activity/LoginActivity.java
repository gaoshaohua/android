package com.gsh.read.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gsh.read.R;
import com.gsh.read.presenter.LoginPresenter;
import com.gsh.read.view.ILoginMvpView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.login)
public class LoginActivity extends BaseActivity implements ILoginMvpView {

    @ViewInject(R.id.et_account)
    private EditText etAccount;

    @ViewInject(R.id.et_pwd)
    private EditText etPwd;

    @ViewInject(R.id.btn_login)
    private Button btnLogin;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new LoginPresenter(this);
    }

    @Event(R.id.btn_login)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                presenter.login();
                break;
        }
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
