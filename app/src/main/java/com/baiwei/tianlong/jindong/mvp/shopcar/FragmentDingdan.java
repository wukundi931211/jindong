package com.baiwei.tianlong.jindong.mvp.shopcar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseFragment;
import com.baiwei.tianlong.jindong.custom_view.MySearchView;
import com.baiwei.tianlong.jindong.mvp.dingdan.DingDan;
import com.baiwei.tianlong.jindong.mvp.fenlei.FragmentFenLei;
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeBeans;
import com.baiwei.tianlong.jindong.mvp.home.view.adapter.MyTuiJianAdapter;
import com.baiwei.tianlong.jindong.mvp.home.view.fragment.FragmentHome;
import com.baiwei.tianlong.jindong.mvp.main.MainActivity;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.CreateOrderBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.DeleteCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.ShopCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.UpdateCarBeans;
import com.baiwei.tianlong.jindong.mvp.shopcar.presenter.ShopCarPresenter;
import com.baiwei.tianlong.jindong.mvp.shopcar.view.MyExpandableListView;
import com.baiwei.tianlong.jindong.mvp.shopcar.view.ShopView;
import com.baiwei.tianlong.jindong.mvp.show.ShowActivity;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

public class FragmentDingdan extends BaseFragment<ShopCarPresenter> implements ShopView {
    private static final String TAG = "ShopCarFragment--";
    @BindView(R.id.mtv_shopCar)
    MySearchView mMtvShopCar;
    @BindView(R.id.btn_msg_shopCar)
    Button mBtnMsgShopCar;
    @BindView(R.id.ll_shopCar)
    LinearLayout mLlShopCar;
    @BindView(R.id.elv_shopCar)
    MyExpandableListView mElvShopCar;
    @BindView(R.id.tv_shopCar)
    TextView mTvShopCar;
    @BindView(R.id.rv_show_shopCar)
    RecyclerView mRvShowShopCar;
    @BindView(R.id.srl_shopCar)
    SmartRefreshLayout mSrlShopCar;
    @BindView(R.id.cb_allCheck_shopCar)
    CheckBox mCbAllCheckShopCar;
    @BindView(R.id.tv_allPrice_shopCar)
    TextView mTvAllPriceShopCar;
    @BindView(R.id.btn_allNumber_shopCar)
    Button mBtnAllNumberShopCar;
    @BindView(R.id.scroll)
    ScrollView scroll;
    Unbinder unbinder;


    private MyShopCarAdapter myShopCarAdapter;
    private List<ShopCarBeans.DataBean> list = new ArrayList<>();
    private String uid;
    private float allPrice;

    //单列 懒加载
    public static FragmentDingdan newInstance(String param1) {
        FragmentDingdan fragmentDingdan = new FragmentDingdan();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragmentDingdan.setArguments(args);
        return fragmentDingdan;
    }
    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideFragmentLayoutID() {
        return R.layout.fragment_dingdan;
    }


