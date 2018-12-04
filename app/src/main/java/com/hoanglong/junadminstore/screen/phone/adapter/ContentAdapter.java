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
import com.hoanglong.junadminstore.data.model.phone_product.DetailContent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private List<DetailContent> mContentList;
    private LayoutInflater mInflater;

    public ContentAdapter(List<DetailContent> contentList) {
        mContentList = contentList;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_detail_content, viewGroup, false);
        return new ContentViewHolder(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder contentViewHolder, int i) {
        DetailContent detailContent = mContentList.get(i);
        contentViewHolder.bindData(detailContent);
    }

    @Override
    public int getItemCount() {
        return mContentList != null ? mContentList.size() : 0;
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_content)
        TextView mTextContent;
        @BindView(R.id.image_content)
        ImageView mImageContent;
        private Context mContext;

        ContentViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            mContext = context;
            ButterKnife.bind(this, itemView);
        }

        void bindData(DetailContent detailContent) {
            if (detailContent == null) {
                return;
            }
            mTextContent.setText(detailContent.getTitle());
            if (detailContent.getImage() != null) {
                Glide.with(mContext).load(detailContent.getImage()).into(mImageContent);
            }
        }
    }
}
