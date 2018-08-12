package com.baiwei.tianlong.jindong.mvp.fenlei.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.LeftFenLeiBeans;

import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.LeftHolder>{
    private List<LeftFenLeiBeans.DataBean> data;
    private Context context;

    public LeftAdapter(List<LeftFenLeiBeans.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public LeftHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fen_lei_leftlayout,viewGroup,false);

        return new LeftHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeftHolder leftHolder, final int i) {
        final LeftFenLeiBeans.DataBean dataBean = data.get(i);

        leftHolder.textView.setText(dataBean.getName());
            if (i == selectedItem){
                leftHolder.textView.setBackgroundColor(Color.WHITE);
            }else {
                leftHolder.textView.setBackgroundColor(Color.TRANSPARENT);
            }

            leftHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(dataBean,i);

                }
            });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class LeftHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public LeftHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_list_item);
        }
    }


    //判断选中状态
    private int selectedItem = -1;

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    //单击事件
    //监听
    private ItemClickListencer listener;

    public interface ItemClickListencer {
        void onItemClick( LeftFenLeiBeans.DataBean dataBean,int position);
    }

    public void setListener(ItemClickListencer listener) {
        this.listener = listener;
    }
}
