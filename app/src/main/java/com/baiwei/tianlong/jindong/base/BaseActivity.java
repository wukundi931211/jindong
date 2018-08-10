package com.baiwei.tianlong.jindong.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    //注入基类   BasePresenter
    protected P presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(provideLayoutId());
        //butterKnitfe  抽基类
        ButterKnife.bind(provideBindView());
        //初始化
        initView();
        //实现p层的方法
        presenter = providePresenter();

        //获得数据
        initData();

        //监听事件
        initListener();
    }

    protected abstract int provideLayoutId();

    protected abstract Activity provideBindView();

    protected abstract P providePresenter();

    protected void initView() {

    }

    protected void initData() {

    }

    protected void initListener() {

    }
}
