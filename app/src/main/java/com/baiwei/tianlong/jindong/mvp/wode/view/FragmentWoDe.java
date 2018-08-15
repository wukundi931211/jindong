package com.baiwei.tianlong.jindong.mvp.wode.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseFragment;

import com.baiwei.tianlong.jindong.mvp.login.LoginActiity;
import com.baiwei.tianlong.jindong.mvp.setting.view.activity.SettingActivity;
import com.baiwei.tianlong.jindong.mvp.show.ShowActivity;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.HomeAdBean;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.LoginBean;

import com.baiwei.tianlong.jindong.mvp.wode.presenter.WoDePresenter;
import com.baiwei.tianlong.jindong.mvp.wode.view.adapter.MyTuiJianAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

public class FragmentWoDe extends BaseFragment<WoDePresenter> implements WoDeIView {
    private static final String TAG = "MyCenterFragment--";
    @BindView(R.id.rv_show_myCenter)
    RecyclerView mRvShowMyCenter;
    @BindView(R.id.iv_user_img_myCenter)
    SimpleDraweeView ivUserImgMyCenter;
    @BindView(R.id.tv_login_myCenter)
    TextView tvLoginMyCenter;
    @BindView(R.id.ll_login_myCenter)
    LinearLayout llLoginMyCenter;
    @BindView(R.id.btn_set_myCenter)
    Button btnSetMyCenter;
    @BindView(R.id.btn_msg_myCenter)
    Button btnMsgMyCenter;
    @BindView(R.id.ll_dfk_myCenter)
    LinearLayout llGfkMyCenter;
    @BindView(R.id.ll_dsf_myCenter)
    LinearLayout llGsfMyCenter;
    @BindView(R.id.ll_dpj_myCenter)
    LinearLayout llGpjMyCenter;
    @BindView(R.id.ll_th_sh_myCenter)
    LinearLayout llThShMyCenter;
    @BindView(R.id.ll_mydd_myCenter)
    LinearLayout llMyddMyCenter;
    @BindView(R.id.ll_sjgz_myCenter)
    LinearLayout llSjgzMyCenter;
    @BindView(R.id.ll_kh_myCenter)
    LinearLayout llKhMyCenter;
    @BindView(R.id.ll_lljl_myCenter)
    LinearLayout llLljlMyCenter;
    @BindView(R.id.ll_myqb_myCenter)
    LinearLayout llMyqbMyCenter;
    @BindView(R.id.ll_spgz_myCenter)
    LinearLayout llSpgzMyCenter;
    @BindView(R.id.rl_myCenter)
    SmartRefreshLayout rlMyCenter;

    private String nickname;
    private String icon;
    private String username;
    private int resultCode;

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


    @Override
    protected void initView(View view) {
        super.initView(view);
        ivUserImgMyCenter.setImageURI("res:///" + R.drawable.user);
        rlMyCenter.setRefreshHeader(new DeliveryHeader(getContext()));
        rlMyCenter.setEnableRefresh(true);
        rlMyCenter.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.MyData();
        getDataFromSharedPreferences();
    }

