package com.baiwei.tianlong.jindong.mvp.setting.view.iview;


import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.AddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.SetAddressBean;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/20 上午 11:48.
 * @Description This is IAddressView.
 */
public interface IAddressView extends View {
    void getAddressSuccess(AddressBean addressBean);

    void getAddressFailed(String error);

    void setAddressSuccess(SetAddressBean setAddressBean);

    void setAddressFailed(String error);


}
