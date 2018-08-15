package com.baiwei.tianlong.jindong.mvp.setting.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;


import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseActivity;
import com.baiwei.tianlong.jindong.custom_view.MySearchView;
import com.baiwei.tianlong.jindong.mvp.setting.adapter.MyAddressListAdapter;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.AddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.SetAddressBean;
import com.baiwei.tianlong.jindong.mvp.setting.presenter.AddressPresenter;
import com.baiwei.tianlong.jindong.mvp.setting.view.iview.IAddressView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;



public class AddressActivity extends BaseActivity<AddressPresenter> implements IAddressView {
    private static final String TAG = "AddressActivity--";
    @BindView(R.id.mtv_address)
    MySearchView mtvAddress;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    @BindView(R.id.btn_addAddress_address)
    Button btnAddAddressAddress;
    private List<AddressBean.DataBean> list = new ArrayList<>();
    private MyAddressListAdapter myAddressListAdapter;
    private String uid;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected AddressPresenter providePresenter() {
        return new AddressPresenter(this);
    }

    @Override
    protected void initData() {
        super.initData();
        getDataFromSharedPreferences();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mtvAddress.setOnMyTitleViewClickListener(new MySearchView.OnMyTitleViewClickListener() {
            @Override
            public void leftClick() {
                Intent intent = new Intent();
                setResult(8, intent);
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
            presenter.getAddressData(uid);
        } else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getAddressSuccess(AddressBean addressBean) {
        List<AddressBean.DataBean> data = addressBean.getData();
        list.clear();
        list.addAll(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAddress.setLayoutManager(layoutManager);
        //设置适配器
        myAddressListAdapter = new MyAddressListAdapter(list);
        rvAddress.setAdapter(myAddressListAdapter);

        //点击事件
        myAddressListAdapter.setOnAddressListListener(new MyAddressListAdapter.OnAddressListListener() {
            @Override
            public void addressListCheckbox(int addrid) {
                Map<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("addrid", addrid + "");
                map.put("status", "1");
                presenter.setAddressData(map);

            }

            //编辑  调往编辑页面
            @Override
            public void addressListEdit(String addr, int addrid, long mobile, String name) {
                Intent intent = new Intent(AddressActivity.this, UpdateAddressActivity.class);
                intent.putExtra("address", addr);
                intent.putExtra("addrid", addrid + "");
                intent.putExtra("mobile", mobile + "");
                intent.putExtra("name", name);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public void getAddressFailed(String error) {
        Log.d(TAG, "getAddressFailed: " + error);
    }

    @Override
    public void setAddressSuccess(SetAddressBean setAddressBean) {
        Toast.makeText(AddressActivity.this, setAddressBean.getMsg(), Toast.LENGTH_SHORT).show();
        initData();
    }

    @Override
    public void setAddressFailed(String error) {
        Log.d(TAG, "setAddressFailed: " + error);
    }



    @OnClick(R.id.btn_addAddress_address)
    public void onViewClicked() {
        Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 8) {
            getDataFromSharedPreferences();
        }
    }

    @Override
    public Context cotext() {
        return this;
    }
}


