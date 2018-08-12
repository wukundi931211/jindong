package com.baiwei.tianlong.jindong.mvp.wode.model;

import android.text.GetChars;

import com.baiwei.tianlong.jindong.mvp.wode.model.beans.Logins;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.WoDeBeans;
import com.baiwei.tianlong.jindong.network.ConstantApi;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WoDeApi {
       //登录
       @FormUrlEncoded
       @POST(ConstantApi.LOGIN)
       Observable<Logins> login(@FieldMap Map<String,String> map);



       @GET(ConstantApi.WO_AD)
       Flowable<WoDeBeans> getWoDeData();
}
