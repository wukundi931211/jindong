package com.baiwei.tianlong.jindong.mvp.search.model;

import com.baiwei.tianlong.jindong.mvp.search.model.beans.SearchBeans;
import com.baiwei.tianlong.jindong.network.ConstantApi;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchIApi {
    @GET(ConstantApi.SEARCH_PRODUCTS)
    Flowable<SearchBeans> getSearchProductsData(
         @Query("keywords")String keywords
    );
}
