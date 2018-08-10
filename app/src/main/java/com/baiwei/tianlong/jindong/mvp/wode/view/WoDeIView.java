package com.baiwei.tianlong.jindong.mvp.wode.view;

import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.Logins;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.WoDeBeans;

public interface WoDeIView extends View {

    //登录
    void getLoginDataSuccess(Logins logins);

    void getLoginDataFailed(String error);

    //我的页面为你推荐
    void getWoDeDataSuccess(WoDeBeans woDeBeans);

    void getWoDeDataError(String error);
}
