package com.baiwei.tianlong.jindong.mvp.register.model;

import com.baiwei.tianlong.jindong.mvp.register.model.beans.RegisterBean;
import com.baiwei.tianlong.jindong.utils.RetrofitcManager;


import java.util.Map;

import io.reactivex.Observable;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/16 下午 4:50.
 * @Description This is RegisterModel.
 */
public class RegisterModel {
    public Observable<RegisterBean> getRegData(Map<String, String> map) {
        return RetrofitcManager.getDefault().create(IRegisterApi.class).reg(map);
    }
}
