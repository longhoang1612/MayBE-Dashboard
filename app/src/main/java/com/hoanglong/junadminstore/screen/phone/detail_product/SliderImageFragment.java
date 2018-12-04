package com.hoanglong.junadminstore.screen.phone.detail_product;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.base.BaseFragment;
import com.hoanglong.junadminstore.screen.phone.adapter.SamplePagerAdapter;
import com.hoanglong.junadminstore.screen.phone.adapter.SliderShowAdapter;
import com.hoanglong.junadminstore.utils.customView.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SliderImageFragment extends BaseFragment
        implements View.OnClickListener,
        SliderShowAdapter.ClickSliderListener,
        SamplePagerAdapter.ClickSliderListener {

    public static final String TAG = SliderImageFragment.class.getName();
    public static final String BUNDLE_STRING_IMAGE = "BUNDLE_STRING_IMAGE";
    public static final String BUNDLE_INT_POSITION = "BUNDLE_INT_POSITION";

    @BindView(R.id.recycler_image)
    RecyclerView mRecyclerImage;
    @BindView(R.id.viewpager)
    LoopViewPager viewPager;
    @BindView(R.id.ic_close)
    ImageView mImageClose;

    private int position;
    private List<String> mListImage;
    private Listener hideCartListener;

    public static SliderImageFragment newInstance(List<String> sliders, int position) {

        Bundle bundle = new Bundle();
        bundle.putStringArrayList(BUNDLE_STRING_IMAGE, (ArrayList<String>) sliders);
        bundle.putInt(BUNDLE_INT_POSITION, position);
        SliderImageFragment fragment = new SliderImageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hideCartListener = (Listener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mListImage = bundle.getStringArrayList(BUNDLE_STRING_IMAGE);
        position = bundle.getInt(BUNDLE_INT_POSITION);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_slider_image;
    }

    @Override
    protected void initComponent(View view) {
        ButterKnife.bind(this, view);
        hideCartListener.onHideButtonCart(View.GONE);
        mImageClose.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        SamplePagerAdapter samplePagerAdapter =
                new SamplePagerAdapter(mListImage, this);
        viewPager.setAdapter(samplePagerAdapter);
        viewPager.setCurrentItem(position);
        final SliderShowAdapter showAdapter = new SliderShowAdapter(mListImage, this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                showAdapter.setRow_index(i);
                showAdapter.notifyDataSetChanged();
                mRecyclerImage.scrollToPosition(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        showAdapter.setRow_index(position);
        mRecyclerImage.setAdapter(showAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_close:
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
                break;
        }
    }

    @Override
    public void onClickSlider(List<String> sliders, int position) {
    }

    @Override
    public void onClickSliderWithPosition(List<String> sliders, int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        hideCartListener.onHideButtonCart(View.VISIBLE);
    }
}
