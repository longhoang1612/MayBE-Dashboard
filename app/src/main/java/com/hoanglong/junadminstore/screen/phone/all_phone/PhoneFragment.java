package com.hoanglong.junadminstore.screen.phone.all_phone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.category.ItemPhoneCategory;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.data.model.phone_product.PhoneProduct;
import com.hoanglong.junadminstore.screen.phone.adapter.PhoneAdapter;
import com.hoanglong.junadminstore.screen.phone.detail_product.DetailProductActivity;
import com.hoanglong.junadminstore.service.BaseService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhoneFragment extends Fragment implements PhoneAdapter.OnClickProductListener {

    public static final String TAG = PhoneFragment.class.getName();
    private static final String BUNDLE_TYPE_PHONE = "BUNDLE_TYPE_PHONE";
    private String typePhone;
    private RecyclerView mRecyclerPhone;
    private TextView mTextResult;
    private ProgressBar mProgressBar;

    public static PhoneFragment newInstance(ItemPhoneCategory itemPhoneCategory) {
        Bundle args = new Bundle();
        PhoneFragment fragment = new PhoneFragment();
        args.putString(BUNDLE_TYPE_PHONE, itemPhoneCategory.getType());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            typePhone = getArguments().getString(BUNDLE_TYPE_PHONE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textType = view.findViewById(R.id.text_type);
        textType.setText(typePhone);
        mRecyclerPhone = view.findViewById(R.id.recycler_phone);
        mTextResult = view.findViewById(R.id.text_result);
        mProgressBar = view.findViewById(R.id.progress_phone);
        mProgressBar.setVisibility(View.VISIBLE);
        setUpData();
    }

    private void setUpData() {
        BaseService.getService().getPhoneWithType(typePhone).enqueue(new Callback<PhoneProduct>() {
            @Override
            public void onResponse(@NonNull Call<PhoneProduct> call, @NonNull Response<PhoneProduct> response) {
                List<ItemPhoneProduct> productList = null;
                if (response.body() != null) {
                    mProgressBar.setVisibility(View.GONE);
                    productList = response.body().getPhoneProduct();
                    setUpRecyclerView(productList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhoneProduct> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setUpRecyclerView(List<ItemPhoneProduct> productList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Tìm thấy ").append(productList.size()).append(" sản phẩm");
        mTextResult.setText(stringBuilder.toString());
        PhoneAdapter phoneAdapter = new PhoneAdapter(productList, this);
        mRecyclerPhone.setAdapter(phoneAdapter);
    }

    @Override
    public void onClickItemProduct(ItemPhoneProduct itemPhoneProduct) {
        Intent intent = new Intent(getActivity(), DetailProductActivity.class);
        intent.putExtra("BUNDLE_ITEM_PRODUCT", itemPhoneProduct.getTitle());
        startActivity(intent);
    }
}
