package com.baiwei.tianlong.jindong.mvp.setting.view.iview;


import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.AddAddressBean;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/20 下午 4:12.
 * @Description This is IAddAddressView.
 */
public interface IAddAddressView extends View {
    void getAddAddressSuccess(AddAddressBean addAddressBean);

    void getAddAddressFailed(String error);
}
