package com.baiwei.tianlong.jindong.mvp.fenlei;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseFragment;
import com.baiwei.tianlong.jindong.custom_view.MySearchView;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.LeftFenLeiBeans;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.RightFenLeiBeans;
import com.baiwei.tianlong.jindong.mvp.fenlei.presenter.FenLeiPresenter;
import com.baiwei.tianlong.jindong.mvp.fenlei.view.FenLeiView;
import com.baiwei.tianlong.jindong.mvp.fenlei.view.adapter.LeftAdapter;
import com.baiwei.tianlong.jindong.mvp.fenlei.view.adapter.RightAdapter;
import com.baiwei.tianlong.jindong.mvp.search.SearchActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentFenLei extends BaseFragment<FenLeiPresenter> implements FenLeiView {

    @BindView(R.id.searchview_fenlei)
    MySearchView searchviewFenlei;
    @BindView(R.id.left_recycle)
    RecyclerView leftRecycle;
    @BindView(R.id.rv_classify)
    RecyclerView rvClassify;
    Unbinder unbinder;

    public static FragmentFenLei newInstance(String param1) {
        FragmentFenLei fragmentFenLei = new FragmentFenLei();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragmentFenLei.setArguments(args);
        return fragmentFenLei;
    }

    private static final String TAG = "ClassifyFragment--";
    private String cid = "1";
    private int arg;
    private LeftAdapter leftAdapter;
    private int selectedItem;


    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideFragmentLayoutID() {
        return R.layout.fragment_fenlei;
    }

    @Override
    protected FenLeiPresenter providePresenter() {
        return new FenLeiPresenter(this);
    }

    //初始化
    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    //展示数据
    @Override
    protected void initData() {
        super.initData();
        if (presenter !=null){
            presenter.getLeftData();
        }
    }

    //监听
    @Override
    protected void initListener() {
        super.initListener();
        searchviewFenlei.setOnMyTitleViewClickListener(new MySearchView.OnMyTitleViewClickListener() {
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
    }

    @Override
    public void getLeftDataSuccess(LeftFenLeiBeans leftFenLeiBeans) {
        List<LeftFenLeiBeans.DataBean> data = leftFenLeiBeans.getData();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        leftAdapter = new LeftAdapter(data,getContext());

        leftAdapter.setSelectedItem(0);

        if (leftRecycle!=null){
            leftRecycle.setAdapter(leftAdapter);
            leftRecycle.setLayoutManager(manager);

            leftAdapter.setListener(new LeftAdapter.ItemClickListencer() {
                @Override
                public void onItemClick(LeftFenLeiBeans.DataBean dataBean, int position) {
                    int cid = dataBean.getCid();

                    presenter.getRightData(cid+"");
                    leftAdapter.setSelectedItem(position);
                    leftAdapter.notifyDataSetChanged();
                }
            });
        }

        presenter.getRightData(cid);
    }

    @Override
    public void getLeftFailed(String error) {
        Log.d(TAG, "getLeftDataFailed: " + error);
    }

    @Override
    public void getRightDataSuccess(RightFenLeiBeans rightFenLeiBeans) {
        List<RightFenLeiBeans.DataBean> data = rightFenLeiBeans.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //适配器
        RightAdapter rightAdapter = new RightAdapter(data,getContext());
        if (rvClassify!=null){
            rvClassify.setLayoutManager(linearLayoutManager);
            rvClassify.setAdapter(rightAdapter);

        }




    }

    @Override
    public void getRightFailed(String error) {
        Log.d(TAG, "getRightDataFailed: " + error);
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
