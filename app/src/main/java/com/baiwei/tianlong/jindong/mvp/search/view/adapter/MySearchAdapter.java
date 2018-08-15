package com.baiwei.tianlong.jindong.mvp.search.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.mvp.search.model.beans.SearchBeans;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySearchAdapter extends RecyclerView.Adapter<MySearchAdapter.MySearchHolder> {

    private List<SearchBeans.DataBean> data;
    private Context context;

    public MySearchAdapter(List<SearchBeans.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MySearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_search_layout, viewGroup, false);
        return new MySearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MySearchHolder mySearchHolder, final int i) {
            mySearchHolder.tvTitleSearchItem.setText(data.get(i).getTitle());
            mySearchHolder.tvPriceSearchItem.setText(data.get(i).getPrice()+"");

        String img_url = data.get(i).getImages().split("\\|")[0];
        mySearchHolder.imgSearchItem.setImageURI(Uri.parse(img_url));

        mySearchHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (search_onItemClickListener!= null){
                    search_onItemClickListener.search_OnItemClick(view,i);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class MySearchHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_search_item)
        SimpleDraweeView imgSearchItem;
        @BindView(R.id.tv_title_search_item)
        TextView tvTitleSearchItem;
        @BindView(R.id.tv_price_search_item)
        TextView tvPriceSearchItem;
        public MySearchHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    Search_onItemClickListener search_onItemClickListener;

    public interface Search_onItemClickListener {
        void search_OnItemClick(View view , int i);
    }

    public void setSearch_onItemClickListener(Search_onItemClickListener search_onItemClickListener) {
        this.search_onItemClickListener = search_onItemClickListener;
    }
}
