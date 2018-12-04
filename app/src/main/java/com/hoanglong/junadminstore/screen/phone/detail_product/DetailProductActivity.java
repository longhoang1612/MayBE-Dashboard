package com.hoanglong.junadminstore.screen.phone.detail_product;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.phone_product.DetailContent;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.data.model.phone_product.ListParameter;
import com.hoanglong.junadminstore.data.model.phone_product.PhoneProduct;
import com.hoanglong.junadminstore.screen.phone.adapter.ContentAdapter;
import com.hoanglong.junadminstore.screen.phone.adapter.ExtraProductAdapter;
import com.hoanglong.junadminstore.screen.phone.adapter.InfoAdapter;
import com.hoanglong.junadminstore.screen.phone.adapter.SaleAdapter;
import com.hoanglong.junadminstore.screen.phone.adapter.SamplePagerAdapter;
import com.hoanglong.junadminstore.service.BaseService;
import com.hoanglong.junadminstore.utils.FragmentTransactionUtils;
import com.hoanglong.junadminstore.utils.customView.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailProductActivity extends AppCompatActivity
        implements View.OnClickListener, SamplePagerAdapter.ClickSliderListener, Listener {

    private static final String TAG = DetailProductActivity.class.getName();
    @BindView(R.id.text_sale)
    TextView mTextSale;
    @BindView(R.id.text_title_product)
    TextView mTextNameProduct;
    @BindView(R.id.text_price_product)
    TextView mTextPrice;
    @BindView(R.id.rating_bar_product)
    RatingBar mRatingBar;
    @BindView(R.id.text_number_rating_product)
    TextView mTextNumRating;
    @BindView(R.id.recycler_sale)
    RecyclerView mRecyclerSale;
    @BindView(R.id.recycler_extra_product)
    RecyclerView mRecyclerExtraProduct;
    @BindView(R.id.recycler_info_product)
    RecyclerView mRecyclerInfoProduct;
    @BindView(R.id.text_title_content)
    TextView mTextTitleContent;
    @BindView(R.id.text_h2)
    TextView mTextH2;
    @BindView(R.id.recycler_des)
    RecyclerView mRecyclerDes;
    @BindView(R.id.viewpager)
    LoopViewPager mViewPager;
    @BindView(R.id.indicator)
    CircleIndicator mIndicator;
    @BindView(R.id.relative_all_content)
    RelativeLayout mRLAllContent;
    @BindView(R.id.text_see_detail)
    TextView textSeeDetail;
    @BindView(R.id.relative_comment)
    RelativeLayout mRelativeComment;
//    @BindView(R.id.ic_shopping)
//    ImageView mImageShopping;
    @BindView(R.id.progress_detail)
    ProgressBar mProgressDetail;
    @BindView(R.id.nest_scroll_detail)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.relative_sale)
    RelativeLayout mRelativeSale;
    @BindView(R.id.ic_back)
    ImageView mImageBack;

    private ItemPhoneProduct itemPhoneProduct;
    private List<DetailContent> mContentListHide;
    private List<ListParameter> mInfoProducts;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);
        mRLAllContent.setOnClickListener(this);
        textSeeDetail.setOnClickListener(this);
        mRelativeComment.setOnClickListener(this);
        mImageBack.setOnClickListener(this);
        mContentListHide = new ArrayList<>();
        mInfoProducts = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString("BUNDLE_ITEM_PRODUCT");
        }
        mNestedScrollView.setVisibility(View.GONE);
        mProgressDetail.setVisibility(View.VISIBLE);
        setData();
    }

    private void setData() {
        final Call<PhoneProduct> productCall = BaseService.getService().getPhoneWithTitle(title);
        productCall.enqueue(new Callback<PhoneProduct>() {
            @Override
            public void onResponse(@NonNull Call<PhoneProduct> call, @NonNull Response<PhoneProduct> response) {
                if (response.body() != null) {
                    mNestedScrollView.setVisibility(View.VISIBLE);
                    mProgressDetail.setVisibility(View.GONE);
                    itemPhoneProduct = response.body().getPhoneProduct().get(0);
                    setupView(itemPhoneProduct);
                    setSlide();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhoneProduct> call, @NonNull Throwable t) {
                mProgressDetail.setVisibility(View.VISIBLE);
                mNestedScrollView.setVisibility(View.GONE);
            }
        });
    }

    private void setSlide() {
        SamplePagerAdapter samplePagerAdapter = new SamplePagerAdapter(
                itemPhoneProduct.getSlider(), this
        );
        mViewPager.setAdapter(samplePagerAdapter);
        mIndicator.setViewPager(mViewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mImageShopping.setVisibility(View.VISIBLE);
        onUpdateCart();
    }

    private void onUpdateCart() {
    }

    private void setupView(ItemPhoneProduct itemPhoneProduct) {
        if (itemPhoneProduct == null) {
            return;
        }
        if (itemPhoneProduct.getDeal() == null || itemPhoneProduct.getDeal().equals("")) {
            mRelativeSale.setVisibility(View.GONE);
        } else {
            mRelativeSale.setVisibility(View.VISIBLE);
            mTextSale.setText(itemPhoneProduct.getDeal());
        }
        mTextNameProduct.setText(itemPhoneProduct.getTitle());
        mTextPrice.setText(itemPhoneProduct.getPrice());
        mRecyclerSale.setAdapter(
                new SaleAdapter(itemPhoneProduct.getListSale()));
        mRecyclerSale.setNestedScrollingEnabled(false);
        mRecyclerExtraProduct.setAdapter(
                new ExtraProductAdapter(itemPhoneProduct.getListExtraProduct()));
        mRecyclerExtraProduct.setNestedScrollingEnabled(false);

        //InFo
        if (itemPhoneProduct.getListParameter().size() > 3) {
            for (int i = 0; i < 3; i++) {
                mInfoProducts.add(itemPhoneProduct.getListParameter().get(i));
            }
        } else {
            mInfoProducts.addAll(itemPhoneProduct.getListParameter());
        }
        mRecyclerInfoProduct.setAdapter(
                new InfoAdapter(mInfoProducts));
        mRecyclerInfoProduct.setNestedScrollingEnabled(false);

        //Content
        mTextTitleContent.setText(itemPhoneProduct.getTitleContent());
        mTextH2.setText(itemPhoneProduct.getTitleH2());
        if (itemPhoneProduct.getDetailContent().size() >= 2) {
            for (int i = 0; i < 2; i++) {
                mContentListHide.add(itemPhoneProduct.getDetailContent().get(i));
            }
        } else {
            mContentListHide.addAll(itemPhoneProduct.getDetailContent());
        }

        mRecyclerDes.setAdapter(
                new ContentAdapter(mContentListHide));
        mRecyclerDes.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative_all_content:
                Intent intent = new Intent(this, ContentDetailActivity.class);
                intent.putExtra(ContentDetailActivity.BUNDLE_TITLE_CONTENT, itemPhoneProduct.getTitleContent());
                intent.putExtra(ContentDetailActivity.BUNDLE_H2_CONTENT, itemPhoneProduct.getTitleH2());
                intent.putParcelableArrayListExtra(ContentDetailActivity.BUNDLE_DETAIL_CONTENT,
                        (ArrayList<? extends Parcelable>) itemPhoneProduct.getDetailContent());
                startActivity(intent);
                break;
            case R.id.text_see_detail:
                Intent intentInfo = new Intent(this, InfoDetailActivity.class);
                intentInfo.putParcelableArrayListExtra(InfoDetailActivity.BUNDLE_INFO,
                        (ArrayList<? extends Parcelable>) itemPhoneProduct.getListParameter());
                startActivity(intentInfo);
                break;
            case R.id.relative_comment:
                FragmentTransactionUtils.addFragment(
                        getSupportFragmentManager(),
                        CommentFragment.newInstance(itemPhoneProduct),
                        R.id.frame_full,
                        CommentFragment.TAG,
                        true,
                        -1, -1);
                break;
            case R.id.ic_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onClickSlider(List<String> sliders, int position) {
        FragmentTransactionUtils.addFragment(
                getSupportFragmentManager(),
                SliderImageFragment.newInstance(sliders, position),
                R.id.frame_full, SliderImageFragment.TAG,
                true, -1, -1
        );
    }

    @Override
    public void onHideButtonCart(int visibility) {
        //mImageShopping.setVisibility(View.GONE);
    }
}
