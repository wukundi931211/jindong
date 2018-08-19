package com.baiwei.tianlong.jindong.mvp.shopcar.presenter;

import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.ShopCarModel;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.CreateOrderBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.DeleteCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.ShopCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.UpdateCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.view.ShopView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class ShopCarPresenter extends BasePresenter<ShopView> {
    //注入model

    private ShopCarModel shopCarModel;

    public ShopCarPresenter(ShopView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        shopCarModel = new ShopCarModel();
    }


    public void getShopCarData(String uid){
        shopCarModel.getShopCarData(uid)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(new DisposableSubscriber<ShopCarBeans>() {
                         @Override
                         public void onNext(ShopCarBeans shopCarBeans) {
                             if ("0".equals(shopCarBeans.getCode())){
                                 if (view!=null){
                                     view.getShopCarDataSuccess(shopCarBeans);
                                 }
                             }else {
                                 if (view!=null){
                                    view.getShopCarDataFailed(shopCarBeans.getMsg());
                                 }
                             }
                         }

                         @Override
                         public void onError(Throwable t) {
                             if (view!=null){
                                 view.getShopCarDataFailed(t.toString());
                             }
                         }

                         @Override
                         public void onComplete() {

                         }
                     });
    }

    public void updateCarData(Map<String,String> map){
        shopCarModel.updateCarData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<UpdateCarBeans>() {
                    @Override
                    public void onNext(UpdateCarBeans updateCarBeans) {
                        if ("0".equals(updateCarBeans.getCode())){
                            if (view!=null){
                                view.updateCartsDataSuccess(updateCarBeans);
                            }
                        }else {
                            if (view!=null){
                                view.updateCartsDataFailed(updateCarBeans.getMsg());
                            }
                        }
                    }


                    @Override
                    public void onError(Throwable t) {
                        if (view!=null){
                            view.updateCartsDataFailed(t.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getShopCarAdData(){
        shopCarModel.getShopCarAdData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<HomeBeans>() {
                    @Override
                    public void onNext(HomeBeans homeBeans) {
                        if ("0".equals(homeBeans.getCode())){
                            if (view!=null){
                                view.getShopCarAdDataSuccess(homeBeans);
                            }
                        }else {
                            if (view!=null){
                                view.getShopCarAdDataFailed(homeBeans.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (view!=null){
                            view.getShopCarAdDataFailed(t.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deleteCartData(Map<String,String> map){
        shopCarModel.deleteCarData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<DeleteCarBeans>() {
                    @Override
                    public void onNext(DeleteCarBeans deleteCarBeans) {
                        if ("0".equals(deleteCarBeans.getCode())){
                            if (view!=null){
                                view.deleteCartDataSuccess(deleteCarBeans);
                            }
                        }else {
                            if (view!=null){
                                view.deleteCartDataFailed(deleteCarBeans.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (view!=null){
                            view.deleteCartDataFailed(t.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void createOrder(String uid,Float price){
        shopCarModel.createOrder(uid,price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<CreateOrderBeans>() {
                    @Override
                    public void onNext(CreateOrderBeans createOrderBeans) {
                        if ("0".equals(createOrderBeans.getCode())){
                            if (view!=null){
                                view.createOrderSuccess(createOrderBeans);
                            }
                        }else {
                            if (view!=null){
                                view.createOrderFailed(createOrderBeans.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (view!=null){
                            view.createOrderFailed(t.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
