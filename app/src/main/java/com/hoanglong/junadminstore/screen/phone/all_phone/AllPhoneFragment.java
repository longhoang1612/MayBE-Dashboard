package com.hoanglong.junadminstore.screen.phone.all_phone;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.base.BaseFragment;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.screen.phone.adapter.PhoneAdapter;
import com.hoanglong.junadminstore.screen.phone.detail_product.DetailProductActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllPhoneFragment extends BaseFragment implements PhoneAdapter.OnClickProductListener{

    public static final String TAG = AllPhoneFragment.class.getName();
    public static final String BUNDLE_ALL_PRODUCT = "BUNDLE_ALL_PRODUCT";
    private List<ItemPhoneProduct> mPhoneProducts;
    @BindView(R.id.recycler_phone)
    RecyclerView mRecyclerPhone;
    @BindView(R.id.text_result)
    TextView mTextResult;
    @BindView(R.id.progress_phone)
    ProgressBar mProgressBar;

    public static AllPhoneFragment newInstance(List<ItemPhoneProduct> phoneProducts) {

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BUNDLE_ALL_PRODUCT,
                (ArrayList<? extends Parcelable>) phoneProducts);
        AllPhoneFragment fragment = new AllPhoneFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPhoneProducts = bundle.getParcelableArrayList(BUNDLE_ALL_PRODUCT);
        }
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_all_phone;
    }

    @Override
    protected void initComponent(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        mProgressBar.setVisibility(View.GONE);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Tìm thấy ").append(mPhoneProducts.size()).append(" sản phẩm");
        mTextResult.setText(stringBuilder.toString());
        PhoneAdapter phoneAdapter = new PhoneAdapter(mPhoneProducts, this);
        mRecyclerPhone.setAdapter(phoneAdapter);
    }

    @Override
    public void onClickItemProduct(ItemPhoneProduct itemPhoneProduct) {
        Intent intent = new Intent(getActivity(), DetailProductActivity.class);
        intent.putExtra("BUNDLE_ITEM_PRODUCT", itemPhoneProduct.getTitle());
        startActivity(intent);
    }
}
