package com.baiwei.tianlong.jindong.mvp.setting.presenter;



import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.setting.model.SettingModel;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.AddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.SetAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.view.iview.IAddressView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/20 上午 11:48.
 * @Description This is AddressPresenter.
 */
public class AddressPresenter extends BasePresenter<IAddressView> {

    private SettingModel settingModel;

    public AddressPresenter(IAddressView iAddressView) {
        super(iAddressView);
    }

    @Override
    protected void initModel() {
        settingModel = new SettingModel();
    }

    public void getAddressData(String uid) {
        settingModel.getAddressData(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(AddressBean addressBean) {
                        if ("0".equals(addressBean.getCode())) {
                            if (view != null) {
                                view.getAddressSuccess(addressBean);
                            }
                        } else {
                            if (view != null) {
                                view.getAddressFailed(addressBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.getAddressFailed(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setAddressData(Map<String, String> map) {
        settingModel.setAddressData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SetAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SetAddressBean setAddressBean) {
                        if ("0".equals(setAddressBean.getCode())) {
                            if (view != null) {
                                view.setAddressSuccess(setAddressBean);
                            }
                        } else {
                            if (view != null) {
                                view.setAddressFailed(setAddressBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.setAddressFailed(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