    private void getDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin) {
            String mobile = sharedPreferences.getString("mobile", "");
            String password = sharedPreferences.getString("password", "");
            //储存登录的请求 进行拼接
            Map<String, String> map = new HashMap<>();
            map.put("mobile", mobile);
            map.put("password", password);
            //调取p层进行请求
            presenter.Login(map);
            //变换登录的背景图
            llLoginMyCenter.setBackgroundResource(R.drawable.reg_bg);

        } else {
            //如果没有登录信息
            ivUserImgMyCenter.setImageURI("res:///" + R.drawable.user);
            llLoginMyCenter.setBackgroundResource(R.drawable.b5a);
            tvLoginMyCenter.setText("登录/注册");
            tvLoginMyCenter.setTextColor(Color.BLACK);
            Toast.makeText(getContext(), "请登录", Toast.LENGTH_SHORT).show();
            //清空登录信息
            nickname = null;
            icon = null;
            username = null;
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        rlMyCenter.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getDataFromSharedPreferences();
                refreshLayout.finishRefresh();
            }
        });
    }


    @Override
    public void getLoginDataSuccess(LoginBean loginBean) {
        nickname = loginBean.getData().getNickname();
        icon = loginBean.getData().getIcon();
        username = loginBean.getData().getUsername();
        Log.d(TAG, "getLoginDataSuccess: " + icon);
        if (!TextUtils.isEmpty(icon)) {
            ivUserImgMyCenter.setImageURI(Uri.parse(icon));
        } else {
            ivUserImgMyCenter.setImageURI("res:///" + R.drawable.user);
        }
        if (!TextUtils.isEmpty(nickname)) {
            tvLoginMyCenter.setText(nickname);
        } else {
            if (!TextUtils.isEmpty(username)) {
                tvLoginMyCenter.setText(username);
            }
        }
    }

    @Override
    public void getLoginDataFailed(String error) {
        Log.d(TAG, "getLoginDataFailed: " + error);
    }

    @Override
    public void getMyCenterDataSuccess(HomeAdBean homeAdBean) {
        final List<HomeAdBean.TuijianBean.ListBean> list = homeAdBean.getTuijian().getList();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        MyTuiJianAdapter myTuiJianAdapter = new MyTuiJianAdapter(list, getContext());
        if (mRvShowMyCenter != null) {
            mRvShowMyCenter.setLayoutManager(layoutManager);
            mRvShowMyCenter.setAdapter(myTuiJianAdapter);

        }

        //条目点击事件
        myTuiJianAdapter.setOnItemClickListener(new MyTuiJianAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                int pid = list.get(position).getPid();
                Log.d(TAG, "OnItemClick: " + pid);
                Intent intent = new Intent(getContext(), ShowActivity.class);
                intent.putExtra("pid", "" + pid);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void getMyCenterDataFailed(String error) {
        Log.d(TAG, "getMyCenterDataFailed: " + error);
    }

    @OnClick({R.id.iv_user_img_myCenter, R.id.tv_login_myCenter, R.id.btn_set_myCenter, R.id.btn_msg_myCenter, R.id.ll_dfk_myCenter, R.id.ll_dsf_myCenter, R.id.ll_dpj_myCenter, R.id.ll_th_sh_myCenter, R.id.ll_mydd_myCenter, R.id.ll_sjgz_myCenter, R.id.ll_kh_myCenter, R.id.ll_lljl_myCenter, R.id.ll_myqb_myCenter, R.id.ll_spgz_myCenter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //跳转修改个人信息页面
            case R.id.iv_user_img_myCenter:
                Intent intent = new Intent(getContext(), SettingActivity.class);
                intent.putExtra("nickname", nickname);
                intent.putExtra("icon", icon);
                intent.putExtra("username", username);
                startActivityForResult(intent, 1);
                break;
            //登录页面
            case R.id.tv_login_myCenter:
                if (tvLoginMyCenter.getText().toString().equals("登录/注册")) {
                    Intent intent1 = new Intent(getContext(), LoginActiity.class);
                    startActivityForResult(intent1, 1);
                } else {
                    //如果已经登录  再点击就跳修改个人信息页面
                    Intent intent2 = new Intent(getContext(), SettingActivity.class);
                    intent2.putExtra("nickname", nickname);
                    intent2.putExtra("icon", icon);
                    intent2.putExtra("username", username);
                    startActivityForResult(intent2, 1);

                }
                break;

            case R.id.btn_set_myCenter:
                Intent intent3 = new Intent(getContext(), SettingActivity.class);
                intent3.putExtra("nickname", nickname);
                intent3.putExtra("icon", icon);
                intent3.putExtra("username", username);
                startActivityForResult(intent3, 1);
                break;
            case R.id.btn_msg_myCenter:
//                MainActivity mainActivity = (MainActivity) getActivity();
//                mainActivity.gotoFindFragment();
//                mainActivity.getBottomBar().setDefaultTab(R.id.tab_find);
                break;
            case R.id.ll_dfk_myCenter:
//                Intent intent4 = new Intent(getContext(), OrderActivity.class);
//                startActivityForResult(intent4, 1);
                break;
            case R.id.ll_dsf_myCenter:
//                Intent intent5 = new Intent(getContext(), OrderActivity.class);
//                startActivityForResult(intent5, 1);
                break;
            case R.id.ll_dpj_myCenter:
//                Intent intent6 = new Intent(getContext(), OrderActivity.class);
//                startActivityForResult(intent6, 1);
                break;
            case R.id.ll_th_sh_myCenter:
                Toast.makeText(getContext(), "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_mydd_myCenter:
//                Intent intent7 = new Intent(getContext(), OrderActivity.class);
//                startActivityForResult(intent7, 1);
                break;
            case R.id.ll_sjgz_myCenter:
                Toast.makeText(getContext(), "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_kh_myCenter:
                Toast.makeText(getContext(), "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_lljl_myCenter:
                Toast.makeText(getContext(), "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_myqb_myCenter:
                Toast.makeText(getContext(), "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_spgz_myCenter:
                Toast.makeText(getContext(), "敬请期待", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 8) {
            Log.d(TAG, "onActivityResult: 回调了");
            getDataFromSharedPreferences();
        }


//        if (requestCode == 1 && resultCode == 66) {
//            MainActivity mainActivity = (MainActivity) getActivity();
//            mainActivity.gotoFindFragment();
//            mainActivity.getBottomBar().setDefaultTab(R.id.tab_find);
//        }
    }

    @Override
    public Context cotext() {
        return getContext();
    }
}
