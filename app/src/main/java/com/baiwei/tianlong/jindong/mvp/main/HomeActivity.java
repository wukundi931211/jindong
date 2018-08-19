package com.baiwei.tianlong.jindong.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.baiwei.tianlong.jindong.R;


import com.baiwei.tianlong.jindong.mvp.shopcar.FragmentDingdan;
import com.baiwei.tianlong.jindong.mvp.faxian.FragmentFaXian;
import com.baiwei.tianlong.jindong.mvp.fenlei.FragmentFenLei;
import com.baiwei.tianlong.jindong.mvp.home.view.fragment.FragmentHome;
import com.baiwei.tianlong.jindong.mvp.wode.view.FragmentWoDe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.td)
    FrameLayout td;

    private FragmentHome fragmentHome;
    private FragmentFenLei fragmentFenLei;
    private FragmentDingdan fragmentDingdan;
    private FragmentWoDe fragmentWoDe;
    private FragmentFaXian fragmentFaXian;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //竖屏锁定
      // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏半透明
       // StatusBarUtil.setTranslucent(this,55);
        //设置状态栏全透明
        //StatusBarUtil.setTransparent(HomeActivity.this);
        //透明状态栏
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_home);


        ButterKnife.bind(this);

        initview();
    }
    //初始化
    private void initview() {
        bottomNavigationBar.setTabSelectedListener(this);
        /**
         * 设置模式
         * 1、BottomNavigationBar.MODE_DEFAULT 默认
         * 如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式
         *
         * 2、BottomNavigationBar.MODE_FIXED 固定大小
         * 填充模式，未选中的Item会显示文字，没有换挡动画。
         *
         * 3、BottomNavigationBar.MODE_SHIFTING 不固定大小
         * 换挡模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像换挡的动画
         */
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        /**
         * 设置背景的样式
         * 1、BottomNavigationBar.BACKGROUND_STYLE_DEFAULT 默认
         * 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。
         * 如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
         *
         * 2、BottomNavigationBar.BACKGROUND_STYLE_STATIC 导航条的背景色是白色，
         * 加上setBarBackgroundColor（）可以设置成你所需要的任何背景颜色
         * 点击的时候没有水波纹效果
         *
         * 3、BottomNavigationBar.BACKGROUND_STYLE_RIPPLE 导航条的背景色是你设置的处于选中状态的
         * Item的颜色（ActiveColor），也就是setActiveColorResource这个设置的颜色
         * 点击的时候有水波纹效果
         */
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        /**
         * 添加导航
         */
        bottomNavigationBar
                        .addItem(new BottomNavigationItem(R.drawable.a1, "首页")
                        .setInactiveIconResource(R.drawable.a2)
                                .setActiveColorResource(R.color.colorRed)//选中字体颜色
                                .setInActiveColorResource(R.color.colorGray)//未选中字体颜色
                );
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.b1, "分类")
                        .setInactiveIconResource(R.drawable.b2)
                        .setActiveColorResource(R.color.colorRed)//选中字体颜色
                        .setInActiveColorResource(R.color.colorGray)//未选中字体颜色
                );
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.c1, "发现")
                        .setInactiveIconResource(R.drawable.c2)
                        .setActiveColorResource(R.color.colorRed)//选中字体颜色
                        .setInActiveColorResource(R.color.colorGray)//未选中字体颜色
                );
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.d1, "购物车")
                        .setInactiveIconResource(R.drawable.d2)
                        .setActiveColorResource(R.color.colorRed)//选中字体颜色
                        .setInActiveColorResource(R.color.colorGray)//未选中字体颜色
                );
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.e1, "我的")
                        .setInactiveIconResource(R.drawable.e2)
                        .setActiveColorResource(R.color.colorRed)//选中字体颜色
                        .setInActiveColorResource(R.color.colorGray)//未选中字体颜色
                );

        //默认选中
        bottomNavigationBar.setFirstSelectedPosition(0);
        //初始化
        bottomNavigationBar.initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }
    //初始化加载页面
    private void setDefaultFragment() {
        fragmentHome = FragmentHome.newInstance("首页");
        getSupportFragmentManager().beginTransaction().replace(R.id.td,fragmentHome).commit();
    }
    @Override
    public void onTabSelected(int position) {
        switch (position){
            case 0:
                if (fragmentHome == null){
                    fragmentHome = FragmentHome.newInstance("首页");
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.td,fragmentHome).commit();
                break;
            case 1:
                if (fragmentFenLei == null){
                    fragmentFenLei =FragmentFenLei.newInstance("分类");
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.td,fragmentFenLei).commit();
                break;
            case 2:
                if (fragmentFaXian == null){
                    fragmentFaXian = FragmentFaXian.newInstance("发现");
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.td,fragmentFaXian).commit();
                break;
            case 3:
                if (fragmentDingdan == null){
                    fragmentDingdan = FragmentDingdan.newInstance("购物车");
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.td,fragmentDingdan).commit();
                break;
            case 4:
                if (fragmentWoDe == null){
                    fragmentWoDe = FragmentWoDe.newInstance("我的");
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.td,fragmentWoDe).commit();
                break;
        }
    }
    /**
     * 设置未选中Fragment 事务
     */
    @Override
    public void onTabUnselected(int position) {

    }
    /**
     * 设置释放Fragment 事务
     */
    @Override
    public void onTabReselected(int position) {

    }


    //联动效果 去购物车页面
    public void  gotoGouWu(){
        if (fragmentDingdan == null){
            fragmentDingdan = FragmentDingdan.newInstance("购物车");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.td,fragmentDingdan).commit();
    }

}
