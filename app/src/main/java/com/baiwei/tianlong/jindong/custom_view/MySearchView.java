package com.baiwei.tianlong.jindong.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.baiwei.tianlong.jindong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySearchView extends LinearLayout {

    @BindView(R.id.btn_left)
    Button btnLeft;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.btn_right)
    Button btnRight;

    public MySearchView(Context context) {
        super(context);

    }

    public MySearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }
    @SuppressLint("ResourceAsColor")
    public MySearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //自定义布局的样式
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MySearchView, 0, 0);

        //左边扫一扫
        String left_image = typedArray.getString(R.styleable.MySearchView_left_image);
        int left_Background = typedArray.getResourceId(R.styleable.MySearchView_left_background, R.color.colorRed);
        boolean left_Visibility = typedArray.getBoolean(R.styleable.MySearchView_left_visibility, true);

        //editText
        String searchText = typedArray.getString(R.styleable.MySearchView_edit_search);
        String searchHintText = typedArray.getString(R.styleable.MySearchView_search_hint_text);
        int searchBackground = typedArray.getResourceId(R.styleable.MySearchView_search_background, R.color.colortou);
        boolean searchVisibility = typedArray.getBoolean(R.styleable.MySearchView_search_visibility, true);

        String right_image = typedArray.getString(R.styleable.MySearchView_right_image);
        int right_Background = typedArray.getResourceId(R.styleable.MySearchView_right_background, R.color.colorRed);
        boolean right_Visibility = typedArray.getBoolean(R.styleable.MySearchView_right_visibility, true);



        //指定布局的id 添加到当前页面
        View view =inflate(context,R.layout.search_view,this);

        ButterKnife.bind(view);

        btnLeft.setText(left_image);
        btnLeft.setBackgroundResource(left_Background);
        btnLeft.setVisibility(left_Visibility ? VISIBLE : INVISIBLE);

        etSearch.setHint(searchHintText);
        etSearch.setText(searchText);
        etSearch.setVisibility(searchVisibility ? VISIBLE : INVISIBLE);
        etSearch.setBackgroundResource(searchBackground);


        btnRight.setText(right_image);
        btnRight.setBackgroundResource(right_Background);
        btnRight.setVisibility(right_Visibility ? VISIBLE : INVISIBLE);
    }


    @OnClick({R.id.btn_left, R.id.et_search, R.id.btn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_left:
                if (onMyTitleViewClickListener != null){
                    onMyTitleViewClickListener.leftClick();
                }
                break;
            case R.id.et_search:
                if (onMyTitleViewClickListener != null){
                    onMyTitleViewClickListener.searhClick();
                }
                break;
            case R.id.btn_right:
                if (onMyTitleViewClickListener != null){
                    onMyTitleViewClickListener.rightClick();
                }
                break;
        }
    }


    //自定义接口
    OnMyTitleViewClickListener onMyTitleViewClickListener;

    public void setOnMyTitleViewClickListener(OnMyTitleViewClickListener onMyTitleViewClickListener) {
        this.onMyTitleViewClickListener = onMyTitleViewClickListener;
    }

    public interface OnMyTitleViewClickListener {
        void leftClick();

        void searhClick();

        void rightClick();
    }
}
