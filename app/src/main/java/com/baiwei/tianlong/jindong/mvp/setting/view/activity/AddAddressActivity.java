package com.baiwei.tianlong.jindong.mvp.setting.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseActivity;
import com.baiwei.tianlong.jindong.custom_view.MySearchView;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.AddAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.presenter.AddAddressPresenter;
import com.baiwei.tianlong.jindong.mvp.setting.view.iview.IAddAddressView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.umeng.socialize.utils.ContextUtil.getContext;

public class AddAddressActivity extends BaseActivity<AddAddressPresenter> implements IAddAddressView {
    private static final String TAG = "AddAddressActivity--";
    @BindView(R.id.mtv_add_address)
    MySearchView mtvAddAddress;
    @BindView(R.id.et_name_add_address)
    EditText etNameAddAddress;
    @BindView(R.id.et_mobile_add_address)
    EditText etMobileAddAddress;
    @BindView(R.id.et_address_add_address)
    EditText etAddressAddAddress;
    @BindView(R.id.btn_submit_add_address)
    Button btnSubmitAddAddress;
    private String uid;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected AddAddressPresenter providePresenter() {
        return new AddAddressPresenter(this);
    }

    @Override
    protected void initData() {
        super.initData();
        getDataFromSharedPreferences();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mtvAddAddress.setOnMyTitleViewClickListener(new MySearchView.OnMyTitleViewClickListener() {
            @Override
            public void leftClick() {
                Intent intent = new Intent();
                setResult(36, intent);
                finish();
            }

            @Override
            public void searhClick() {

            }

            @Override
            public void rightClick() {

            }
        });
    }

    //获取数据
    private void getDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin) {
            uid = sharedPreferences.getString("uid", "");

        } else {
            Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getAddAddressSuccess(AddAddressBean addAddressBean) {
        Toast.makeText(AddAddressActivity.this, addAddressBean.getMsg(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        setResult(8, intent);
        finish();
    }

    @Override
    public void getAddAddressFailed(String error) {
        Log.d(TAG, "getAddAddressFailed: " + error);
    }




    @OnClick(R.id.btn_submit_add_address)
    public void onViewClicked() {
        String name = etNameAddAddress.getText().toString();
        String mobile = etMobileAddAddress.getText().toString();
        String address = etAddressAddAddress.getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("addr", address);
        map.put("mobile", mobile);
        map.put("name", name);
        presenter.addAddressData(map);

    }

    @Override
    public Context cotext() {
        return this;
    }
}
