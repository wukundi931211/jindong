package com.baiwei.tianlong.jindong.mvp.wode.model;

import com.baiwei.tianlong.jindong.mvp.wode.model.beans.Logins;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.WoDeBeans;
import com.baiwei.tianlong.jindong.utils.RetrofitcManager;

import java.util.Map;

import io.reactivex.Flowable;

public class MoDeModel implements WoDeApi{
    @Override
    public Flowable<Logins> login(Map<String, String> map) {
        return RetrofitcManager.getDefault().create(WoDeApi.class).login(map);
    }

    @Override
    public Flowable<WoDeBeans> getWoDeData() {
        return RetrofitcManager.getDefault().create(WoDeApi.class).getWoDeData();
    }
}
