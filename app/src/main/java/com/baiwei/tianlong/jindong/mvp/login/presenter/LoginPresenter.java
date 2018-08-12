package com.baiwei.tianlong.jindong.mvp.login.presenter;

import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.login.model.LoginModel;
import com.baiwei.tianlong.jindong.mvp.login.model.beans.LoginsActivityBeans;
import com.baiwei.tianlong.jindong.mvp.login.view.LoginView;


import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView>{
    //注入model
    private LoginModel loginModel;


    //注入p层
    public LoginPresenter(LoginView view) {
        super(view);
    }
    //注入model
    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    public void Login(Map<String,String> map){
        loginModel.login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginsActivityBeans>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LoginsActivityBeans loginsActivityBeans) {
                        if ("0".equals(loginsActivityBeans.getCode())){
                            if (view!=null){
                                view.getLoginDataSuccess(loginsActivityBeans);
                            }
                        }else {
                            if (view!=null){
                                view.getLoginDataFailed(loginsActivityBeans.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (view!=null){
                            view.getLoginDataFailed(t.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
