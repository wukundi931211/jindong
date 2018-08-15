package com.baiwei.tianlong.jindong.mvp.setting.model;



import com.baiwei.tianlong.jindong.mvp.setting.model.beans.AddAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.AddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.SetAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UpdateAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UpdateNickNameBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UploadBean;
import com.baiwei.tianlong.jindong.network.ConstantApi;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/17 下午 3:44.
 * @Description This is ISettingApi.
 */
public interface ISettingApi {
    @GET(ConstantApi.UPDATE_NICKNAME)
    Observable<UpdateNickNameBean> updateNickName(@QueryMap Map<String, String> map);

    @Multipart
    @POST(ConstantApi.UPLOAD)
    Observable<UploadBean> uploadPhoto(@Query("uid") String uid, @Part MultipartBody.Part part);

    @GET(ConstantApi.GET_ADDRS)
    Observable<AddressBean> getAddressData(@Query("uid") String uid);

    @GET(ConstantApi.ADD_ADDR)
    Observable<AddAddressBean> addAddressData(@QueryMap Map<String, String> map);

    @GET(ConstantApi.SET_ADDR)
    Observable<SetAddressBean> setAddressData(@QueryMap Map<String, String> map);

    @GET(ConstantApi.UPDATE_ADDR)
    Observable<UpdateAddressBean> updateAddressData(@Query("uid") String uid, @Query("addrid") String addrid, @Query("addr") String addr, @Query("mobile") String mobile, @Query("name") String name);

}
