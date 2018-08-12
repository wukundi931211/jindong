package com.baiwei.tianlong.jindong.mvp.login.model;


import com.baiwei.tianlong.jindong.mvp.login.model.beans.LoginsActivityBeans;
import com.baiwei.tianlong.jindong.mvp.wode.model.WoDeApi;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.Logins;
import com.baiwei.tianlong.jindong.utils.RetrofitcManager;

import java.util.Map;

import io.reactivex.Observable;

public class LoginModel implements LoginApi {
    @Override
    public Observable<LoginsActivityBeans> login(Map<String, String> map) {
        return RetrofitcManager.getDefault().create(LoginApi.class).login(map);
    }
}
