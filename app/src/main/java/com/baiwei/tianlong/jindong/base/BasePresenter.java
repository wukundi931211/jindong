package com.baiwei.tianlong.jindong.base;

import android.content.Context;

import com.baiwei.tianlong.jindong.app.App;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends View> {
    protected V view;
    //取消订阅
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BasePresenter(V view) {
        this.view = view;
        initModel();
    }
    //注入m层的方法
    protected abstract void initModel();


    //解决内存泄漏
    void onDestroy(){
        view = null;
        compositeDisposable.clear();
    }

    protected Context context(){
        if (view!=null && view.cotext()!=null){
            return view.cotext();
        }else {
            return App.getAppContext();
        }
    }
}
