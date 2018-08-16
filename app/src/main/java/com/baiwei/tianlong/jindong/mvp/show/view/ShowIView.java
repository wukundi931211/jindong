package com.baiwei.tianlong.jindong.mvp.show.view;

import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.AddCartBean;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.ProductBeans;

public interface ShowIView extends View{

    //展示数据

    void ShowProuductSuccess(ProductBeans productBeans);

    void ShowProuductFaildes(String error);


    //添加购物车
    void AddCartSuccess(AddCartBean addCartBean);

    void AddCartFaildes(String error);
}
