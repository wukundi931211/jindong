package com.baiwei.tianlong.jindong.mvp.show.presenter;

import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.show.model.ShowApiModel;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.AddCartBean;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.ProductBeans;
import com.baiwei.tianlong.jindong.mvp.show.view.ShowIView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class ShowPresenter extends BasePresenter<ShowIView> {

    //注入model

    private ShowApiModel showApiModel;

    public ShowPresenter(ShowIView view) {
        super(view);
    }


    @Override
    protected void initModel() {
        showApiModel = new ShowApiModel();
    }

    public void getShowShopData(final String pid){
        showApiModel.getProuduct(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<ProductBeans>() {
                    @Override
                    public void onNext(ProductBeans productBeans) {
                        if ("0".equals(productBeans.getCode())){
                            if (view!=null){
                                view.ShowProuductSuccess(productBeans);
                            }
                        }else {
                            if (view!=null){
                                view.ShowProuductFaildes(productBeans.getMsg());
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        if (view!=null){
                            view.ShowProuductFaildes(t.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getAddCartData(String uid ,String pid){
        showApiModel.getAddCartData(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<AddCartBean>() {
                    @Override
                    public void onNext(AddCartBean addCartBean) {
                        if ("0".equals(addCartBean.getCode())){
                            if (view!=null){
                                view.AddCartSuccess(addCartBean);
                            }
                        }else {
                            if (view!=null){
                                view.AddCartFaildes(addCartBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (view!=null){
                            view.AddCartFaildes(t.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
