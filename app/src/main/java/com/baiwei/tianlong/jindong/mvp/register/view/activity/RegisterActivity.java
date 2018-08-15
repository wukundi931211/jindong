package com.baiwei.tianlong.jindong.mvp.register.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseActivity;
import com.baiwei.tianlong.jindong.custom_view.MySearchView;
import com.baiwei.tianlong.jindong.mvp.register.model.beans.RegisterBean;
import com.baiwei.tianlong.jindong.mvp.register.presenter.RegisterPresenter;
import com.baiwei.tianlong.jindong.mvp.register.view.iview.IRegisterView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterView {
    private static final String TAG = "RegisterActivity--";
    @BindView(R.id.ed_mobile_reg)
    EditText edMobileReg;
    @BindView(R.id.ed_password_reg)
    EditText edPasswordReg;
    @BindView(R.id.btn_now_reg)
    Button btnNowReg;
    @BindView(R.id.mtv_register)
    MySearchView mySearchView;
    private String mobile;
    private String password;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected RegisterPresenter providePresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mySearchView.setOnMyTitleViewClickListener(new MySearchView.OnMyTitleViewClickListener() {
                                                       @Override
                                                       public void leftClick() {
                                                           Intent intent = new Intent();
                                                           intent.putExtra("mobile", mobile);
                                                           intent.putExtra("password", password);
                                                           setResult(10, intent);
                                                           finish();
                                                       }

                                                       @Override
                                                       public void searhClick() {

                                                       }

                                                       @Override
                                                       public void rightClick() {

                                                       }
                                                   }
        );
    }

    @Override
    public void getRegDataSuccess(RegisterBean registerBean) {
        String msg = registerBean.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("mobile", mobile);
        intent.putExtra("password", password);
        setResult(10, intent);
        finish();
    }

    @Override
    public void getRegDataFailed(String error) {
        Log.d(TAG, "getRegDataFailed: " + error);
    }




    @OnClick({R.id.btn_now_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_now_reg:
                mobile = edMobileReg.getText().toString();
                password = edPasswordReg.getText().toString();
                Map<String, String> map = new HashMap<>();
                map.put("mobile", mobile);
                map.put("password", password);
                presenter.getRegData(map);
                break;
        }
    }

    @Override
    public Context cotext() {
        return this;
    }
}
