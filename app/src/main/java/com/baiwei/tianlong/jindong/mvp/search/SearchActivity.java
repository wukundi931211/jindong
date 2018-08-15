package com.baiwei.tianlong.jindong.mvp.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseActivity;
import com.baiwei.tianlong.jindong.gen.DaoSession;
import com.baiwei.tianlong.jindong.gen.KeyWordsBeansDao;
import com.baiwei.tianlong.jindong.mvp.search.model.beans.KeyWordsBeans;
import com.baiwei.tianlong.jindong.mvp.search.model.beans.SearchBeans;
import com.baiwei.tianlong.jindong.mvp.search.presenter.SearchPresenter;
import com.baiwei.tianlong.jindong.mvp.search.view.FlowLayout;
import com.baiwei.tianlong.jindong.mvp.search.view.SerchView;
import com.baiwei.tianlong.jindong.mvp.search.view.adapter.MySearchAdapter;
import com.baiwei.tianlong.jindong.mvp.search.view.adapter.MySearchGridAdapter;
import com.baiwei.tianlong.jindong.mvp.showactivity.ShowActivity;
import com.baiwei.tianlong.jindong.network.DaoManager;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.FunGameBattleCityHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 此页面有两个布局  不同点击事件   展示不一样
 */
public class SearchActivity extends BaseActivity<SearchPresenter> implements SerchView {
    private static final String TAG = "SearchActivity--";
    //第一个布局
    @BindView(R.id.ll_flow_search)
    LinearLayout llFlowSearch;
    //返回按钮
    @BindView(R.id.btn_back_search)
    Button btnBackSearch;
    //輸入框
    @BindView(R.id.et_keywords_search)
    EditText etKeywordsSearch;
    //搜索
    @BindView(R.id.btn_goto_search)
    Button btnGotoSearch;
    //流式布局
    @BindView(R.id.fl_show_search)
    FlowLayout flShowSearch;
    //清空历史
    @BindView(R.id.tv_history_search)
    TextView tvHistorySearch;



    //第二个布局
    @BindView(R.id.ll_show_search)
    LinearLayout llShowSearch;
    //取消
    @BindView(R.id.btn_cancel_search)
    Button btnCancelSearch;
    //第二个输入框
    @BindView(R.id.et_show_keywords_search)
    EditText etShowKeywordsSearch;

    //按钮转换样式  纵向
    @BindView(R.id.btn_change_search)
    Button btnChangeSearch;

    //排序的按钮  销量 综合  价格
    @BindView(R.id.rg_search)
    RadioGroup rgSearch;
    //综合
    @BindView(R.id.rb_btn0_search)
    RadioButton rbBtn0Search;
    //销量
    @BindView(R.id.rb_btn1_search)
    RadioButton rbBtn1Search;
    //价格
    @BindView(R.id.rb_btn2_search)
    RadioButton rbBtn2Search;


    //筛选 布局
    @BindView(R.id.ll_btn3_search)
    LinearLayout llBtn3Search;
    //筛选 按钮
    @BindView(R.id.rb_btn3_search)
    RadioButton rbBtn3Search;


   //下面展示数据的布局
   @BindView(R.id.ll_rv_show_search)
   LinearLayout llRvShowSearch;
    //横向展示
    @BindView(R.id.rv_show_search)
    RecyclerView rvShowSearch;

    //上拉刷新
    @BindView(R.id.srl_show_search)
    SmartRefreshLayout srlShowSearch;




    //第二个刷新
    @BindView(R.id.srl_grid_show_search)
    SmartRefreshLayout srlGridShowSearch;
    //展示页面
    @BindView(R.id.rv_grid_show_search)
    RecyclerView rvGridShowSearch;
    @BindView(R.id.ll_rv_grid_show_search)
    LinearLayout llRvGridShowSearch;



    private List<SearchBeans.DataBean> list = new ArrayList<>();
    private MySearchAdapter searchAdapter;
    private MySearchGridAdapter mySearchGridAdapter;
    //实例化dao包
    private KeyWordsBeansDao keyWordsBeansDao;
    private DaoSession daoSession;
    //定义页面
    private int page = 1;
    private String keywords ;


    @Override
    protected int provideLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected SearchPresenter providePresenter() {
        return new SearchPresenter(this);
    }

