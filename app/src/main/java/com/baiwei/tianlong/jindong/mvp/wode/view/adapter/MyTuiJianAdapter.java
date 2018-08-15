package com.baiwei.tianlong.jindong.mvp.wode.view.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.mvp.wode.model.beans.HomeAdBean;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTuiJianAdapter extends RecyclerView.Adapter<MyTuiJianAdapter.TuiJianViewHolder>{

    private transient List<HomeAdBean.TuijianBean.ListBean> list;
    private Context context;

    public MyTuiJianAdapter(List<HomeAdBean.TuijianBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TuiJianViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("aaa", "xxxxx 4");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tuijian_layout, viewGroup, false);

        return new TuiJianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TuiJianViewHolder tuiJianViewHolder, final int position) {
        Log.d("aaa", "xxxxx 5");
        String imageUrl = list.get(position).getImages().split("\\|")[0];

       // tuiJianViewHolder.tuijianSimpleview.setImageURI(imageUrl);
        Glide.with(context).load(imageUrl).into(tuiJianViewHolder.tuijianSimpleview);
        tuiJianViewHolder.tuijianTitle.setText(list.get(position).getTitle());
        tuiJianViewHolder.tuijianPrice.setText("￥" + list.get(position).getBargainPrice());


        //单击事件
        tuiJianViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClick(view, position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    public class TuiJianViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tuijian_simpleview)
        SimpleDraweeView tuijianSimpleview;
        @BindView(R.id.tuijian_title)
        TextView tuijianTitle;
        @BindView(R.id.tuijian_price)
        TextView tuijianPrice;
        public TuiJianViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
