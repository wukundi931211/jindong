package com.baiwei.tianlong.jindong.mvp.setting.view.iview;


import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UpdateAddressBean;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/20 下午 12:04.
 * @Description This is IUpdateAddressView.
 */
public interface IUpdateAddressView extends View {
    void updateAddressSuccess(UpdateAddressBean updateAddressBean);

    void updateAddressFailed(String error);



}
