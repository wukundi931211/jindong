package com.baiwei.tianlong.jindong.mvp.dingdan.presenter;

import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.dingdan.model.DingDanModel;
import com.baiwei.tianlong.jindong.mvp.dingdan.model.beans.QingQiuMoRenDiZhi;
import com.baiwei.tianlong.jindong.mvp.dingdan.view.DingDanView;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.ProductBeans;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class DingDanPresenter extends BasePresenter<DingDanView>{

    private DingDanModel dingDanModel;

    public DingDanPresenter(DingDanView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        dingDanModel = new DingDanModel();
    }

    public void getAddDefaultAddr(String uid){
        dingDanModel.getDefaultAddr(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<QingQiuMoRenDiZhi>() {
                    @Override
                    public void onNext(QingQiuMoRenDiZhi qingQiuMoRenDiZhi) {
                        if ("0".equals(qingQiuMoRenDiZhi.getCode())){
                            if (view!=null){
                                view.ShowAddDefaultAddrSuccess(qingQiuMoRenDiZhi);
                            }
                        }else {
                            if (view!=null){
                                view.ShowAddDefaultAddrFaildes(qingQiuMoRenDiZhi.getMsg());
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (view!=null){
                            view.ShowAddDefaultAddrFaildes(t.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                    Disposable disposable = new Disposable() {
                        @Override
                        public void dispose() {
                            disposable.dispose();
                        }

                        @Override
                        public boolean isDisposed() {
                            return false;
                        }
                    };
                });
    }


    public void getShowShopData(String pid){
        dingDanModel.getProuduct(pid)
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
}
