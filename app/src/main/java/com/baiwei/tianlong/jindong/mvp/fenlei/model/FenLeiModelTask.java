package com.baiwei.tianlong.jindong.mvp.fenlei.model;

import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.LeftFenLeiBeans;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.RightFenLeiBeans;
import com.baiwei.tianlong.jindong.utils.RetrofitcManager;

import io.reactivex.Flowable;

public class FenLeiModelTask implements FenLeiIModelApi {
    @Override
    public Flowable<LeftFenLeiBeans> getLeftData() {
        return RetrofitcManager.getDefault().create(FenLeiIModelApi.class).getLeftData();
    }

    @Override
    public Flowable<RightFenLeiBeans> getRightData(String cid) {
        return RetrofitcManager.getDefault().create(FenLeiIModelApi.class).getRightData(cid);
    }
}
