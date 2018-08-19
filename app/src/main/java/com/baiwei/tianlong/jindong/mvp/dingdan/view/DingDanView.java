package com.baiwei.tianlong.jindong.mvp.dingdan.view;

import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.dingdan.model.beans.QingQiuMoRenDiZhi;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.ProductBeans;

public interface DingDanView extends View{




    //默认地址
    void ShowAddDefaultAddrSuccess(QingQiuMoRenDiZhi qingQiuMoRenDiZhi);

    void ShowAddDefaultAddrFaildes(String error);





    //展示数据

    void ShowProuductSuccess(ProductBeans productBeans);

    void ShowProuductFaildes(String error);
}
