package com.baiwei.tianlong.jindong.mvp.setting.presenter;



import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.setting.model.SettingModel;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.AddAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.view.iview.IAddAddressView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/20 下午 4:11.
 * @Description This is AddAddressPresenter.
 */
public class AddAddressPresenter extends BasePresenter<IAddAddressView> {

    private SettingModel settingModel;

    public AddAddressPresenter(IAddAddressView iAddAddressView) {
        super(iAddAddressView);
    }

    @Override
    protected void initModel() {
        settingModel = new SettingModel();
    }

    public void addAddressData(Map<String, String> map) {
        settingModel.addAddressData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(AddAddressBean addAddressBean) {
                        if ("0".equals(addAddressBean.getCode())) {
                            if (view != null) {
                                view.getAddAddressSuccess(addAddressBean);
                            }
                        } else {
                            if (view != null) {
                                view.getAddAddressFailed(addAddressBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.getAddAddressFailed(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
