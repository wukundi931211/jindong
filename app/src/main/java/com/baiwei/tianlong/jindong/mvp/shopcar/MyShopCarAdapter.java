package com.baiwei.tianlong.jindong.mvp.shopcar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.mvp.MyAddSumView;
import com.baiwei.tianlong.jindong.mvp.shopcar.model.beans.ShopCarBeans;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author JenSenLeung.
 * @Time 2018/7/12 下午 2:58.
 * @Description This is MyShopCarAdapter.
 */
public class MyShopCarAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "MyShopCarAdapter--";

    private List<ShopCarBeans.DataBean> data;
    private boolean allProductsSelected;
    private ChildViewHolder childViewHolder;
    private boolean isEdit = true;

    public MyShopCarAdapter(List<ShopCarBeans.DataBean> list) {
        this.data = list;
    }

    @Override
    public int getGroupCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getList() == null ? 0 : data.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.group_view_layout, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        //赋值
        //商家名称
        groupViewHolder.tvTitleGroup.setText(data.get(groupPosition).getSellerName());
        boolean currentSellerAllProductSelected = isCurrentSellerAllProductSelected(groupPosition);
        //商家checkbox
        groupViewHolder.cbGroupItem.setChecked(currentSellerAllProductSelected);
        groupViewHolder.cbGroupItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onShopCarListChangeListener != null) {
                    onShopCarListChangeListener.SellerSelectedChange(groupPosition);
                }
            }
        });

        return convertView;

    }

    //当前商家所有商品的选中状态
    public boolean isCurrentSellerAllProductSelected(int groupPosition) {
        List<ShopCarBeans.DataBean.ListBean> list = data.get(groupPosition).getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSelected() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.child_view_layout, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        //赋值
        final ShopCarBeans.DataBean.ListBean listBean = data.get(groupPosition).getList().get(childPosition);
        //商品名称
        childViewHolder.tvTitleChild.setText(listBean.getTitle());
        //商品价格
        childViewHolder.tvPriceChild.setText("￥" + listBean.getBargainPrice());
        String image = listBean.getImages().split("\\|")[0];
        //商品图片
        childViewHolder.sdvIconChild.setImageURI(Uri.parse(image));

        //商品数量
        childViewHolder.masvChild.setNumber(listBean.getNum());
        childViewHolder.masvChild.setOnNumberChangeListener(new MyAddSumView.onNumberChangeListener() {
            @Override
            public void onNumberChange(int num) {
                if (onShopCarListChangeListener != null) {
                    onShopCarListChangeListener.ProductNumberChange(groupPosition, childPosition, num);
                }
            }
        });
        //商品checkbox
        childViewHolder.cbChildItem.setChecked(listBean.getSelected() == 1);
        childViewHolder.cbChildItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onShopCarListChangeListener != null) {
                    onShopCarListChangeListener.ProductSelectedChange(groupPosition, childPosition);
                }
            }
        });

        if (isEdit) {
            childViewHolder.tvDelChild.setVisibility(View.GONE);
        } else {
            childViewHolder.tvDelChild.setVisibility(View.VISIBLE);
        }
        //删除条目数据
        childViewHolder.tvDelChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("警示框");
                builder.setMessage("现在要删除该商品吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //获取要删除的pid
                        int pid = data.get(groupPosition).getList().get(childPosition).getPid();
                        data.get(groupPosition).getList().remove(childPosition);
                        notifyDataSetChanged();
                        if (onShopCarListChangeListener != null) {

                            onShopCarListChangeListener.DeleteCurrentProduct(groupPosition, childPosition, pid);
                        }
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();

            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //设置删除按钮的可见性
    public void setDelButtonVisibility() {
        isEdit = isEdit == true ? false : true;
    }

    //改变当前商家所有商品的状态
    public void changeCurrentSellerAllProductSelected(boolean selected, int groupPosition) {
        List<ShopCarBeans.DataBean.ListBean> list = data.get(groupPosition).getList();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelected(selected ? 1 : 0);
        }
    }

    //单前商品的选中状态
    public boolean isCurrentProductSelected(int groupPosition, int childPosition) {
        ShopCarBeans.DataBean.ListBean listBean = data.get(groupPosition).getList().get(childPosition);
        if (listBean.getSelected() == 0) {
            return false;
        }
        return true;
    }

    //改变当前商品的选中状态
    public void changeCurrentProductSelected(int groupPosition, int childPosition, boolean selected) {
        ShopCarBeans.DataBean.ListBean listBean = data.get(groupPosition).getList().get(childPosition);
        listBean.setSelected(selected ? 1 : 0);
    }

    //当前商品数量的改变
    public void changeCurrentProductNumber(int groupPosition, int childPosition, int number) {
        ShopCarBeans.DataBean.ListBean listBean = data.get(groupPosition).getList().get(childPosition);
        listBean.setNum(number);
    }

    //所有商品的选中状态
    public boolean isAllProductsSelected() {
        for (int i = 0; i < data.size(); i++) {
            List<ShopCarBeans.DataBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //改变所有商品的选中状态
    public void changeAllProductSelected(boolean selected) {
        for (int i = 0; i < data.size(); i++) {
            List<ShopCarBeans.DataBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                list.get(j).setSelected(selected ? 1 : 0);
            }
        }
    }

    //计算总价
    public float calculateAllPrice() {
        int totalPrice = 0;
        for (int i = 0; i < data.size(); i++) {
            List<ShopCarBeans.DataBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 1) {
                    float price = list.get(j).getBargainPrice();
                    int num = list.get(j).getNum();
                    totalPrice += price * num;
                }
            }
        }
        return totalPrice;
    }

    //计算总数量
    public int calculateAllNumber() {
        int totalNumber = 0;
        for (int i = 0; i < data.size(); i++) {
            List<ShopCarBeans.DataBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 1) {
                    int num = list.get(j).getNum();
                    totalNumber += num;
                }
            }
        }
        return totalNumber;
    }

    static class GroupViewHolder {
        @BindView(R.id.cb_group_item)
        CheckBox cbGroupItem;
        @BindView(R.id.tv_title_group)
        TextView tvTitleGroup;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.cb_child_item)
        CheckBox cbChildItem;
        @BindView(R.id.sdv_icon_child)
        SimpleDraweeView sdvIconChild;
        @BindView(R.id.tv_title_child)
        TextView tvTitleChild;
        @BindView(R.id.tv_price_child)
        TextView tvPriceChild;
        @BindView(R.id.masv_child)
        MyAddSumView masvChild;
        @BindView(R.id.tv_del_child)
        TextView tvDelChild;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    OnShopCarListChangeListener onShopCarListChangeListener;

    public void setOnShopCarListChangeListener(OnShopCarListChangeListener onShopCarListChangeListener) {
        this.onShopCarListChangeListener = onShopCarListChangeListener;
    }

    public interface OnShopCarListChangeListener {
        void SellerSelectedChange(int groupPosition);

        void ProductSelectedChange(int groupPosition, int childPosition);

        void ProductNumberChange(int groupPosition, int childPosition, int number);

        void DeleteCurrentProduct(int groupPosition, int childPosition, int pid);

    }
}
