package com.baiwei.tianlong.jindong.mvp.setting.presenter;



import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.setting.model.SettingModel;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UpdateAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.view.iview.IUpdateAddressView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/20 下午 12:04.
 * @Description This is UpdateAddressPresenter.
 */
public class UpdateAddressPresenter extends BasePresenter<IUpdateAddressView> {

    private SettingModel settingModel;

    public UpdateAddressPresenter(IUpdateAddressView iUpdateAddressView) {
        super(iUpdateAddressView);
    }

    @Override
    protected void initModel() {
        settingModel = new SettingModel();
    }

    public void updateAddressData(String uid, String addrid, String addr, String mobile, String name) {
        settingModel.updateAddressData(uid, addrid, addr, mobile, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UpdateAddressBean updateAddressBean) {
                        if ("0".equals(updateAddressBean.getCode())) {
                            if (view != null) {
                                view.updateAddressSuccess(updateAddressBean);
                            }
                        } else {
                            if (view != null) {
                                view.updateAddressFailed(updateAddressBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.updateAddressFailed(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
