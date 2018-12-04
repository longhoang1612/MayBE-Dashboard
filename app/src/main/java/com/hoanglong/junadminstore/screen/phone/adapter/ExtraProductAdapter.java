package com.hoanglong.junadminstore.screen.phone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.phone_product.ListExtraProduct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExtraProductAdapter extends RecyclerView.Adapter<ExtraProductAdapter.ItemExtra> {

    private List<ListExtraProduct> mExtraProductList;
    private LayoutInflater mInflater;

    public ExtraProductAdapter(List<ListExtraProduct> extraProductList) {
        mExtraProductList = extraProductList;
    }

    @NonNull
    @Override
    public ItemExtra onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_extra_product, viewGroup, false);
        return new ItemExtra(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemExtra itemExtra, int i) {
        ListExtraProduct mExtra = mExtraProductList.get(i);
        itemExtra.bindData(mExtra);
    }

    @Override
    public int getItemCount() {
        return mExtraProductList != null ? mExtraProductList.size()-1 : 0;
    }

    static class ItemExtra extends RecyclerView.ViewHolder {

        @BindView(R.id.image_extra_item)
        ImageView mImageExtra;
        @BindView(R.id.text_extra_item)
        TextView mTextExtra;
        private Context mContext;

        ItemExtra(@NonNull View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            if (context == null)
                return;
            mContext = context;
        }

        void bindData(ListExtraProduct mExtra) {
            if (mExtra == null) {
                return;
            }
            Glide.with(mContext).load(mExtra.getImageExtra()).into(mImageExtra);
            mTextExtra.setText(mExtra.getTitleExtra());
        }
    }
}
