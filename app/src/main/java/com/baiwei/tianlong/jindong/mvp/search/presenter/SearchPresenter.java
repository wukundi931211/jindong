package com.baiwei.tianlong.jindong.mvp.search.presenter;

import com.baiwei.tianlong.jindong.base.BasePresenter;
import com.baiwei.tianlong.jindong.mvp.search.model.SearchModel;
import com.baiwei.tianlong.jindong.mvp.search.model.beans.SearchBeans;
import com.baiwei.tianlong.jindong.mvp.search.view.SerchView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class SearchPresenter extends BasePresenter<SerchView>{

    //注入model
    private SearchModel searchModel;

    public SearchPresenter(SerchView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        searchModel = new SearchModel();
    }

    public void getSearchProductsData(String keywords, String page, String sort){
        searchModel.getSearchProductsData(keywords,page,sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<SearchBeans>() {
                    @Override
                    public void onNext(SearchBeans searchBeans) {
                        if ("0".equals(searchBeans.getCode())){
                            if (view!=null){
                                view.getSearchProductsDataSuccess(searchBeans);
                            }
                        }else{
                            if (view!=null){
                                view.getSearchProductsDataFailed(searchBeans.getMsg());
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (view!=null){
                            view.getSearchProductsDataFailed(t.toString());
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

}
