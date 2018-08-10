package com.baiwei.tianlong.jindong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baiwei.tianlong.jindong.R;

public class FragmentDingdan extends Fragment{

    public static FragmentDingdan newInstance(String param1) {
        FragmentDingdan fragmentDingdan = new FragmentDingdan();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragmentDingdan.setArguments(args);
        return fragmentDingdan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dingdan,container,false);

        return view;
    }
}
