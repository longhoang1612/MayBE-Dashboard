package com.hoanglong.junadminstore.screen.phone.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

public class SamplePagerAdapter extends PagerAdapter {

    private List<String> sliders;
    private ClickSliderListener mClickSliderListener;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;

    public SamplePagerAdapter(List<String> sliders, ClickSliderListener clickSliderListener) {
        this.sliders = sliders;
        this.mClickSliderListener = clickSliderListener;
    }

    @Override
    public int getCount() {
        return sliders != null ? sliders.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup view, int position, @NonNull Object object) {
        view.removeView((View) object);
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup view, final int position) {
        PhotoView imageView = new PhotoView(view.getContext());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickSliderListener.onClickSlider(sliders, position);
            }
        });
        String image = sliders.get(position);
        if (!image.contains("https:")) {
            image = "https:" + image;
        }
        Glide.with(view.getContext()).load(image).into(imageView);
        view.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return imageView;
    }

    public interface ClickSliderListener {
        void onClickSlider(List<String> sliders, int position);
    }

}