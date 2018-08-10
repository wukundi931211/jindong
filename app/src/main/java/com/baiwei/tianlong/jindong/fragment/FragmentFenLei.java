package com.baiwei.tianlong.jindong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.custom_view.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentFenLei extends Fragment {


    @BindView(R.id.searchview_fenlei)
    SearchView searchviewFenlei;
    Unbinder unbinder;

    public static FragmentFenLei newInstance(String param1) {
        FragmentFenLei fragmentFenLei = new FragmentFenLei();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragmentFenLei.setArguments(args);
        return fragmentFenLei;
    }
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //清空
        if (view == null){
            view = inflater.inflate(R.layout.fragment_fenlei, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();

        if (parent!= null){
            parent.removeView(view);
        }


        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
