package com.baiwei.tianlong.jindong.mvp.home.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.mvp.search.SearchActivity;
import com.baiwei.tianlong.jindong.base.BaseFragment;
import com.baiwei.tianlong.jindong.custom_view.MySearchView;

import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeBeans;
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeFenLeiBeans;
import com.baiwei.tianlong.jindong.mvp.home.presenter.HomePresenter;
import com.baiwei.tianlong.jindong.mvp.home.view.HomeView;
import com.baiwei.tianlong.jindong.mvp.home.view.adapter.HomeFenLeiAdapter;
import com.baiwei.tianlong.jindong.mvp.home.view.adapter.MiaoShaAdapter;
import com.baiwei.tianlong.jindong.mvp.home.view.adapter.MyTuiJianAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentHome extends BaseFragment<HomePresenter> implements HomeView {

    private static final String TAG = "HomeFragment--";


    @BindView(R.id.home_banner)
    Banner homeBanner;
    @BindView(R.id.searchview_home)
    MySearchView searchviewHome;
    @BindView(R.id.fg_home)
    RelativeLayout fgHome;
    @BindView(R.id.home_mqv)
    MarqueeView homeMqv;
    @BindView(R.id.home_Recommended_for_you)
    RecyclerView homeRecommendedForYou;
    //上拉刷新
    @BindView(R.id.sr1_main)
    SmartRefreshLayout sr1Main;
    @BindView(R.id.home_more)
    TextView homeMore;
    @BindView(R.id.pinzhi_shop)
    SimpleDraweeView pinzhiShop;
    @BindView(R.id.pinzhi_shop1)
    SimpleDraweeView pinzhiShop1;
    @BindView(R.id.pinpai_miaosha_img1)
    SimpleDraweeView pinpaiMiaoshaImg1;
    @BindView(R.id.pinlei_miaosha_img2)
    SimpleDraweeView pinleiMiaoshaImg2;
    @BindView(R.id.home_huimiao1)
    SimpleDraweeView homeHuimiao1;
    @BindView(R.id.home_huimiao2)
    SimpleDraweeView homeHuimiao2;
    @BindView(R.id.home_fenlei_recycler)
    RecyclerView homeFenleiRecycler;
    @BindView(R.id.home_Second_kill)
    RecyclerView homeSecondKill;

    Unbinder unbinder1;
    private HomeFenLeiAdapter homeFenLeiAdapter;
    private GridLayoutManager gridLayoutManager;

    //单列 懒加载
    public static FragmentHome newInstance(String param1) {
        FragmentHome fragmentHome = new FragmentHome();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragmentHome.setArguments(args);
        return fragmentHome;
    }

    //集合添加数据
    //跑马灯
    List<String> list_icon = new ArrayList<>();
    //banner的数据源
    private List<String> list_title = new ArrayList<>();
    private List<String> list_info = new ArrayList<>();
    private List<HomeBeans.TuijianBean.ListBean> list_data = new ArrayList<>();
    private MyTuiJianAdapter adapter;
    //页数
    private int page = 1;

    //ButterKinfer
    @Override
    protected Object provideBindView() {
        return this;
    }

    //得到当前布局
    @Override
    protected int provideFragmentLayoutID() {
        return R.layout.fragment_home;
    }

    //注入P层
    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter(this);
    }

    //初始化
    @Override
    protected void initView(View view) {
        super.initView(view);

        view.setFocusable(false);
        //初始化组件
        //添加头部
        sr1Main.setRefreshHeader(new WaveSwipeHeader(getContext()));
        sr1Main.setRefreshFooter(new BallPulseFooter(getContext()));

        //跑马灯添加数据
        list_info.add("618京东大狂欢");
        list_info.add("三大运营商IPTV最新进展报告");
        list_info.add("飞跃小白鞋:国货经典,男女通杀");
        list_info.add("苹果今秋全线产品更新");

        homeMqv.startWithList(list_info, R.anim.anim_bottom_in, R.anim.anim_top_out);


    }

    /**
     * P层获得数据
     */
    @Override
    protected void initData() {
        super.initData();
        presenter.getHomeData();
    }

    /**
     * 监听
     */
    @Override
    protected void initListener() {
        super.initListener();
        //自定布局单击事件
        searchviewHome.setOnMyTitleViewClickListener(new MySearchView.OnMyTitleViewClickListener() {
            @Override
            public void leftClick() {

            }

            @Override
            public void searhClick() {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }

            @Override
            public void rightClick() {

            }
        });
        //banner的单击事件
        homeBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getContext(), list_title.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        //更多按钮
        homeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

        //刷新的监听事件
        sr1Main.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                presenter.getHomeData();
                refreshLayout.finishLoadMore();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 1;
                presenter.getHomeData();
                refreshLayout.finishRefresh();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }

    //加载主页面布局
    @Override
    public void getHomeDataSuccess(HomeBeans homeBeans) {
        List<HomeBeans.DataBean> list_ad = homeBeans.getData();
        list_icon.clear();
        list_title.clear();

        for (int i = 0; i < list_ad.size(); i++) {
            list_icon.add(list_ad.get(i).getIcon());
            list_title.add(list_ad.get(i).getTitle());
        }

        //轮播图
        homeBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        homeBanner.setImageLoader(new MyLoader());
        homeBanner.setImages(list_icon);
        homeBanner.setBannerTitles(list_title);
        homeBanner.isAutoPlay(true);
        homeBanner.setDelayTime(3000);
        homeBanner.setBannerAnimation(Transformer.FlipHorizontal);
        homeBanner.start();

        //秒杀数据
        List<HomeBeans.MiaoshaBean.ListBeanX> list1 = homeBeans.getMiaosha().getList();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        homeSecondKill.setLayoutManager(layoutManager);
        //设置适配器
        MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(list1, getContext());
        homeSecondKill.setAdapter(miaoShaAdapter);


        //推荐数据
        List<HomeBeans.TuijianBean.ListBean> list = homeBeans.getTuijian().getList();
        Log.i("TAG", list + "");
        if (page == 1) {
            list_ad.clear();
        }
        list_data.addAll(list);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        //
        manager.setOrientation(GridLayoutManager.VERTICAL);
        //适配器
        adapter = new MyTuiJianAdapter(list_data, getContext());

        homeRecommendedForYou.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        homeRecommendedForYou.setLayoutManager(manager);

        //条目单击事件
        adapter.setOnItemClickListener(new MyTuiJianAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

            }
        });

        //图片赋值
        for (int i = 0; i < list_data.size(); i++) {
            String imgUrl1 = list_data.get(0).getImages().split("\\|")[0];
            String imgUrl2 = list_data.get(1).getImages().split("\\|")[1];
            String imgUrl3 = list_data.get(2).getImages().split("\\|")[0];
            String imgUrl4 = list_data.get(3).getImages().split("\\|")[3];
            String imgUrl5 = list_data.get(4).getImages().split("\\|")[3];
            String imgUrl6 = list_data.get(3).getImages().split("\\|")[2];

            pinzhiShop.setImageURI(imgUrl1);
            pinzhiShop1.setImageURI(imgUrl2);
            pinpaiMiaoshaImg1.setImageURI(imgUrl3);
            pinleiMiaoshaImg2.setImageURI(imgUrl4);
            homeHuimiao1.setImageURI(imgUrl5);
            homeHuimiao2.setImageURI(imgUrl6);
        }

        presenter.getHomeFeileiData();
    }

    //主页面加载失败
    @Override
    public void getHomeDataFailed(String error) {
        Log.d(TAG, "getHomeDataFailed" + error);
    }

    //得到主页面布局
    @Override
    public void getHomeFenleiDataSuccess(HomeFenLeiBeans homeFenLeiBeans) {
        List<HomeFenLeiBeans.DataBean> data = homeFenLeiBeans.getData();
        gridLayoutManager = new GridLayoutManager(getContext(), 5);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        homeFenLeiAdapter = new HomeFenLeiAdapter(data);

        if (homeFenleiRecycler != null) {
            homeFenleiRecycler.setLayoutManager(gridLayoutManager);
            homeFenleiRecycler.setAdapter(homeFenLeiAdapter);
        }
        //单击事件
        homeFenLeiAdapter.setOnItemClickListener(new HomeFenLeiAdapter.OnItemClickListener() {
            @Override
            public void OnItenClick(View view, int position) {

            }
        });

    }

    @Override
    public void getHomeFenleiFailed(String error) {

    }

    @Override
    public Context cotext() {
        return getContext();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消绑定
        unbinder1.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
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
