package com.baiwei.tianlong.jindong.mvp.show.model;


import com.baiwei.tianlong.jindong.mvp.show.model.beans.AddCartBean;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.ProductBeans;
import com.baiwei.tianlong.jindong.network.ConstantApi;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IShowApi {
     //商品信息
     @GET(ConstantApi.PRODUCT_INFO)
     Flowable<ProductBeans> getProuduct(@Query("pid") String pid);


     @GET(ConstantApi.PRODUCT_ADDCART)
     Flowable<AddCartBean> getAddCartData(@Query("uid") String uid, @Query("pid") String pid);
}
