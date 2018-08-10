package com.baiwei.tianlong.jindong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baiwei.tianlong.jindong.R;

public class FragmentFaXian extends Fragment{

    public static FragmentFaXian newInstance(String param1) {
        FragmentFaXian fragmentFaXian = new FragmentFaXian();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragmentFaXian.setArguments(args);
        return fragmentFaXian;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faxian,container,false);

        return view;
    }
}
