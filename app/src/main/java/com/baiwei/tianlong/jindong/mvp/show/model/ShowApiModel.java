package com.baiwei.tianlong.jindong.mvp.show.model;

import com.baiwei.tianlong.jindong.mvp.show.model.beans.AddCartBean;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.ProductBeans;
import com.baiwei.tianlong.jindong.utils.RetrofitcManager;

import io.reactivex.Flowable;

public class ShowApiModel implements IShowApi{

    @Override
    public Flowable<ProductBeans> getProuduct(String pid) {
        return RetrofitcManager.getDefault().create(IShowApi.class).getProuduct(pid);
    }

    @Override
    public Flowable<AddCartBean> getAddCartData(String uid, String pid) {
        return RetrofitcManager.getDefault().create(IShowApi.class).getAddCartData(uid,pid);
    }
}
