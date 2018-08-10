package com.baiwei.tianlong.jindong.mvp.home.model;

import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeBeans;
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeFenLeiBeans;
import com.baiwei.tianlong.jindong.network.ConstantApi;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HomeApi {

    //主页面数据
    @GET(ConstantApi.HOME_AD)
    Flowable<HomeBeans> getHomeData();


    //主页面分类的数据
    @GET(ConstantApi.HOME_CATAGORY)
    Flowable<HomeFenLeiBeans> getHomeFeileiData();

}