    //初始化
    @Override
    protected void initView() {
        super.initView();
        daoSession = DaoManager.getDaoManager(this).daoSession();
        keyWordsBeansDao = daoSession.getKeyWordsBeansDao();

        //上面的刷新
        srlShowSearch.setRefreshHeader(new FlyRefreshHeader(this));
        //第二个分类跳进来的刷新
        srlGridShowSearch.setRefreshHeader(new FunGameBattleCityHeader(this));

    }

    //加载数据
    @Override
    protected void initData() {
        super.initData();
        flShowSearch.removeAllViews();
        //数据库
        List<KeyWordsBeans> list = keyWordsBeansDao.queryBuilder().build().list();

        for (int i = 0; i <list.size() ; i++) {
            keywords = list.get(i).getKeywords();
            Log.d(TAG,"initData"+keywords);
            final TextView textView = new TextView(SearchActivity.this);
            textView.setText(keywords);
            textView.setPadding(10,10,10,10);
            textView.setBackgroundColor(Color.WHITE);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = textView.getText().toString();
                    etKeywordsSearch.setText(name);
                    etShowKeywordsSearch.setText(name);
                    //p曾请求数据
                    presenter.getSearchProductsData(name,""+page,"0");
                    //搜索框的布局
                    llFlowSearch.setVisibility(View.GONE);
                    //展示
                    llShowSearch.setVisibility(View.VISIBLE);
                }
            });

            //流式布局添加 文字
            flShowSearch.addView(textView);
        }

        //接受跳转传值
        Intent intent = getIntent();
        String keywords = intent.getStringExtra("keywords");
        if (!TextUtils.isEmpty(keywords)){
            list.clear();
            etKeywordsSearch.setText(keywords);
            etShowKeywordsSearch.setText(keywords);

            presenter.getSearchProductsData(keywords,""+page,"0");

            llFlowSearch.setVisibility(View.GONE);
            llShowSearch.setVisibility(View.VISIBLE);
        }

    }

    //监听事件
    @Override
    protected void initListener() {
        super.initListener();
        //排序的监听
        rgSearch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId){
                    case R.id.rb_btn0_search :
                        list.clear();
                        String keyword0 = etKeywordsSearch.getText().toString();
                        presenter.getSearchProductsData(keyword0,page+"","0");
                        if (searchAdapter!=null){
                            searchAdapter.notifyDataSetChanged();
                        }
                        rbBtn0Search.setTextColor(Color.RED);
                        rbBtn1Search.setTextColor(Color.BLACK);
                        rbBtn2Search.setTextColor(Color.BLACK);
                        break;
                    case R.id.rb_btn1_search:
                        list.clear();
                        String keyword1 = etKeywordsSearch.getText().toString();
                        presenter.getSearchProductsData(keyword1,page+"","1");
                        if (searchAdapter!=null){
                            searchAdapter.notifyDataSetChanged();
                        }
                        rbBtn0Search.setTextColor(Color.BLACK);
                        rbBtn1Search.setTextColor(Color.RED);
                        rbBtn2Search.setTextColor(Color.BLACK);
                        break;
                     case R.id.rb_btn2_search:
                         list.clear();
                         String keyword2 = etKeywordsSearch.getText().toString();
                         presenter.getSearchProductsData(keyword2,page+"","2");
                         if (searchAdapter!=null){
                             searchAdapter.notifyDataSetChanged();
                         }
                         rbBtn0Search.setTextColor(Color.BLACK);
                         rbBtn1Search.setTextColor(Color.BLACK);
                         rbBtn2Search.setTextColor(Color.RED);
                         break;
                    case R.id.rb_btn3_search:
                        Toast.makeText(SearchActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
                        break;
                      default:
                          break;
                }
            }
        });
        //刷新监听
        srlGridShowSearch.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                presenter.getSearchProductsData(keywords, "" + page, "1");
                refreshLayout.finishLoadMore();
                if (searchAdapter != null) {
                    searchAdapter.notifyDataSetChanged();
                }
                if (mySearchGridAdapter != null) {
                    mySearchGridAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 1;
                presenter.getSearchProductsData(keywords, "" + page, "1");
                refreshLayout.finishRefresh();
                if (searchAdapter != null) {
                    searchAdapter.notifyDataSetChanged();
                }
                if (mySearchGridAdapter != null) {
                    mySearchGridAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void getSearchProductsDataSuccess(SearchBeans searchBeans) {
        final List<SearchBeans.DataBean> data = searchBeans.getData();
        if (page == 1){
            //清空集合
            list.clear();
        }
        list.addAll(data);
        //横向布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvShowSearch.setLayoutManager(linearLayoutManager);
        searchAdapter = new MySearchAdapter(list,SearchActivity.this);
        rvShowSearch.setAdapter(searchAdapter);

        //纵向布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchActivity.this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rvGridShowSearch.setLayoutManager(gridLayoutManager);
        //适配器
        mySearchGridAdapter = new MySearchGridAdapter(list,SearchActivity.this);
        rvGridShowSearch.setAdapter(mySearchGridAdapter);

        //横向条目单击事件 跳转商品详情页面 适配器单击事件
        searchAdapter.setSearch_onItemClickListener(new MySearchAdapter.Search_onItemClickListener() {
            @Override
            public void search_OnItemClick(View view, int i) {
                int pid = data.get(i).getPid();
                Intent intent = new Intent(SearchActivity.this,ShowActivity.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });

        //纵向条目单击事件 跳转商品详情页面 适配器单击事件
        mySearchGridAdapter.setGridonItemClickListener(new MySearchGridAdapter.GridOnItemClickListener() {
            @Override
            public void grid_onitemclick(View view, int i) {
                int pid = data.get(i).getPid();
                Intent intent = new Intent(SearchActivity.this,ShowActivity.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
    }
    //请求数据失败
    @Override
    public void getSearchProductsDataFailed(String error) {
        Log.d(TAG,"getSearchProductsDataFailed:"+error);
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
    @OnClick({R.id.btn_back_search, R.id.btn_cancel_search, R.id.btn_goto_search, R.id.tv_history_search, R.id.et_keywords_search, R.id.btn_change_search, R.id.et_show_keywords_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //1.返回
            case R.id.btn_back_search:
                Intent intent = new Intent();
                setResult(1,intent);
                finish();
                break;
                //2
            case R.id.et_keywords_search:
                llFlowSearch.setVisibility(View.VISIBLE);
                llShowSearch.setVisibility(View.GONE);
                initData();
                break;
                //3.搜索
            case R.id.btn_goto_search:
                keywords = etKeywordsSearch.getText().toString();
                //去重
                KeyWordsBeans unique = keyWordsBeansDao.queryBuilder()
                        .where(KeyWordsBeansDao.Properties.Keywords.eq(keywords))
                        .build()
                        .unique();
                if (unique == null){
                    //添加数据库
                    keyWordsBeansDao.insert(new KeyWordsBeans(null,keywords));
                    initData();
                }
                //输入框赋值
                etShowKeywordsSearch.setText(keywords);
                presenter.getSearchProductsData(keywords,""+page,"0");

                llFlowSearch.setVisibility(View.GONE);
                llShowSearch.setVisibility(View.VISIBLE);
                break;

                //4.清除数据
            case R.id.tv_history_search:
                keyWordsBeansDao.deleteAll();
                initData();
                break;
                //5.取消
            case R.id.btn_cancel_search:
                Intent intent1 = new Intent();
                setResult(1,intent1);
                finish();
                initData();
                break;
                //6.第二个搜索
            case R.id.et_show_keywords_search:
                llFlowSearch.setVisibility(View.VISIBLE);
                llShowSearch.setVisibility(View.GONE);
                initData();
                break;
                //7.改变按钮
            case R.id.btn_change_search:
                int visibility = llRvShowSearch.getVisibility();
                if (visibility ==View.VISIBLE){
                    llRvShowSearch.setVisibility(View.GONE);
                    llRvGridShowSearch.setVisibility(View.VISIBLE);
                    btnChangeSearch.setBackgroundResource(R.drawable.kind_grid);
                }else {
                    llRvShowSearch.setVisibility(View.VISIBLE);
                    llRvGridShowSearch.setVisibility(View.GONE);
                    btnChangeSearch.setBackgroundResource(R.drawable.kind_liner);
                }
                break;
        }
    }
}
