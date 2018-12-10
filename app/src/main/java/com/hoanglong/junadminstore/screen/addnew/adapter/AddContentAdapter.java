package com.hoanglong.junadminstore.screen.addnew.adapter;

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

public class AddContentAdapter extends RecyclerView.Adapter<AddContentAdapter.ContentViewHolder> {

    private List<DetailContent> mContentList;
    private LayoutInflater mInflater;

    public AddContentAdapter(List<DetailContent> contentList) {
        mContentList = contentList;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_add_detail_content, viewGroup, false);
        return new ContentViewHolder(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder contentViewHolder, int i) {
        DetailContent detailContent = mContentList.get(i);
        contentViewHolder.bindData(detailContent,i);
    }

    @Override
    public int getItemCount() {
        return mContentList != null ? mContentList.size() : 0;
    }

    class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.text_content)
        TextView mTextContent;
        @BindView(R.id.image_content)
        ImageView mImageContent;
        @BindView(R.id.ic_delete)
        ImageView mImageDelete;
        private Context mContext;
        private int mPosition;

        ContentViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            mContext = context;
            ButterKnife.bind(this, itemView);
            mImageDelete.setOnClickListener(this);
        }

        void bindData(DetailContent detailContent,int position) {
            if (detailContent == null) {
                return;
            }
            mPosition = position;
            mTextContent.setText(detailContent.getTitle());
            if (detailContent.getImage() != null) {
                Glide.with(mContext).load(detailContent.getImage()).into(mImageContent);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ic_delete:
                    mContentList.remove(mPosition);
                    notifyItemRemoved(mPosition);
                    notifyItemRangeChanged(mPosition, mContentList.size());
                    notifyDataSetChanged();
                    break;
            }
        }
    }
}
