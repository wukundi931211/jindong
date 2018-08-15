package com.baiwei.tianlong.jindong.mvp.wode.presenter;

import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.wode.model.MoDeModel;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.HomeAdBean;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.LoginBean;

import com.baiwei.tianlong.jindong.mvp.wode.view.WoDeIView;

import org.reactivestreams.Subscriber;

import java.lang.annotation.Annotation;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.ResourceSubscriber;

public class WoDePresenter extends BasePresenter<WoDeIView>{

    private MoDeModel moDeModel;

    public WoDePresenter(WoDeIView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        moDeModel = new MoDeModel();
    }

    public void Login(Map<String,String> map){
        moDeModel.login(map)
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Observer<LoginBean>() {
                       @Override
                       public void onSubscribe(Disposable d) {
                          compositeDisposable.add(d);
                       }

                       @Override
                       public void onNext(LoginBean logins) {
                           if ("0".equals(logins.getCode())){
                                if (view!=null){
                                   view.getLoginDataSuccess(logins);
                                }
                           }else {
                                if (view!=null){
                                    view.getLoginDataFailed(logins.getMsg());
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

    public void MyData(){
        moDeModel.getWoDeData()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<HomeAdBean>() {

                    @Override
                    public void onNext(HomeAdBean homeAdBean) {
                        if ("0".equals(homeAdBean.getCode())){
                            if (view!=null){
                                view.getMyCenterDataSuccess(homeAdBean);
                            }
                        }else {
                            if (view!=null){
                                view.getMyCenterDataFailed(homeAdBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.getMyCenterDataFailed(t.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                    //
                    Disposable disposable =new Disposable() {
                        @Override
                        public void dispose() {
                            compositeDisposable.add(disposable);
                        }

                        @Override
                        public boolean isDisposed() {
                            return false;
                        }
                    };

                });

    }
}
