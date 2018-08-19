package com.baiwei.tianlong.jindong.mvp.shopcar.view;

import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.CreateOrderBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.DeleteCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.ShopCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.UpdateCarBeans;



public interface ShopView extends View{
    //购物车
    void getShopCarDataSuccess(ShopCarBeans shopCarBean);

    void getShopCarDataFailed(String error);


    //修改购物车
    void updateCartsDataSuccess(UpdateCarBeans updateCartsBean);

    void updateCartsDataFailed(String error);

    //推荐数据
    void getShopCarAdDataSuccess(HomeBeans homeBeans);

    void getShopCarAdDataFailed(String error);


    //删除购物车
    void deleteCartDataSuccess(DeleteCarBeans deleteCartBean);

    void deleteCartDataFailed(String error);


    //添加订单
    void createOrderSuccess(CreateOrderBeans createOrderBean);

    void createOrderFailed(String error);
}
