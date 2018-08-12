package com.baiwei.tianlong.jindong.mvp.fenlei.model;

import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.LeftFenLeiBeans;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.RightFenLeiBeans;
import com.baiwei.tianlong.jindong.network.ConstantApi;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FenLeiIModelApi {

    @GET(ConstantApi.FEILEI_CATAGORY)
    Flowable<LeftFenLeiBeans> getLeftData();

    @GET(ConstantApi.FEILEI_PRODUCT)
    Flowable<RightFenLeiBeans> getRightData(@Query("cid") String cid);
}
