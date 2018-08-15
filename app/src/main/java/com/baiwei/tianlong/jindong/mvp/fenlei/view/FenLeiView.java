package com.baiwei.tianlong.jindong.mvp.fenlei.view;

import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.LeftFenLeiBeans;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.RightFenLeiBeans;

public interface FenLeiView extends View {
    void getLeftDataSuccess(LeftFenLeiBeans leftFenLeiBeans);
    void getLeftFailed(String error);
    void getRightDataSuccess(RightFenLeiBeans rightFenLeiBeans);
    void getRightFailed(String error);
}
