package com.baiwei.tianlong.jindong.mvp.home.view;

import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeBeans;
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeFenLeiBeans;

public interface HomeView extends View{
    //1.主页面数据 不包括 分类
    //成功
    void getHomeDataSuccess(HomeBeans homeBeans);
    //失败
    void getHomeDataFailed(String error);

    //2.分类
    void getHomeFenleiDataSuccess(HomeFenLeiBeans homeFenLeiBeans);
    //失败
    void getHomeFenleiFailed(String error);

}
