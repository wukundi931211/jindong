package com.baiwei.tianlong.jindong.mvp.wode.model;

import com.baiwei.tianlong.jindong.mvp.wode.model.beans.HomeAdBean;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.LoginBean;
import com.baiwei.tianlong.jindong.utils.RetrofitcManager;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class MoDeModel implements WoDeApi{
    @Override
    public Observable<LoginBean> login(Map<String, String> map) {
        return RetrofitcManager.getDefault().create(WoDeApi.class).login(map);
    }

    @Override
    public Flowable<HomeAdBean> getWoDeData() {
        return RetrofitcManager.getDefault().create(WoDeApi.class).getWoDeData();
    }
}
