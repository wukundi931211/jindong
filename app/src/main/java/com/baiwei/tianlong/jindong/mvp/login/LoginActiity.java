package com.baiwei.tianlong.jindong.mvp.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseActivity;
import com.baiwei.tianlong.jindong.mvp.login.model.beans.LoginsActivityBeans;
import com.baiwei.tianlong.jindong.mvp.login.presenter.LoginPresenter;
import com.baiwei.tianlong.jindong.mvp.login.view.LoginView;

import butterknife.ButterKnife;

public class LoginActiity extends BaseActivity<LoginPresenter> implements LoginView {




    @Override
    protected int provideLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected LoginPresenter providePresenter() {
        return null;
    }


    @Override
    public void getLoginDataSuccess(LoginsActivityBeans logins) {

    }

    @Override
    public void getLoginDataFailed(String error) {

    }

    @Override
    public Context cotext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
