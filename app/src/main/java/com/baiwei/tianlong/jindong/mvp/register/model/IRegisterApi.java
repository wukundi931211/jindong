package com.baiwei.tianlong.jindong.mvp.register.model;


import com.baiwei.tianlong.jindong.mvp.register.model.beans.RegisterBean;
import com.baiwei.tianlong.jindong.network.ConstantApi;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/16 下午 4:43.
 * @Description This is IRegisterApi.
 */
public interface IRegisterApi {
    @FormUrlEncoded
    @POST(ConstantApi.REG)
    Observable<RegisterBean> reg(@FieldMap Map<String, String> map);
}