    @Override
    protected ShopCarPresenter providePresenter() {
        return new ShopCarPresenter(this);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mSrlShopCar.setRefreshHeader(new DeliveryHeader(getContext()));
        mSrlShopCar.setEnableLoadMore(false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getDataFromSharedPreferences();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.getShopCarAdData();
        getDataFromSharedPreferences();
    }

    private void getDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin) {
            uid = sharedPreferences.getString("uid", "");
            presenter.getShopCarData(uid);
        } else {

            Toast.makeText(getContext(), "请登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initListener() {
        super.initListener();


        mSrlShopCar.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getShopCarData(uid);
                refreshLayout.finishRefresh();
                if (myShopCarAdapter != null) {
                    myShopCarAdapter.notifyDataSetChanged();
                }
            }
        });

        mMtvShopCar.setOnMyTitleViewClickListener(new MySearchView.OnMyTitleViewClickListener() {
            @Override
            public void leftClick() {

            }

            @Override
            public void searhClick() {

            }

            @Override
            public void rightClick() {
                if (myShopCarAdapter != null) {
                    myShopCarAdapter.setDelButtonVisibility();
                    myShopCarAdapter.notifyDataSetChanged();
                }
            }
        });



        mElvShopCar.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int pid = list.get(groupPosition).getList().get(childPosition).getPid();
                Intent intent = new Intent(getContext(), ShowActivity.class);
                intent.putExtra("pid", "" + pid);
                startActivityForResult(intent, 1);
                return true;
            }
        });
    }


    @Override
    public void getShopCarDataSuccess(ShopCarBeans shopCarBean) {
        List<ShopCarBeans.DataBean> data = shopCarBean.getData();
        list.clear();
        list.addAll(data);
        //设置适配器
        myShopCarAdapter = new MyShopCarAdapter(list);
        if (mElvShopCar != null) {
            mElvShopCar.setAdapter(myShopCarAdapter);
            //默认张开
            for (int i = 0; i < data.size(); i++) {
                mElvShopCar.expandGroup(i);
            }
            //去掉下箭头
            mElvShopCar.setGroupIndicator(null);

            myShopCarAdapter.setOnShopCarListChangeListener(new MyShopCarAdapter.OnShopCarListChangeListener() {
                @Override
                public void SellerSelectedChange(int groupPosition) {
                    //商家选中的状态改变
                    if (myShopCarAdapter != null) {
                        boolean currentSellerAllProductSelected = myShopCarAdapter.isCurrentSellerAllProductSelected(groupPosition);
                        myShopCarAdapter.changeCurrentSellerAllProductSelected(!currentSellerAllProductSelected, groupPosition);
                        myShopCarAdapter.notifyDataSetChanged();
                        //刷新底部数据
                        RefreshAllProductsAndAllPriceAndAllNumber();
                        //刷新线上商家选中状态
                        refreshCurrentSellerSelectedOnLine(!currentSellerAllProductSelected, groupPosition);
                    }
                }

                @Override
                public void ProductSelectedChange(int groupPosition, int childPosition) {
                    //商品选中状态改变
                    if (myShopCarAdapter != null) {
                        boolean currentProductSelected = myShopCarAdapter.isCurrentProductSelected(groupPosition, childPosition);
                        myShopCarAdapter.changeCurrentProductSelected(groupPosition, childPosition, !currentProductSelected);
                        myShopCarAdapter.notifyDataSetChanged();
                        RefreshAllProductsAndAllPriceAndAllNumber();
                        //刷新线上商品选中的状态
                        refreshCurrentProductSelectedOnLine(groupPosition, childPosition, !currentProductSelected);
                    }
                }

                @Override
                public void ProductNumberChange(int groupPosition, int childPosition, int number) {
                    //商品数量的改变
                    if (myShopCarAdapter != null) {
                        myShopCarAdapter.changeCurrentProductNumber(groupPosition, childPosition, number);
                        myShopCarAdapter.notifyDataSetChanged();
                    }
                    RefreshAllProductsAndAllPriceAndAllNumber();
                    //刷新线上商品数量
                    refreshCurrentProductNumberOnLine(groupPosition, childPosition, number);
                }

                @Override
                public void DeleteCurrentProduct(int groupPosition, int childPosition, int pid) {
                    //删除单前指定商品调用接口
                    Log.d(TAG, "DeleteCurrentProduct: " + groupPosition + "......." + childPosition);
                    Log.d(TAG, "DeleteCurrentProduct: " + pid);
                    Map<String, String> map = new HashMap<>();
                    map.put("uid", uid);
                    map.put("pid", "" + pid);
                    presenter.deleteCartData(map);
                }
            });
        }
    }


    //刷新线上商家选中状态
    private void refreshCurrentSellerSelectedOnLine(boolean b, int groupPosition) {
        String sellerid = list.get(groupPosition).getSellerid();
        List<ShopCarBeans.DataBean.ListBean> list1 = list.get(groupPosition).getList();
        for (int i = 0; i < list1.size(); i++) {
            Map<String, String> map = new HashMap<>();
            int pid = list1.get(i).getPid();
            int num = list1.get(i).getNum();
            int selected = list1.get(i).getSelected();
            map.put("uid", uid);
            map.put("sellerid", sellerid + "");
            map.put("pid", pid + "");
            map.put("num", num + "");
            map.put("selected", selected + "");
            presenter.updateCarData(map);

        }
    }

    //刷新线上商品选中状态
    private void refreshCurrentProductSelectedOnLine(int groupPosition, int childPosition, boolean b) {
        String sellerid = list.get(groupPosition).getSellerid();
        ShopCarBeans.DataBean.ListBean listBean = list.get(groupPosition).getList().get(childPosition);
        int pid = listBean.getPid();
        int num = listBean.getNum();
        int selected = b ? 1 : 0;
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("sellerid", sellerid + "");
        map.put("pid", pid + "");
        map.put("num", num + "");
        map.put("selected", selected + "");
        presenter.updateCarData(map);
    }

    //刷新线上商品数量
    private void refreshCurrentProductNumberOnLine(int groupPosition, int childPosition, int number) {
        String sellerid = list.get(groupPosition).getSellerid();
        ShopCarBeans.DataBean.ListBean listBean = list.get(groupPosition).getList().get(childPosition);
        int pid = listBean.getPid();
        int num = number;
        int selected = listBean.getSelected() == 1 ? 1 : 0;
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("sellerid", sellerid + "");
        map.put("pid", pid + "");
        map.put("num", num + "");
        map.put("selected", selected + "");
        presenter.updateCarData(map);
    }


    private void RefreshAllProductsAndAllPriceAndAllNumber() {
        if (myShopCarAdapter != null) {
            //全选按钮
            boolean allProductsSelected = myShopCarAdapter.isAllProductsSelected();
            mCbAllCheckShopCar.setChecked(allProductsSelected);
            //总价
            allPrice = myShopCarAdapter.calculateAllPrice();
            mTvAllPriceShopCar.setText("￥" + allPrice);
            //结算
            int allNumber = myShopCarAdapter.calculateAllNumber();
            mBtnAllNumberShopCar.setText("去结算(" + allNumber + ")");
        }

    }

    @Override
    public void getShopCarDataFailed(String error) {
        Log.d(TAG, "getShopCarDataFailed: " + error);
        //处理购物车数据是空的，但还显示商家名的问题
        if (myShopCarAdapter != null) {
            list.clear();
            myShopCarAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateCartsDataSuccess(UpdateCarBeans updateCartsBean) {
        Log.d(TAG, "updateCartsDataSuccess: " + updateCartsBean.getMsg());
    }

    @Override
    public void updateCartsDataFailed(String error) {
        Log.d(TAG, "updateCartsDataFailed: " + error);
    }

    @Override
    public void getShopCarAdDataSuccess(HomeBeans homeBean) {
        final List<HomeBeans.TuijianBean.ListBean> list1 = homeBean.getTuijian().getList();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        MyTuiJianAdapter myTuiJianAdapter = new MyTuiJianAdapter(list1, getActivity());
        if (mRvShowShopCar != null) {
            mRvShowShopCar.setLayoutManager(layoutManager);
            mRvShowShopCar.setAdapter(myTuiJianAdapter);
        }


        //条目点击事件
        myTuiJianAdapter.setOnItemClickListener(new MyTuiJianAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                int pid = list1.get(position).getPid();
                Log.d(TAG, "OnItemClick: " + pid);
                Intent intent = new Intent(getContext(), ShowActivity.class);
                intent.putExtra("pid", "" + pid);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void getShopCarAdDataFailed(String error) {
        Log.d(TAG, "getShopCarAdDataFailed: " + error);
    }

    @Override
    public void deleteCartDataSuccess(DeleteCarBeans deleteCartBean) {
        Log.d(TAG, "deleteCartDataSuccess: " + deleteCartBean.getMsg());
        presenter.getShopCarData(uid);
    }

    @Override
    public void deleteCartDataFailed(String error) {
        Log.d(TAG, "deleteCartDataFailed: " + error);
    }

    @Override
    public void createOrderSuccess(CreateOrderBeans createOrderBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("提示框");
        builder.setMessage(createOrderBean.getMsg() + ",点击继续支付订单");
        builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getContext(), DingDan.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @Override
    public void createOrderFailed(String error) {
        Log.d(TAG, "createOrderFailed: " + error);
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }



    @Override
    public Context cotext() {
        return getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cb_allCheck_shopCar, R.id.btn_allNumber_shopCar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_allCheck_shopCar:
                if (myShopCarAdapter != null) {
                    boolean allProductsSelected = myShopCarAdapter.isAllProductsSelected();
                    myShopCarAdapter.changeAllProductSelected(!allProductsSelected);
                    myShopCarAdapter.notifyDataSetChanged();
                }

                RefreshAllProductsAndAllPriceAndAllNumber();
                break;
            case R.id.btn_allNumber_shopCar:
                break;
        }
    }
}
