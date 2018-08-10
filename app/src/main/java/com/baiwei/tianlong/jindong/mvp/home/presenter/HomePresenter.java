package com.baiwei.tianlong.jindong.mvp.home.presenter;

import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.home.model.HomeModel;
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeBeans;
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeFenLeiBeans;
import com.baiwei.tianlong.jindong.mvp.home.view.HomeView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class HomePresenter extends BasePresenter<HomeView> {

    //注入model
    private HomeModel homeModel;

    //注入view层
    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    public void getHomeData(){
        homeModel.getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<HomeBeans>() {
                    @Override
                    public void onNext(HomeBeans homeBeans) {
                        if ("0".equals(homeBeans.getCode())){
                            if (view!=null){
                                view.getHomeDataSuccess(homeBeans);
                            }
                        }else {
                            if (view!=null){
                                view.getHomeDataFailed(homeBeans.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (view!=null){
                            view.getHomeDataFailed(t.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getHomeFeileiData(){
        homeModel.getHomeFeileiData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<HomeFenLeiBeans>() {


                    @Override
                    public void onNext(HomeFenLeiBeans homeFenLeiBeans) {
                        if ("0".equals(homeFenLeiBeans.getCode())){
                            if (view!=null){
                                view.getHomeFenleiDataSuccess(homeFenLeiBeans);
                            }
                        }else {
                            if (view!=null){
                                view.getHomeFenleiFailed(homeFenLeiBeans.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.getHomeDataFailed(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
