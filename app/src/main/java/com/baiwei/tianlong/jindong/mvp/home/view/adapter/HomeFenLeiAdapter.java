package com.baiwei.tianlong.jindong.mvp.home.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeFenLeiBeans;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFenLeiAdapter extends RecyclerView.Adapter<HomeFenLeiAdapter.HomeFenLeiHolder> {

    private  transient List<HomeFenLeiBeans.DataBean> data;
    private Context context;

    public HomeFenLeiAdapter(List<HomeFenLeiBeans.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public HomeFenLeiHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.homefen_layout, viewGroup, false);
        return new HomeFenLeiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFenLeiHolder homeFenLeiHolder, final int position) {
        homeFenLeiHolder.fenleiTitle.setText(data.get(position).getName());
        String icon = data.get(position).getIcon();
       // Glide.with(context).load(icon).into(homeFenLeiHolder.sdvImgFenleiHome);
       homeFenLeiHolder.sdvImgFenleiHome.setImageURI(data.get(position).getIcon());

        homeFenLeiHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener !=null){
                    onItemClickListener.OnItenClick(view,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class HomeFenLeiHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdv_img_fenlei_home)
        SimpleDraweeView sdvImgFenleiHome;
        @BindView(R.id.fenlei_title)
        TextView fenleiTitle;
        public HomeFenLeiHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    //单击事件
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void OnItenClick(View view ,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
