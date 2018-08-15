package com.baiwei.tianlong.jindong.mvp.setting.view.iview;


import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UpdateNickNameBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UploadBean;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/16 下午 7:16.
 * @Description This is ISettingView.
 */
public interface ISettingView extends View {
    void updateNickNameSuccess(UpdateNickNameBean updateNickNameBean);

    void updateNickNameFailed(String error);

    void uploadPhotoSuccess(UploadBean uploadBean);

    void uploadPhotoFailed(String error);

}
