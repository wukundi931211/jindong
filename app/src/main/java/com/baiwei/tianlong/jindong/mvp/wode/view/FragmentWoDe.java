package com.baiwei.tianlong.jindong.mvp.wode.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseFragment;

import com.baiwei.tianlong.jindong.mvp.login.LoginActiity;
import com.baiwei.tianlong.jindong.mvp.register.view.activity.RegisterActivity;
import com.baiwei.tianlong.jindong.mvp.showactivity.ShowActivity;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.Logins;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.WoDeBeans;
import com.baiwei.tianlong.jindong.mvp.wode.presenter.WoDePresenter;
import com.baiwei.tianlong.jindong.mvp.wode.view.adapter.MyTuiJianAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.header.DropboxHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentWoDe extends BaseFragment<WoDePresenter> implements WoDeIView {

    private static final String TAG = "MyCenterFragment--";
    @BindView(R.id.btn_set_login)
    Button btnSetLogin;
    @BindView(R.id.btn_msg_login)
    Button btnMsgLogin;
    @BindView(R.id.user_my_login_image)
    SimpleDraweeView userMyLoginImage;
    //登录注册
    @BindView(R.id.user_login)
    TextView userLogin;
    //登录变化背景图
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


    private String nickname;
    private String icon;
    private String username;

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
        //刷新的头部
        wode.setRefreshHeader(new DropboxHeader(getContext()));
        wode.setEnableRefresh(true);
        wode.setEnableLoadMore(true);
    }

    //获得数据
    @Override
    protected void initData() {
        super.initData();
        presenter.MyData();
        //登录后的状态
        getDataFromSharedPreferences();
    }

    //登陸判斷
    private void getDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("useInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //判断登录的值
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin){
            String mobile = sharedPreferences.getString("mobile", "");
            String password = sharedPreferences.getString("password", "");
            Map<String,String> map = new HashMap<>();
            map.put("mobile",mobile);
            map.put("password",password);

            presenter.Login(map);

            //变换登录的背景图
            login.setBackgroundResource(R.drawable.reg_bg);
        }else {
            userMyLoginImage.setImageURI("res:///" + R.drawable.user);
            login.setBackgroundResource(R.drawable.b5a);
            userLogin.setText("登录/注册");
            Toast.makeText(getContext(), "请登录", Toast.LENGTH_SHORT).show();

            //清空登录信息
            nickname = null;
            icon = null;
            username = null;

        }

    }
    //监听事件
    @Override
    protected void initListener() {
        super.initListener();
        //刷新的刷新
        wode.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                //登录后的状态
                getDataFromSharedPreferences();
                refreshLayout.finishRefresh();
            }
        });
    }

    //登录
    @Override
    public void getLoginDataSuccess(Logins logins) {
        nickname = logins.getData().getNickname();
        icon = logins.getData().getIcon();
        username = logins.getData().getUsername();

        Log.d(TAG, "getLoginDataSuccess: " + icon);
        if (!TextUtils.isEmpty(icon)) {
            userMyLoginImage.setImageURI(Uri.parse(icon));
        } else {
            userMyLoginImage.setImageURI("res:///" + R.drawable.user);
        }

        if (!TextUtils.isEmpty(nickname)) {
            userLogin.setText(nickname);
        } else {
            if (!TextUtils.isEmpty(username)) {
                userLogin.setText(username);
            }
        }

    }
    //失败
    @Override
    public void getLoginDataFailed(String error) {
        Log.d(TAG, "getLoginDataFailed: " + error);
    }

    //展示
    @Override
    public void getWoDeDataSuccess(WoDeBeans woDeBeans) {
        final List<WoDeBeans.TuijianBean.ListBean> list = woDeBeans.getTuijian().getList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        MyTuiJianAdapter myTuiJianAdapter = new MyTuiJianAdapter(list, getContext());
        if (rvShowLogin!=null){
            rvShowLogin.setLayoutManager(gridLayoutManager);
            rvShowLogin.setAdapter(myTuiJianAdapter);
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
    public void getWoDeDataError(String error) {
        Log.d(TAG, "getLoginDataFailed: " + error);
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

    @OnClick({R.id.btn_set_login, R.id.btn_msg_login, R.id.user_my_login_image, R.id.user_login, R.id.ll_dfk_login, R.id.ll_d_login, R.id.ll_dpj_login, R.id.ll_th_sh_login, R.id.ll_mydd_login, R.id.ll_sqgz_login, R.id.rv_show_login, R.id.wode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//設置個人信息
            case R.id.btn_set_login:
                Intent intent3 = new Intent(getContext(), RegisterActivity.class);
                intent3.putExtra("nickname", nickname);
                intent3.putExtra("icon", icon);
                intent3.putExtra("username", username);
                startActivityForResult(intent3, 1);
                break;
//发现
            case R.id.btn_msg_login:
                break;
//头像按钮跳转页面
            case R.id.user_my_login_image:
                //跳转 修改个人信息页面
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                intent.putExtra("nickname",nickname);
                intent.putExtra("icon",icon);
                intent.putExtra("username",username);
                startActivityForResult(intent,1);
                break;
 //文字
            case R.id.user_login:
                if (userLogin.getText().toString().equals("登录/注册")){
                    //跳转登录页面
                    Intent intent1 = new Intent(getContext(),LoginActiity.class);
                    startActivityForResult(intent1,1);
                }else {
                    //跳转注册页面
                    Intent intent2 = new Intent(getContext(), RegisterActivity.class);
                    intent2.putExtra("nickname",nickname);
                    intent2.putExtra("icon",icon);
                    intent2.putExtra("username",username);
                    startActivityForResult(intent2,1);
                }
                break;
                //代付款
            case R.id.ll_dfk_login:
                break;
                //代收款
            case R.id.ll_d_login:
                break;
                //待评价
            case R.id.ll_dpj_login:
                break;
                //退换/售后
            case R.id.ll_th_sh_login:
                break;
                //我的订单
            case R.id.ll_mydd_login:
                break;
                //商品关注
            case R.id.ll_sqgz_login:
                break;
                //页面展示
            case R.id.rv_show_login:
                break;
                //刷新
            case R.id.wode:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode==8){
            Log.d(TAG, "onActivityResult: 回调了");
            //登录后进入判断 登录状态
            getDataFromSharedPreferences();
        }

        if (requestCode == 1 && resultCode == 99) {
            Log.d(TAG, "onActivityResult: 99回调了");
            String name = data.getStringExtra("name");
            String url = data.getStringExtra("url");
            userLogin.setText(name);
            userMyLoginImage.setImageURI(Uri.parse(url));
        }

//        if (requestCode == 1 && resultCode == 66) {
//            MainActivity mainActivity = (MainActivity) getActivity();
//            mainActivity.gotoFindFragment();
//            mainActivity.getBottomBar().setDefaultTab(R.id.tab_find);
//        }
    }
}
