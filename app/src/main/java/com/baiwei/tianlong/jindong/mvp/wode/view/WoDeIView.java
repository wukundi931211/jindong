package com.baiwei.tianlong.jindong.mvp.wode.view;

import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.HomeAdBean;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.LoginBean;


public interface WoDeIView extends View {

    //登录
    void getLoginDataSuccess(LoginBean loginBean);

    void getLoginDataFailed(String error);

    //我的页面为你推荐
    void getMyCenterDataSuccess(HomeAdBean homeAdBean);

    void getMyCenterDataFailed(String error);
}
