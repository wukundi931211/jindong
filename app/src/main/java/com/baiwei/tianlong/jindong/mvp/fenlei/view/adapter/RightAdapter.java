package com.baiwei.tianlong.jindong.mvp.fenlei.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.mvp.fenlei.model.beans.RightFenLeiBeans;
import com.baiwei.tianlong.jindong.mvp.search.SearchActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.RightHolder>{
    List<RightFenLeiBeans.DataBean> data;
    private Context context;

    public RightAdapter(List<RightFenLeiBeans.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RightHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_right_classify, viewGroup, false);

        return new RightHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RightHolder rightHolder, int i) {
        rightHolder.tvRecycleItemClassify.setText(data.get(i).getName());
        //获得右边的数据
        final List<RightFenLeiBeans.DataBean.ListBean> list1 = data.get(i).getList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        rightHolder.rvItemClassify.setLayoutManager(gridLayoutManager);

        MyGridAdapter gridAdapter = new MyGridAdapter(list1);
        rightHolder.rvItemClassify.setAdapter(gridAdapter);

        gridAdapter.setOnItemClickListener(new MyGridAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                String name = list1.get(position).getName();
                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("keywords", name);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class RightHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_recycle_item_classify)
        TextView tvRecycleItemClassify;
        @BindView(R.id.rv_item_classify)
        RecyclerView rvItemClassify;
        public RightHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
