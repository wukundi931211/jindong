package com.baiwei.tianlong.jindong.mvp.wode.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseFragment;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.Logins;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.WoDeBeans;
import com.baiwei.tianlong.jindong.mvp.wode.presenter.WoDePresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.header.DropboxHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentWoDe extends BaseFragment<WoDePresenter> implements WoDeIView {

    private static final String TAG = "MyCenterFragment--";
    @BindView(R.id.btn_set_login)
    Button btnSetLogin;
    @BindView(R.id.btn_msg_login)
    Button btnMsgLogin;
    @BindView(R.id.user_my_login_image)
    SimpleDraweeView userMyLoginImage;
    @BindView(R.id.user_login)
    TextView userLogin;
    @BindView(R.id.login)
    LinearLayout login;
    @BindView(R.id.ll_dfk_login)
    LinearLayout llDfkLogin;
    @BindView(R.id.ll_d_login)
    LinearLayout llDLogin;
    @BindView(R.id.ll_dpj_login)
    LinearLayout llDpjLogin;
    @BindView(R.id.ll_th_sh_login)
    LinearLayout llThShLogin;
    @BindView(R.id.ll_mydd_login)
    LinearLayout llMyddLogin;
    @BindView(R.id.ll_sqgz_login)
    LinearLayout llSqgzLogin;
    @BindView(R.id.rv_show_login)
    RecyclerView rvShowLogin;
    Unbinder unbinder;
    @BindView(R.id.wode)
    SmartRefreshLayout wode;

    public static FragmentWoDe newInstance(String param1) {
        FragmentWoDe fragmentWoDe = new FragmentWoDe();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragmentWoDe.setArguments(args);
        return fragmentWoDe;
    }

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideFragmentLayoutID() {
        return R.layout.fragment_wode;
    }

    @Override
    protected WoDePresenter providePresenter() {
        return new WoDePresenter(this);
    }

    //初始化
    @Override
    protected void initView(View view) {
        super.initView(view);
        userMyLoginImage.setImageURI("res:///" + R.drawable.user);
        wode.setRefreshHeader(new DropboxHeader(getContext()));
        wode.setEnableRefresh(true);
        wode.setEnableLoadMore(true);
    }

    //获得数据
    @Override
    protected void initData() {
        super.initData();
        presenter.MyData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }
     //登录
    @Override
    public void getLoginDataSuccess(Logins logins) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("useInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

    }

    @Override
    public void getLoginDataFailed(String error) {

    }

    @Override
    public void getWoDeDataSuccess(WoDeBeans woDeBeans) {

    }

    @Override
    public void getWoDeDataError(String error) {

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
}
