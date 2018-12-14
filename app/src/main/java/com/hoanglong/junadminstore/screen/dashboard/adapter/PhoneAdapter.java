package com.hoanglong.junadminstore.screen.dashboard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;

import java.util.List;


public class PhoneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemPhoneProduct> mPhoneProducts;
    private LayoutInflater mLayoutInflater;
    private OnClickProductListener mOnClickProductListener;
    private final int VIEW_ITEM = 1;

    public PhoneAdapter(List<ItemPhoneProduct> phoneProducts, OnClickProductListener onClickProductListener) {
        mPhoneProducts = phoneProducts;
        mOnClickProductListener = onClickProductListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        if (i == VIEW_ITEM) {
            View view = mLayoutInflater.inflate(R.layout.item_product, viewGroup, false);
            return new ItemPhoneViewHolder(view, viewGroup.getContext(), mOnClickProductListener);
        } else {
            View view = mLayoutInflater.inflate(R.layout.item_loading, viewGroup, false);
            return new ProgressViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder itemPhoneViewHolder, int i) {
        ItemPhoneProduct itemPhoneProduct = mPhoneProducts.get(i);
        if (getItemViewType(i) == VIEW_ITEM) {
            ((ItemPhoneViewHolder) itemPhoneViewHolder).bindData(itemPhoneProduct);
        } else {
            ((ProgressViewHolder) itemPhoneViewHolder).mProgressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mPhoneProducts != null ? mPhoneProducts.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        int VIEW_LOADING = 0;
        return mPhoneProducts.get(position) == null ? VIEW_LOADING : VIEW_ITEM;
    }

    public interface OnClickProductListener {
        void onClickItemProduct(ItemPhoneProduct itemPhoneProduct);
    }

    public static class ItemPhoneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context mContext;
        private OnClickProductListener mListener;
        private ImageView mImageView;
        private TextView mTextSale;
        private TextView mTextPrice;
        private TextView mTextProduct;
        //private TextView mTextInfo;
        private TextView mTextNumberRatting;
        private RatingBar mRatingBar;
        private RelativeLayout mRelativeSale;
        private ItemPhoneProduct mItemPhoneProduct;

        ItemPhoneViewHolder(@NonNull View itemView, Context context, OnClickProductListener listener) {
            super(itemView);
            mContext = context;
            mListener = listener;
            mImageView = itemView.findViewById(R.id.image_product);
            mTextSale = itemView.findViewById(R.id.text_sale);
            mTextPrice = itemView.findViewById(R.id.text_price);
            mTextProduct = itemView.findViewById(R.id.text_name_product);
//            mTextInfo = itemView.findViewById(R.id.text_info_product);
            mRelativeSale = itemView.findViewById(R.id.relative_sale);
            mTextNumberRatting = itemView.findViewById(R.id.text_number_rating);
            mRatingBar = itemView.findViewById(R.id.rating_bar);
            itemView.setOnClickListener(this);
        }

        public void bindData(ItemPhoneProduct itemPhoneProduct) {
            if (itemPhoneProduct == null) {
                return;
            }
            mItemPhoneProduct = itemPhoneProduct;
            Glide.with(mContext).load(itemPhoneProduct.getImage()).into(mImageView);
            if (itemPhoneProduct.getDeal() == null || itemPhoneProduct.getDeal().equals("")) {
                mRelativeSale.setVisibility(View.GONE);
            } else {
                mRelativeSale.setVisibility(View.VISIBLE);
            }
            mTextSale.setText(itemPhoneProduct.getDeal());
            mTextPrice.setText(itemPhoneProduct.getPrice());
            mTextProduct.setText(itemPhoneProduct.getTitle());
            if (itemPhoneProduct.getNumberRating().equals("")) {
                mTextNumberRatting.setText("Chưa có đánh giá");
            } else {
                mTextNumberRatting.setText(itemPhoneProduct.getNumberRating());
            }
            mRatingBar.setRating(Float.valueOf(itemPhoneProduct.getRating()));
        }

        @Override
        public void onClick(View view) {
            mListener.onClickItemProduct(mItemPhoneProduct);
        }
    }

    static class ProgressViewHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressBar;

        ProgressViewHolder(View v) {
            super(v);
            mProgressBar = v.findViewById(R.id.progressBar);
        }
    }

    public void addLoadingIndicator() {
        mPhoneProducts.add(mPhoneProducts.size(), null);
        notifyItemInserted(mPhoneProducts.size() - 1);
    }

    public void removeLoadingIndicator() {
        for (int i = 0; i < mPhoneProducts.size(); i++) {
            if (mPhoneProducts.get(i) == null) {
                mPhoneProducts.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, mPhoneProducts.size());
            }
        }
    }

    public void addData(List<ItemPhoneProduct> tracks) {
        mPhoneProducts.addAll(tracks);
        notifyDataSetChanged();
    }

    public void clearData() {
        mPhoneProducts.clear();
        notifyDataSetChanged();
    }
}

