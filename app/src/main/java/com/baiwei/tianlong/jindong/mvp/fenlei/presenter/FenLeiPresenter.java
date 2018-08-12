package com.baiwei.tianlong.jindong.mvp.fenlei.presenter;

import android.app.Activity;

import com.baiwei.tianlong.jindong.base.BaseActivity;
import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.FenLeiModelTask;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.LeftFenLeiBeans;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.RightFenLeiBeans;
import com.baiwei.tianlong.jindong.mvp.fenlei.view.FenLeiView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class FenLeiPresenter extends BasePresenter<FenLeiView> {

    //注入model层
    private FenLeiModelTask fenLeiModelTask;

    public FenLeiPresenter(FenLeiView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        fenLeiModelTask = new FenLeiModelTask();
    }

    public void getLeftData(){
        fenLeiModelTask.getLeftData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableSubscriber<LeftFenLeiBeans>() {
                            @Override
                            public void onNext(LeftFenLeiBeans leftFenLeiBeans) {
                                    if ("0".equals(leftFenLeiBeans.getCode())){
                                        if (view !=null){
                                            view.getLeftDataSuccess(leftFenLeiBeans);
                                        }
                                    }else {
                                        if (view!=null){
                                            view.getLeftFailed(leftFenLeiBeans.getMsg());
                                        }
                                    }
                            }

                            @Override
                            public void onError(Throwable t) {
                                    if (view!=null){
                                        view.getLeftFailed(t.toString());
                                    }
                            }

                            @Override
                            public void onComplete() {

                            }

                            Disposable disposable = new Disposable() {
                                @Override
                                public void dispose() {
                                    disposable.dispose();
                                }

                                @Override
                                public boolean isDisposed() {
                                    return false;
                                }
                            };

                        });
    }

    public void getRightData(String cid){
        fenLeiModelTask.getRightData(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<RightFenLeiBeans>() {
                    @Override
                    public void onNext(RightFenLeiBeans rightFenLeiBeans) {
                        if ("0".equals(rightFenLeiBeans.getCode())){
                            if (view !=null){
                                view.getRightDataSuccess(rightFenLeiBeans);
                            }
                        }else {
                            if (view!=null){
                                view.getRightFailed(rightFenLeiBeans.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (view!=null){
                            view.getRightFailed(t.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    Disposable disposable = new Disposable() {
                        @Override
                        public void dispose() {
                            disposable.dispose();
                        }

                        @Override
                        public boolean isDisposed() {
                            return false;
                        }
                    };

                });
    }
}
