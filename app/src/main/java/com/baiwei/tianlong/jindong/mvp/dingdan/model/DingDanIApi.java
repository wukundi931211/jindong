package com.baiwei.tianlong.jindong.mvp.dingdan.model;

import com.baiwei.tianlong.jindong.mvp.dingdan.model.beans.QingQiuMoRenDiZhi;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.ProductBeans;
import com.baiwei.tianlong.jindong.network.ConstantApi;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DingDanIApi {
    @GET(ConstantApi.GET_DEFAULT_ADDR)
    Flowable<QingQiuMoRenDiZhi> getDefaultAddr(@Query("uid") String uid);

    //商品信息
    @GET(ConstantApi.PRODUCT_INFO)
    Flowable<ProductBeans> getProuduct(@Query("pid") String pid);
}
