package com.baiwei.tianlong.jindong.mvp.dingdan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseActivity;
import com.baiwei.tianlong.jindong.custom_view.MySearchView;
import com.baiwei.tianlong.jindong.mvp.MyAddSumView;
import com.baiwei.tianlong.jindong.mvp.dingdan.model.beans.QingQiuMoRenDiZhi;
import com.baiwei.tianlong.jindong.mvp.dingdan.presenter.DingDanPresenter;
import com.baiwei.tianlong.jindong.mvp.dingdan.view.DingDanView;
import com.baiwei.tianlong.jindong.mvp.main.MainActivity;
import com.baiwei.tianlong.jindong.mvp.show.ShowActivity;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.ProductBeans;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DingDan extends BaseActivity<DingDanPresenter> implements DingDanView {
    private static final String TAG = "DingDanActivity--";





    //
    @BindView(R.id.mtv_title_show)
    MySearchView mtvTitleShow;
    //
    @BindView(R.id.tv_name_address_item)
    TextView tvNameAddressItem;
    @BindView(R.id.tv_mobile_address_item)
    TextView tvMobileAddressItem;
    @BindView(R.id.tv_address_address)
    TextView tvAddressAddress;
    @BindView(R.id.tv_check_address_item)
    TextView tvCheckAddressItem;
    @BindView(R.id.ll_edit_address_item)
    LinearLayout llEditAddressItem;
    @BindView(R.id.shop_jia)
    TextView shopJia;
    @BindView(R.id.shangpin_img)
    SimpleDraweeView shangpinImg;

    @BindView(R.id.tv_title_child)
    TextView tvTitleChild;
    @BindView(R.id.tv_price_child)
    TextView tvPriceChild;
    @BindView(R.id.masv_child)
    MyAddSumView masvChild;
    @BindView(R.id.shangpin)
    LinearLayout shangpin;
    @BindView(R.id.dingdan_jiaqian)
    TextView dingdanJiaqian;
    @BindView(R.id.ll_shopProvide_show)
    LinearLayout llShopProvideShow;
    @BindView(R.id.ll_seller_show)
    LinearLayout llSellerShow;
    @BindView(R.id.ll_shopCar_show)
    LinearLayout llShopCarShow;
    @BindView(R.id.btn_buy_product_show)
    Button btnBuyProductShow;



    public String pid1;
    private String sellerid1;
    private String uid;
    @Override
    protected int provideLayoutId() {
        return R.layout.activity_ding_dan;
    }

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected DingDanPresenter providePresenter() {
        return new DingDanPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();

        getDataFromSharedPreferences();

    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
          uid = intent.getStringExtra("uid");
          pid1 = intent.getStringExtra("pid");
          sellerid1 = intent.getStringExtra("sellerid");

         presenter.getAddDefaultAddr(uid);
    }

    private void getDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin){
             uid = sharedPreferences.getString("uid", "");
        }else {
            Toast.makeText(DingDan.this, "请先登录", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void initListener() {
        super.initListener();
        mtvTitleShow.setOnMyTitleViewClickListener(new MySearchView.OnMyTitleViewClickListener() {
            @Override
            public void leftClick() {

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
    public void ShowAddDefaultAddrSuccess(QingQiuMoRenDiZhi qingQiuMoRenDiZhi) {
        String addr = qingQiuMoRenDiZhi.getData().getAddr();
        String name = qingQiuMoRenDiZhi.getData().getName();
        String mobile = qingQiuMoRenDiZhi.getData().getMobile()+"";

            tvNameAddressItem.setText(name);
            tvMobileAddressItem.setText(mobile);
            tvCheckAddressItem.setText(addr);

        presenter.getShowShopData(pid1);
    }

    @Override
    public void ShowAddDefaultAddrFaildes(String error) {
        Log.d(TAG, "ShowAddDefaultAddrFaildes: " + error);
    }

    @Override
    public void ShowProuductSuccess(ProductBeans productBeans) {
        //商家
        ProductBeans.SellerBean seller = productBeans.getSeller();
        String shangjia_name = seller.getName();
        //商品
        ProductBeans.DataBean data = productBeans.getData();
        String pid = data.getPid()+"";
        String price = data.getPrice()+"";
        String  title = data.getTitle();
        String  image = data.getImages().split("\\|")[0];
        String sellerid = String.valueOf(data.getSellerid());


        if (pid.equals(pid1) && sellerid.equals(sellerid1)){
            shangpinImg.setImageURI(image);
            shopJia.setText(shangjia_name);
            tvTitleChild.setText(title);
            tvPriceChild.setText("￥"+price);
            dingdanJiaqian.setText("￥"+price);
         }

    }

    @Override
    public void ShowProuductFaildes(String error) {
        Log.d(TAG, "ShowAddDefaultAddrFaildes: " + error);
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


    //单击事件
    @OnClick(R.id.btn_buy_product_show)
    public void onViewClicked() {

            
    }


}


