package com.baiwei.tianlong.jindong.mvp.search.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup{

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量所有孩子的宽高
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//精确测量的宽
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);//测量宽的模式
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);//精确测量的高
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);//精确测量的模式

        int width = 0;//AT—MOST模式的宽
        int height = 0;//AT—MOST模式的高
        int lineWidth = 0;//行宽
        int lineHeight = 0;//行高
        int totalHeight = 0;//前面的行的行高的和****累加高度
        View childView;
        int childWidth = 0;//孩子的宽
        int childHeight = 0;//孩子对的高

        for (int i = 0; i <getChildCount() ; i++) {
            childView = getChildAt(i);//获取到当前孩子的view
            childWidth = childView.getMeasuredWidth();//当前孩子的宽
            childHeight = childView.getMeasuredHeight();//当前孩子的高
            //当孩子的宽太大的时候
            if (childWidth > widthSize){
                //跑异常
              throw   new IllegalArgumentException("子view宽度不能大于FlowLayout宽度");
            }

            //换行 & 不换行
            if (childWidth +childHeight >widthSize){
                //换行
                //换行说明布局的宽度达到了最大
                width = widthSize;
                //累加的高度开始累加
                totalHeight += lineHeight;
                //行宽就是当前孩子的宽度,因为换行的孩子都是这一行的第一个
                lineWidth = childWidth;
                lineHeight = childHeight;
            }else {
                //不换行
                //行宽开始累加
                lineWidth += childWidth;
                //行高取最大值
                lineHeight = Math.max(lineHeight,childHeight);
                //假如只有一行，测量的宽度就是孩子的宽
                width = Math.max(lineWidth,width);
            }

            //结束遍历的时候计算高度
            if (i == getChildCount() - 1){
                //总高度就是累积的行高加上最后一行的行高
                totalHeight += lineHeight;
                height = totalHeight;
            }
        }

        //对测量模式进行判断
        width = widthMode == MeasureSpec.EXACTLY ? widthSize :width;
        height = heightMode == MeasureSpec.EXACTLY ? heightSize : height;

        //确定最后的宽高
        setMeasuredDimension(width,height);
    }

    //
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineWidth = 0;//行宽
        int lineHeight = 0;//行高
        int totalHeight = 0;//前面的行的行高的和****累加高度
        View childView;
        int childWidth = 0;//孩子的宽
        int childHeight = 0;//孩子对的高

        for (int i = 0; i < getChildCount(); i++) {
            childView = getChildAt(i);//获取到当前孩子的view
            childWidth = childView.getMeasuredWidth();//当前孩子的宽
            childHeight = childView.getMeasuredHeight();//当前孩子的高

            //换行&不换行
            if (childWidth + lineWidth > getMeasuredWidth()) {
                //换行
                //累加高度开始累加
                totalHeight += lineHeight;
                lineWidth = 0;

                //设置孩子布局
                childViewLayout(childView, lineWidth, totalHeight, lineWidth + childWidth, totalHeight + childHeight);

                //行款就是当前孩子的宽度,因为换行的孩子都是这一行的第一个
                lineWidth = childWidth;
                lineHeight = childHeight;
                //
            } else {
                //不换行
                //设置孩子布局
                childViewLayout(childView, lineWidth, totalHeight, lineWidth + childWidth, totalHeight + childHeight);
                //行宽开始累加
                lineWidth += childWidth;
                //行高取最大值
                lineHeight = Math.max(lineHeight, childHeight);
            }

        }
    }

    private void childViewLayout(View childView, int left, int top, int right, int buttom) {
        childView.layout(left,top,right,buttom);
    }
}
