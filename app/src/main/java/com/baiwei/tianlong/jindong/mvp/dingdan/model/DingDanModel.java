package com.baiwei.tianlong.jindong.mvp.dingdan.model;

import com.baiwei.tianlong.jindong.mvp.dingdan.model.beans.QingQiuMoRenDiZhi;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.ProductBeans;
import com.baiwei.tianlong.jindong.utils.RetrofitcManager;

import io.reactivex.Flowable;

public class DingDanModel implements DingDanIApi{
    //默认地址
    @Override
    public Flowable<QingQiuMoRenDiZhi> getDefaultAddr(String uid) {
        return RetrofitcManager.getDefault().create(DingDanIApi.class).getDefaultAddr(uid);
    }
    //
    @Override
    public Flowable<ProductBeans> getProuduct(String pid) {
        return RetrofitcManager.getDefault().create(DingDanIApi.class).getProuduct(pid);
    }
}
