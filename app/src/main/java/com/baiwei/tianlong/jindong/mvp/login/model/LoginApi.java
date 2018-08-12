package com.baiwei.tianlong.jindong.mvp.login.model;


import com.baiwei.tianlong.jindong.mvp.login.model.beans.LoginsActivityBeans;
import com.baiwei.tianlong.jindong.network.ConstantApi;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {
    //登录
    @FormUrlEncoded
    @POST(ConstantApi.LOGIN)
    Observable<LoginsActivityBeans> login(@FieldMap Map<String,String> map);


}
