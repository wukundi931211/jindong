package com.baiwei.tianlong.jindong.mvp.search.view;

import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.search.model.beans.SearchBeans;

public interface Serchview extends View{
    void getSearchProductsDataSuccess(SearchBeans searchBeans);

    void getSearchProductsDataFailed(String error);
}
