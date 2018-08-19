package com.baiwei.tianlong.jindong.mvp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baiwei.tianlong.jindong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAddSumView extends LinearLayout {
    @BindView(R.id.tv_sub_view)
    TextView tvSubView;
    @BindView(R.id.tv_number_view)
    TextView tvNumberView;
    @BindView(R.id.tv_add_view)
    TextView tvAddView;
    private int number = 1;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        tvNumberView.setText(number + "");
    }

    public MyAddSumView(Context context) {
        super(context);
    }

    public MyAddSumView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyAddSumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflate = View.inflate(context, R.layout.my_add_sub_view, this);
        ButterKnife.bind(inflate);

    }

    @OnClick({R.id.tv_sub_view, R.id.tv_add_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sub_view:
                if (number > 1) {
                    --number;
                    tvNumberView.setText(number + "");
                    if (onNumberChangeListener != null) {
                        onNumberChangeListener.onNumberChange(number);
                    }
                } else {
                    Toast.makeText(getContext(), "不能在少啦", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_add_view:
                ++number;
                tvNumberView.setText(number + "");
                if (onNumberChangeListener != null) {
                    onNumberChangeListener.onNumberChange(number);
                }
                break;
        }
    }

    onNumberChangeListener onNumberChangeListener;



    public void setOnNumberChangeListener(MyAddSumView.onNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }

    public interface onNumberChangeListener {
        void onNumberChange(int num);
    }
    }


