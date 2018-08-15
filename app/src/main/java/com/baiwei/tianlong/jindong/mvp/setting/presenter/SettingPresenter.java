package com.baiwei.tianlong.jindong.mvp.setting.presenter;



import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.setting.model.SettingModel;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UpdateNickNameBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UploadBean;
import com.baiwei.tianlong.jindong.mvp.setting.view.iview.ISettingView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/16 下午 7:01.
 * @Description This is SettingPresenter.
 */
public class SettingPresenter extends BasePresenter<ISettingView> {

    private SettingModel settingModel;

    public SettingPresenter(ISettingView iSettingView) {
        super(iSettingView);
    }

    @Override
    protected void initModel() {
        settingModel = new SettingModel();
    }

    public void updateNickName(Map<String, String> map) {
        settingModel.updateNickName(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateNickNameBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UpdateNickNameBean updateNickNameBean) {
                        if ("0".equals(updateNickNameBean.getCode())) {
                            if (view != null) {
                                view.updateNickNameSuccess(updateNickNameBean);
                            }
                        } else {
                            if (view != null) {
                                view.updateNickNameFailed(updateNickNameBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.updateNickNameFailed(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void uploadPhoto(String uid, MultipartBody.Part part) {
        settingModel.uploadPhoto(uid, part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UploadBean uploadBean) {
                        if ("0".equals(uploadBean.getCode())) {
                            if (view != null) {
                                view.uploadPhotoSuccess(uploadBean);
                            }
                        } else {
                            if (view != null) {
                                view.uploadPhotoFailed(uploadBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.uploadPhotoFailed(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
