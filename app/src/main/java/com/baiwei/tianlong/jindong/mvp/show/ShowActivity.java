package com.baiwei.tianlong.jindong.mvp.show;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseActivity;
import com.baiwei.tianlong.jindong.custom_view.MySearchView;
import com.baiwei.tianlong.jindong.mvp.login.LoginActiity;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.AddCartBean;
import com.baiwei.tianlong.jindong.mvp.show.model.beans.ProductBeans;
import com.baiwei.tianlong.jindong.mvp.show.presenter.ShowPresenter;
import com.baiwei.tianlong.jindong.mvp.show.view.ShowIView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivity extends BaseActivity<ShowPresenter> implements ShowIView {
    private static final String TAG = "ShowActivity--";
    //自定义布局
    @BindView(R.id.mtv_title_show)
    MySearchView mtvTitleShow;
    //轮播图
    @BindView(R.id.iv_product_icon_show)
    Banner ivProductIconShow;
    //商品价钱
    @BindView(R.id.tv_product_price_show)
    TextView tvProductPriceShow;
    //商品标题
    @BindView(R.id.tv_product_title_show)
    TextView tvProductTitleShow;
    //优惠信息
    @BindView(R.id.tv_product_subhead_show)
    TextView tvProductSubheadShow;
    //商家图标
    @BindView(R.id.sdv_seller_icon_show)
    SimpleDraweeView sdvSellerIconShow;
    //商家名车
    @BindView(R.id.tv_seller_name_show)
    TextView tvSellerNameShow;

    @BindView(R.id.ll_show)
    LinearLayout llShow;
    @BindView(R.id.ll_shopProvide_show)
    LinearLayout llShopProvideShow;
    @BindView(R.id.ll_seller_show)
    LinearLayout llSellerShow;
    @BindView(R.id.ll_shopCar_show)
    LinearLayout llShopCarShow;
    @BindView(R.id.btn_add_product_show)
    Button btnAddProductShow;
    @BindView(R.id.btn_buy_product_show)
    Button btnBuyProductShow;


    private String detailUrl;
    //标题
    private String title;
    private String uid;
    private String productId;
    private ProductBeans.DataBean data;
    private List<String> list = new ArrayList<>();

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected ShowPresenter providePresenter() {
        return new ShowPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        getDataFromSharedPreferences();
    }

    private void getDataFromSharedPreferences() {
        SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = userInfo.edit();
        boolean isLogin = userInfo.getBoolean("isLogin", false);
        if (isLogin) {
            uid = userInfo.getString("uid", "");
        } else {
            Toast.makeText(ShowActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        productId = pid;
        presenter.getShowShopData(pid);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mtvTitleShow.setOnMyTitleViewClickListener(new MySearchView.OnMyTitleViewClickListener() {
            @Override
            public void leftClick() {
                Intent intent = new Intent();
                setResult(1, intent);
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
    public void ShowProuductSuccess(ProductBeans productBeans) {
        //商家信息
        ProductBeans.SellerBean seller = productBeans.getSeller();
        tvSellerNameShow.setText(seller.getName());
        sdvSellerIconShow.setImageURI(Uri.parse(seller.getIcon()));


        //商品信息
         data = productBeans.getData();
        //商品的url  分享使用
        detailUrl = data.getDetailUrl();
        //商品图片
        String[] split = data.getImages().split("\\|");
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }
        ivProductIconShow.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        ivProductIconShow.setImageLoader(new MyLoader());
        ivProductIconShow.setImages(list);
        ivProductIconShow.setDelayTime(3000);
        ivProductIconShow.setBannerAnimation(Transformer.BackgroundToForeground);
        ivProductIconShow.start();


        title = data.getTitle();
        //价钱
        tvProductPriceShow.setText("￥" + data.getBargainPrice());
        tvProductTitleShow.setText(title);
        tvProductSubheadShow.setText(data.getSubhead());


    }

    @Override
    public void ShowProuductFaildes(String error) {
        Log.d(TAG, "getProductDataFailed: " + error);
    }

    @Override
    public void AddCartSuccess(AddCartBean addCartBean) {
        Toast.makeText(ShowActivity.this, addCartBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void AddCartFaildes(String error) {
        Log.d(TAG, "addCartDataFailed: " + error);
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

    @OnClick({R.id.btn_add_product_show, R.id.btn_buy_product_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //加入购物车
            case R.id.btn_add_product_show:
                if (!TextUtils.isEmpty(uid)){
                    presenter.getAddCartData(uid,productId);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setTitle("请登录后使用!");
                    builder.setMessage("现在要跳往登录页面吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(ShowActivity.this, LoginActiity.class);
                            startActivityForResult(intent,1);
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();

                }
                break;
                //立即购买
            case R.id.btn_buy_product_show:
                if (!TextUtils.isEmpty(uid)){





                    presenter.getAddCartData(uid,productId);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setTitle("请登录后使用!");
                    builder.setMessage("现在要跳往登录页面吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(ShowActivity.this, LoginActiity.class);
                            startActivityForResult(intent,1);
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();

                }
                break;

             default:
                 break;
        }
    }

    //图片加载
    private class MyLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageURI(Uri.parse((String) path));
        }

        @Override
        public ImageView createImageView(Context context) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
            return simpleDraweeView;
        }
    }
}
