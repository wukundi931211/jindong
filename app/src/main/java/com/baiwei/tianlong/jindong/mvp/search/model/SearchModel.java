package com.baiwei.tianlong.jindong.mvp.search.model;

import com.baiwei.tianlong.jindong.mvp.search.model.beans.SearchBeans;
import com.baiwei.tianlong.jindong.utils.RetrofitcManager;

import io.reactivex.Flowable;

public class SearchModel  implements SearchIApi{


    @Override
    public Flowable<SearchBeans> getSearchProductsData(String keywords, String page, String sort) {
        return RetrofitcManager.getDefault().create(SearchIApi.class)
                .getSearchProductsData(keywords,page,sort);
    }
}
