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
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UpdateAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.presenter.UpdateAddressPresenter;
import com.baiwei.tianlong.jindong.mvp.setting.view.iview.IUpdateAddressView;


import butterknife.BindView;
import butterknife.OnClick;



public class UpdateAddressActivity extends BaseActivity<UpdateAddressPresenter> implements IUpdateAddressView {
    private static final String TAG = "UpdateAddressActivity--";
    @BindView(R.id.mtv_update_address)
    MySearchView mtvUpdateAddress;
    @BindView(R.id.et_name_update_address)
    EditText etNameUpdateAddress;
    @BindView(R.id.et_mobile_update_address)
    EditText etMobileUpdateAddress;
    @BindView(R.id.btn_submit_update_address)
    Button btnSubmitUpdateAddress;
    @BindView(R.id.et_address_update_address)
    EditText etAddressUpdateAddress;
    private String uid;
    private String addrid;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_update_address;
    }

    @Override
    protected UpdateAddressPresenter providePresenter() {
        return new UpdateAddressPresenter(this);
    }

    @Override
    protected void initData() {
        super.initData();
        getDataFromSharedPreferences();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String mobile = intent.getStringExtra("mobile");
        String address = intent.getStringExtra("address");
        addrid = intent.getStringExtra("addrid");

        etNameUpdateAddress.setText(name);
        etMobileUpdateAddress.setText(mobile);
        etAddressUpdateAddress.setText(address);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mtvUpdateAddress.setOnMyTitleViewClickListener(new MySearchView.OnMyTitleViewClickListener() {
            @Override
            public void leftClick() {
                Intent intent = new Intent();
                setResult(3, intent);
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
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateAddressSuccess(UpdateAddressBean updateAddressBean) {
        Toast.makeText(UpdateAddressActivity.this, updateAddressBean.getMsg(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        setResult(8, intent);
        finish();
    }

    @Override
    public void updateAddressFailed(String error) {
        Log.d(TAG, "updateAddressFailed: " + error);
        Toast.makeText(UpdateAddressActivity.this, "更新失败：请检查手机号", Toast.LENGTH_SHORT).show();

    }





    @OnClick(R.id.btn_submit_update_address)
    public void onViewClicked() {
        String name = etNameUpdateAddress.getText().toString();
        String mobile = etMobileUpdateAddress.getText().toString();
        String address = etAddressUpdateAddress.getText().toString();

        presenter.updateAddressData(uid, addrid, address, mobile, name);
    }

    @Override
    public Context cotext() {
        return this;
    }
}
