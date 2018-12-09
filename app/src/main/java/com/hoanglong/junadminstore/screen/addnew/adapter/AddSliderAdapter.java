package com.hoanglong.junadminstore.screen.addnew.adapter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hoanglong.junadminstore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddSliderAdapter extends RecyclerView.Adapter<AddSliderAdapter.SliderViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Bitmap> mBitmaps;

    public AddSliderAdapter(List<Bitmap> bitmaps) {
        mBitmaps = bitmaps;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mLayoutInflater.inflate(R.layout.item_add_slider, viewGroup, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder sliderViewHolder, int i) {
        Bitmap bitmap = mBitmaps.get(i);
        sliderViewHolder.bindData(bitmap);
    }

    @Override
    public int getItemCount() {
        return mBitmaps != null ? mBitmaps.size() : 0;
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_slider)
        ImageView mImageSlider;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Bitmap bitmap) {
            if (bitmap == null) {
                return;
            }
            mImageSlider.setImageBitmap(bitmap);
        }
    }
}
