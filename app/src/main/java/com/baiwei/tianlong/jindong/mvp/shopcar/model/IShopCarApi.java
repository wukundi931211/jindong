package com.baiwei.tianlong.jindong.mvp.shopcar.model;

import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.CreateOrderBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.DeleteCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.ShopCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.UpdateCarBeans;
import com.baiwei.tianlong.jindong.network.ConstantApi;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IShopCarApi {

    //购物车数据
    @GET(ConstantApi.SHOPCAR_CARTS)
   Flowable<ShopCarBeans> getShopCarData(@Query("uid") String uid);

    //修改购物车
    @GET(ConstantApi.SHOPCAR_UPDATECARTS)
   Flowable<UpdateCarBeans>updateCarData(@QueryMap Map<String, String> map);

    //为你推荐
    @GET(ConstantApi.SHOPCAR_AD)
   Flowable<HomeBeans>getShopCarAdData();

    //删除购物车
    @GET(ConstantApi.SHOPCAR_DELETECART)
   Flowable<DeleteCarBeans>deleteCarData(@QueryMap Map<String, String> map);

    //创建订单
    @GET(ConstantApi.CREATE_ORDER)
   Flowable<CreateOrderBeans>createOrder(@Query("uid") String uid, @Query("price") Float price);




}
