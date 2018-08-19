package com.baiwei.tianlong.jindong.mvp.shopcar.model;

import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.CreateOrderBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.DeleteCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.ShopCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.UpdateCarBeans;
import com.baiwei.tianlong.jindong.utils.RetrofitcManager;

import java.util.Map;

import io.reactivex.Flowable;

public class ShopCarModel implements IShopCarApi{
    @Override
    public Flowable<ShopCarBeans> getShopCarData(String uid) {
        return RetrofitcManager.getDefault().create(IShopCarApi.class).getShopCarData(uid);
    }

    @Override
    public Flowable<UpdateCarBeans> updateCarData(Map<String, String> map) {
        return RetrofitcManager.getDefault().create(IShopCarApi.class).updateCarData(map);
    }

    @Override
    public Flowable<HomeBeans> getShopCarAdData() {
        return RetrofitcManager.getDefault().create(IShopCarApi.class).getShopCarAdData();
    }

    @Override
    public Flowable<DeleteCarBeans> deleteCarData(Map<String, String> map) {
        return RetrofitcManager.getDefault().create(IShopCarApi.class).deleteCarData(map);
    }

    @Override
    public Flowable<CreateOrderBeans> createOrder(String uid, Float price) {
        return RetrofitcManager.getDefault().create(IShopCarApi.class).createOrder(uid,price);
    }
}
