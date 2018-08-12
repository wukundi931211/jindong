package com.baiwei.tianlong.jindong.mvp.fenlei.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.RightFenLeiBeans;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.MyGridHolder>{
    List<RightFenLeiBeans.DataBean.ListBean> list1;
    private Context context;

    public MyGridAdapter(List<RightFenLeiBeans.DataBean.ListBean> list1) {
        this.list1 = list1;
    }

    @NonNull
    @Override
    public MyGridHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_grid_view_classify, viewGroup, false);
        return new MyGridHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGridHolder myGridHolder, final int i) {
        myGridHolder.tvGridItemClassify.setText(list1.get(i).getName());
        myGridHolder.sdvGridViewClassify.setImageURI(Uri.parse(list1.get(i).getIcon()));

        //条目点击事件  //keywords
        myGridHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener!=null) {
                    onItemClickListener.OnItemClick(view,i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1 == null ? 0 : list1.size();
    }

    public class MyGridHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.sdv_grid_view_classify)
        SimpleDraweeView sdvGridViewClassify;
        @BindView(R.id.tv_grid_item_classify)
        TextView tvGridItemClassify;

        public MyGridHolder(@NonNull View itemView) {
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
