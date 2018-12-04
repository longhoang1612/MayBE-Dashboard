package com.hoanglong.junadminstore.screen.phone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hoanglong.junadminstore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SliderShowAdapter extends RecyclerView.Adapter<SliderShowAdapter.SlideViewHolder> {

    private List<String> mSliders;
    private LayoutInflater mInflater;
    private ClickSliderListener mClickSliderListener;
    private int row_index;

    public SliderShowAdapter(List<String> sliders,
                             ClickSliderListener clickSliderListener) {
        mSliders = sliders;
        mClickSliderListener = clickSliderListener;
    }

    public void setRow_index(int row_index) {
        this.row_index = row_index;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_image_show, viewGroup, false);
        return new SlideViewHolder(view, viewGroup.getContext(), mClickSliderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final SlideViewHolder slideViewHolder,
                                 final int position) {
        slideViewHolder.bindData(mSliders, position);
        slideViewHolder.mFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewHolder.mClickSliderListener
                        .onClickSliderWithPosition(mSliders, position);
                row_index = position;
                notifyDataSetChanged();
            }
        });

        if (row_index == position) {
            slideViewHolder.mFrameLayout.setBackgroundResource(R.drawable.custom_button3);
        } else {
            slideViewHolder.mFrameLayout.setBackgroundResource(R.drawable.custom_button4);
        }
    }

    @Override
    public int getItemCount() {
        return mSliders != null ? mSliders.size() : 0;
    }

    public interface ClickSliderListener {
        void onClickSliderWithPosition(List<String> sliders, int position);
    }

    public class SlideViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_show)
        ImageView mImageShow;
        @BindView(R.id.frame)
        FrameLayout mFrameLayout;
        private Context mContext;
        private ClickSliderListener mClickSliderListener;

        private SlideViewHolder(@NonNull View itemView, Context context,
                                ClickSliderListener clickSliderListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = context;
            mClickSliderListener = clickSliderListener;
        }

        public void bindData(List<String> sliders, int position) {
            mSliders = sliders;
            if (sliders == null) {
                return;
            }
            String image = sliders.get(position);
            if (!image.contains("https:")) {
                image = "https:" + image;
            }
            Glide.with(mContext).load(image).into(mImageShow);
        }

    }
}
