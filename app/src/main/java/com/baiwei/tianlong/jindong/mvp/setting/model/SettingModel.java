package com.baiwei.tianlong.jindong.mvp.setting.model;



import com.baiwei.tianlong.jindong.mvp.setting.model.beans.AddAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.AddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.SetAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UpdateAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UpdateNickNameBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UploadBean;
import com.baiwei.tianlong.jindong.utils.RetrofitcManager;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/16 下午 7:01.
 * @Description This is SettingModel.
 */
public class SettingModel {
    public Observable<UpdateNickNameBean> updateNickName(Map<String, String> map) {
        return RetrofitcManager.getDefault().create(ISettingApi.class).updateNickName(map);
    }

    public Observable<UploadBean> uploadPhoto(String uid, MultipartBody.Part part) {
        return RetrofitcManager.getDefault().create(ISettingApi.class).uploadPhoto(uid, part);
    }

    //获取订单列表
    public Observable<AddressBean> getAddressData(String uid) {
        return RetrofitcManager.getDefault().create(ISettingApi.class).getAddressData(uid);
    }
    //添加订单
    public Observable<AddAddressBean> addAddressData(Map<String, String> map) {
        return RetrofitcManager.getDefault().create(ISettingApi.class).addAddressData(map);
    }
    //修改订单
    public Observable<UpdateAddressBean> updateAddressData(String uid, String addrid, String addr, String mobile, String name) {
        return RetrofitcManager.getDefault().create(ISettingApi.class).updateAddressData(uid, addrid, addr, mobile, name);
    }

    //设置默认地址
    public Observable<SetAddressBean> setAddressData(Map<String, String> map){
        return RetrofitcManager.getDefault().create(ISettingApi.class).setAddressData(map);
    }
}
