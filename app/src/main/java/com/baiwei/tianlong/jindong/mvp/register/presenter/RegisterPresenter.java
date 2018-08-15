package com.baiwei.tianlong.jindong.mvp.register.presenter;


import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.register.model.RegisterModel;
import com.baiwei.tianlong.jindong.mvp.register.model.beans.RegisterBean;
import com.baiwei.tianlong.jindong.mvp.register.view.iview.IRegisterView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/16 下午 4:51.
 * @Description This is RegisterPresenter.
 */
public class RegisterPresenter extends BasePresenter<IRegisterView> {

    private RegisterModel registerModel;

    public RegisterPresenter(IRegisterView iRegisterView) {
        super(iRegisterView);
    }

    @Override
    protected void initModel() {
        registerModel = new RegisterModel();
    }

    public void getRegData(Map<String, String> map) {
        registerModel.getRegData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if ("0".equals(registerBean.getCode())) {
                            if (view != null) {
                                view.getRegDataSuccess(registerBean);
                            }
                        } else {
                            if (view != null) {
                                view.getRegDataFailed(registerBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.getRegDataFailed(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
