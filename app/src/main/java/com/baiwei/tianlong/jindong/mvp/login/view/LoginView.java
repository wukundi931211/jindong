package com.baiwei.tianlong.jindong.mvp.login.view;

import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.login.model.beans.LoginsActivityBeans;


public interface LoginView extends View{
    //登录
    void getLoginDataSuccess(LoginsActivityBeans logins);

    void getLoginDataFailed(String error);
}
