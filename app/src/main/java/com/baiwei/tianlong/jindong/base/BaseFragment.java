package com.baiwei.tianlong.jindong.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baiwei.tianlong.jindong.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected P presenter;
    private View view;
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        if (view == null){
            view = inflater.inflate(provideFragmentLayoutID(), container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();

        if (parent!= null){
            parent.removeView(view);
        }

        //ButterKifer
        unbinder = ButterKnife.bind(provideBindView(),view);
        return view;
    }

    protected abstract Object provideBindView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = providePresenter();
        //初始化
        initView(view);
        initData();
        initListener();

    }

    //FragmentID
    protected abstract int provideFragmentLayoutID();
    //注入P 层
    protected abstract P providePresenter();

    protected void initView(View view) {
    }
    protected void initData() {
    }
    protected void initListener() {
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Fresco 清理缓存
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
        imagePipeline.clearDiskCaches();
        imagePipeline.clearCaches();

        unbinder.unbind();
    }
}
