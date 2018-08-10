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
import com.baiwei.tianlong.jindong.mvp.home.model.beans.HomeBeans;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MiaoShaAdapter extends RecyclerView.Adapter<MiaoShaAdapter.MiaoShaHolder> {
    private transient List<HomeBeans.MiaoshaBean.ListBeanX> list1;

    private Context context;

    public MiaoShaAdapter(List<HomeBeans.MiaoshaBean.ListBeanX> list1, Context context) {
        this.list1 = list1;
        this.context = context;
    }

    @NonNull
    @Override
    public MiaoShaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.miaosha_layout, viewGroup, false);

        return new MiaoShaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MiaoShaHolder miaoShaHolder, final int i) {
        String split = list1.get(i).getImages().split("\\|")[0];
        miaoShaHolder.sdvImgMiaoshaHome.setImageURI(Uri.parse(split));

        miaoShaHolder.miaoshaTitle.setText(list1.get(i).getPrice()+"");

        miaoShaHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClick(view, i);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list1 == null ? 0 : list1.size();
    }

    public class MiaoShaHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdv_img_miaosha_home)
        SimpleDraweeView sdvImgMiaoshaHome;
        @BindView(R.id.miaosha_title)
        TextView miaoshaTitle;
        public MiaoShaHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }


}
