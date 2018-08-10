package com.baiwei.tianlong.jindong.mvp.home.model;

import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeBeans;
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeFenLeiBeans;
import com.baiwei.tianlong.jindong.utils.RetrofitcManager;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class HomeModel implements HomeApi{


    public Flowable<HomeBeans> getHomeData() {
        //返回值直接注入 封装的Retrofitc+Rxjava得到主页面数据
        return RetrofitcManager.getDefault().create(HomeApi.class).getHomeData();
    }


    public Flowable<HomeFenLeiBeans> getHomeFeileiData() {
        return RetrofitcManager.getDefault().create(HomeApi.class).getHomeFeileiData();
    }
}
