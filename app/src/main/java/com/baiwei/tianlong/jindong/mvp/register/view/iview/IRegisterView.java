package com.baiwei.tianlong.jindong.mvp.register.view.iview;


import com.baiwei.tianlong.jindong.base.View;
import com.baiwei.tianlong.jindong.mvp.register.model.beans.RegisterBean;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/16 下午 4:51.
 * @Description This is IRegisterView.
 */
public interface IRegisterView extends View {
    void getRegDataSuccess(RegisterBean registerBean);

    void getRegDataFailed(String error);
}
