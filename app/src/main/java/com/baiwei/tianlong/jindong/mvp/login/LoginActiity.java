package com.baiwei.tianlong.jindong.mvp.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseActivity;
import com.baiwei.tianlong.jindong.custom_view.MySearchView;
import com.baiwei.tianlong.jindong.mvp.login.model.beans.LoginsActivityBeans;
import com.baiwei.tianlong.jindong.mvp.login.presenter.LoginPresenter;
import com.baiwei.tianlong.jindong.mvp.login.view.LoginView;
import com.baiwei.tianlong.jindong.mvp.update_personal.PersonalActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActiity extends BaseActivity<LoginPresenter> implements LoginView {
    private static final String TAG = "LoginActivity--";
    //自定义布局
    @BindView(R.id.search_login)
    MySearchView searchLogin;
    @BindView(R.id.ed_mobile_login)
    EditText edMobileLogin;
    @BindView(R.id.ed_password_login)
    EditText edPasswordLogin;
    @BindView(R.id.btn_login_login)
    Button btnLoginLogin;
    //去注册
    @BindView(R.id.tv_reg_login)
    TextView tvRegLogin;
    @BindView(R.id.tv_other_login)
    TextView tvOtherLogin;
    @BindView(R.id.iv_wx_login_login)
    ImageView ivWxLoginLogin;
    @BindView(R.id.iv_qq_login_login)
    ImageView ivQqLoginLogin;

    private String mobile;
    private String password;


    private SharedPreferences.Editor editor;
    public SharedPreferences sharedPreferences;

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected LoginPresenter providePresenter() {
        return new LoginPresenter(this);
    }


    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        //自定义布局的监听
        searchLogin.setOnMyTitleViewClickListener(new MySearchView.OnMyTitleViewClickListener() {
            @Override
            public void leftClick() {
                Intent intent = getIntent();
                setResult(5, intent);
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

    @Override
    public void getLoginDataSuccess(LoginsActivityBeans logins) {
        //请求判断登录状态  是否登陆成功
        String msg = logins.getMsg();
        String icon = logins.getData().getIcon();
        int uid = logins.getData().getUid();
        String username = logins.getData().getUsername();
         //显示登录成功
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

        //储存登录状态
         sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
         //储存sharedPreferences 储存的库的名字
         editor = sharedPreferences.edit();
         editor.putString("mobile",mobile);
         editor.putString("password",password);
         editor.putString("icon",icon);
         editor.putString("uid",""+uid);
         editor.putString("username",username);
         editor.putBoolean("isLogin",true);
         editor.commit();

         //回调显示
         Intent intent = getIntent();
         setResult(8,intent);
         finish();
    }

    @Override
    public void getLoginDataFailed(String error) {
        Log.d(TAG, "getLoginDataFailed: " + error);
    }

    @Override
    public Context cotext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //获取登录信息
    @OnClick({ R.id.btn_login_login,R.id.tv_reg_login, R.id.tv_other_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //登录单击事件
            case R.id.btn_login_login:
                 mobile = edMobileLogin.getText().toString();
                 password = edPasswordLogin.getText().toString();
                Map<String,String> map = new HashMap<>();
                map.put("mobile",mobile);
                map.put("password",password);
                //把数据存储到p层 请求数据
                presenter.Login(map);
                break;


                //去注册
            case R.id.tv_reg_login:
                Intent intent1 = new Intent(LoginActiity.this, PersonalActivity.class);
                startActivityForResult(intent1,1);
                break;

            case R.id.tv_other_login:
                Toast.makeText(this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode ==10){
            String mobile = data.getStringExtra("mobile");
            String password = data.getStringExtra("password");
            edMobileLogin.setText(mobile);
            edPasswordLogin.setText(password);
        }
    }
}
