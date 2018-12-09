package com.hoanglong.junadminstore.screen.addnew.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.phone_product.Parameter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddInfoAdapter extends RecyclerView.Adapter<AddInfoAdapter.AddInfoViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Parameter> mParameters;

    public AddInfoAdapter(List<Parameter> parameters) {
        mParameters = parameters;
    }

    @NonNull
    @Override
    public AddInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mLayoutInflater.inflate(R.layout.item_add_info_product, viewGroup, false);
        return new AddInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddInfoViewHolder addInfoViewHolder, int i) {
        Parameter parameter = mParameters.get(i);
        addInfoViewHolder.bindData(parameter, i);
    }

    @Override
    public int getItemCount() {
        return mParameters != null ? mParameters.size() : 0;
    }

    public class AddInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.text_title)
        TextView mTextTitle;
        @BindView(R.id.text_info)
        TextView mTextInfo;
        @BindView(R.id.image_delete)
        ImageView mImageDelete;
        private int mPosition;

        AddInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mImageDelete.setOnClickListener(this);
        }

        public void bindData(Parameter parameter, int position) {
            mPosition = position;
            if (parameter == null) {
                return;
            }
            mTextTitle.setText(parameter.getTitlePara());
            mTextInfo.setText(parameter.getContentPara());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_delete:
                    mParameters.remove(mPosition);
                    notifyItemRemoved(mPosition);
                    notifyItemRangeChanged(mPosition, mParameters.size());
                    notifyDataSetChanged();
                    break;
            }
        }
    }
}
