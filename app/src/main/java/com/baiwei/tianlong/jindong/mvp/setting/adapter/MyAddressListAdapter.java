package com.baiwei.tianlong.jindong.mvp.setting.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.AddressBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/20 下午 2:40.
 * @Description This is MyAddressListAdapter.
 */
public class MyAddressListAdapter extends RecyclerView.Adapter<MyAddressListAdapter.AddressListViewHolder> {

    private List<AddressBean.DataBean> list;

    public MyAddressListAdapter(List<AddressBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AddressListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_address_list_item, parent, false);
        return new AddressListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressListViewHolder holder, final int position) {
        holder.tvNameAddressItem.setText(list.get(position).getName());
        Long mobileNUm = list.get(position).getMobile();
        final String mobile = String.valueOf(mobileNUm);
        String substring1 = mobile.substring(0, 3);
        String substring2 = mobile.substring(7, mobile.length());
        holder.tvMobileAddressItem.setText(substring1 + "****" + substring2);
        holder.tvAddressAddress.setText(list.get(position).getAddr());
        if (list.get(position).getStatus() == 1) {
            holder.cbAddressItem.setChecked(true);
            holder.tvCheckAddressItem.setText("默认地址");
        } else {
            holder.cbAddressItem.setChecked(false);
            holder.tvCheckAddressItem.setText("设为默认");
        }

        holder.cbAddressItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int addrid = list.get(position).getAddrid();
                if (onAddressListListener != null) {
                    onAddressListListener.addressListCheckbox(addrid);
                }
            }
        });

        holder.llEditAddressItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddressListListener != null) {
                    String addr = list.get(position).getAddr();
                    int addrid = list.get(position).getAddrid();
                    long mobile1 = list.get(position).getMobile();
                    String name = list.get(position).getName();
                    onAddressListListener.addressListEdit(addr, addrid, mobile1, name);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class AddressListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_address_item)
        TextView tvNameAddressItem;
        @BindView(R.id.tv_mobile_address_item)
        TextView tvMobileAddressItem;
        @BindView(R.id.tv_address_address)
        TextView tvAddressAddress;
        @BindView(R.id.cb_address_item)
        CheckBox cbAddressItem;
        @BindView(R.id.tv_check_address_item)
        TextView tvCheckAddressItem;
        @BindView(R.id.ll_edit_address_item)
        LinearLayout llEditAddressItem;

        public AddressListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnAddressListListener onAddressListListener;

    public void setOnAddressListListener(OnAddressListListener onAddressListListener) {
        this.onAddressListListener = onAddressListListener;
    }

    public interface OnAddressListListener {
        void addressListCheckbox(int addrid);

        void addressListEdit(String addr, int addrid, long mobile, String name);
    }
}
