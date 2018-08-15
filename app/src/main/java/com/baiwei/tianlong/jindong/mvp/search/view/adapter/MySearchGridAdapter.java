package com.baiwei.tianlong.jindong.mvp.search.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.mvp.search.model.beans.SearchBeans;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySearchGridAdapter extends RecyclerView.Adapter<MySearchGridAdapter.MyGridSearchHolder> {

    private List<SearchBeans.DataBean> data;
    private Context context;

    public MySearchGridAdapter(List<SearchBeans.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyGridSearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_grid_item, viewGroup, false);
        return new MyGridSearchHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGridSearchHolder myGridSearchHolder, final int i) {
        myGridSearchHolder.searchTitleHome.setText(data.get(i).getTitle());
        myGridSearchHolder.searchPriceHome.setText("￥"+ data.get(i).getPrice());

        String img_url = data.get(i).getImages().split("\\|")[0];

        Glide.with(context).load(img_url).into(myGridSearchHolder.searchImgHome);
        //跳转详情页
        myGridSearchHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gridonItemClickListener != null){
                    gridonItemClickListener.grid_onitemclick(view,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class MyGridSearchHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_img_home)
        ImageView searchImgHome;
        @BindView(R.id.search_title_home)
        TextView searchTitleHome;
        @BindView(R.id.search_price_home)
        TextView searchPriceHome;
        public MyGridSearchHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    GridOnItemClickListener  gridonItemClickListener;

    public interface GridOnItemClickListener {
        void grid_onitemclick(View view ,int i);
    }
    public void setGridonItemClickListener(GridOnItemClickListener gridonItemClickListener) {
        this.gridonItemClickListener = gridonItemClickListener;
    }
}
