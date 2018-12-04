package com.hoanglong.junadminstore.screen.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;

import java.util.List;


public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ItemPhoneViewHolder> {

    private List<ItemPhoneProduct> mPhoneProducts;
    private LayoutInflater mLayoutInflater;
    private OnClickProductListener mOnClickProductListener;

    public PhoneAdapter(List<ItemPhoneProduct> phoneProducts, OnClickProductListener onClickProductListener) {
        mPhoneProducts = phoneProducts;
        mOnClickProductListener = onClickProductListener;
    }

    @NonNull
    @Override
    public ItemPhoneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mLayoutInflater.inflate(R.layout.item_product, viewGroup, false);
        return new ItemPhoneViewHolder(view, viewGroup.getContext(), mOnClickProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPhoneViewHolder itemPhoneViewHolder, int i) {
        ItemPhoneProduct itemPhoneProduct = mPhoneProducts.get(i);
        itemPhoneViewHolder.bindData(itemPhoneProduct);
    }

    @Override
    public int getItemCount() {
        return mPhoneProducts != null ? mPhoneProducts.size() : 0;
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

        public ItemPhoneViewHolder(@NonNull View itemView, Context context, OnClickProductListener listener) {
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
            if (itemPhoneProduct.getDeal().equals("")) {
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
}
