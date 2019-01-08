package com.hoanglong.junadminstore.screen.phone.all_phone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.category.ItemPhoneCategory;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.data.model.phone_product.PhoneProduct;
import com.hoanglong.junadminstore.screen.phone.adapter.DetailCategoryAdapter;
import com.hoanglong.junadminstore.screen.phone.detail_product.DetailProductActivity;
import com.hoanglong.junadminstore.service.BaseService;
import com.hoanglong.junadminstore.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhoneFragment extends Fragment implements DetailCategoryAdapter.OnClickProductListener,
        View.OnClickListener {

    public static final String TAG = PhoneFragment.class.getName();
    private static final String BUNDLE_TYPE_PHONE = "BUNDLE_TYPE_PHONE";
    private String typePhone;
    private RecyclerView mRecyclerPhone;
    private TextView mTextResult;
    private ProgressBar mProgressBar;
    @BindView(R.id.ic_sort)
    ImageView mImageSort;
    @BindView(R.id.ic_grid)
    ImageView mImageGrid;
    public static boolean mIsViewWithCatalog = false;
    private DetailCategoryAdapter mAllPhoneAdapter;
    private List<ItemPhoneProduct> mPhoneProducts;

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
            getArguments().clear();
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
        ButterKnife.bind(this, view);
        TextView textType = view.findViewById(R.id.text_type);
        textType.setText(typePhone);
        mRecyclerPhone = view.findViewById(R.id.recycler_phone);
        mTextResult = view.findViewById(R.id.text_result);
        mProgressBar = view.findViewById(R.id.progress_phone);
        mProgressBar.setVisibility(View.VISIBLE);
        mImageGrid.setOnClickListener(this);
        mImageSort.setOnClickListener(this);
        if (mIsViewWithCatalog) {
            mImageGrid.setImageResource(R.drawable.ic_format_list_bulleted_black_24dp);
        } else {
            mImageGrid.setImageResource(R.drawable.ic_view_module_black_24dp);
        }
        setUpData();
    }

    private void setUpData() {
        BaseService.getService().getPhoneWithType(typePhone).enqueue(new Callback<PhoneProduct>() {
            @Override
            public void onResponse(@NonNull Call<PhoneProduct> call, @NonNull Response<PhoneProduct> response) {
                if (response.body() != null) {
                    mProgressBar.setVisibility(View.GONE);
                    mPhoneProducts = new ArrayList<>();
                    mPhoneProducts = response.body().getPhoneProduct();
                    setUpRecyclerView(mPhoneProducts);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhoneProduct> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setUpRecyclerView(List<ItemPhoneProduct> productList) {
        mTextResult.setText(String.format(getString(R.string.finded_product), productList.size()));
        mAllPhoneAdapter = new DetailCategoryAdapter(mPhoneProducts, this);
        mRecyclerPhone.setLayoutManager(mIsViewWithCatalog ? new LinearLayoutManager(getContext()) : new GridLayoutManager(getContext(), 2));
        mRecyclerPhone.setAdapter(mAllPhoneAdapter);
    }

    @Override
    public void onClickItemProduct(ItemPhoneProduct itemPhoneProduct) {
        Intent intent = new Intent(getActivity(), DetailProductActivity.class);
        intent.putExtra("BUNDLE_ITEM_PRODUCT", itemPhoneProduct.getTitle());
        startActivity(intent);
    }

    @Override
    public void deleteProduct(final ItemPhoneProduct itemPhoneProduct) {
        if (getContext() == null) return;
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa sản phẩm")
                .setMessage("Bạn chắc chắn muốn xóa sản phẩm này?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.deleteProduct(getContext(), itemPhoneProduct);
                        mAllPhoneAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_sort:
                final String[] listItems = getResources().getStringArray(R.array.shopping_item);
                if (getContext() == null) return;
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                mBuilder.setTitle("Sắp xếp");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (listItems[i]) {
                            case "Sắp xếp theo tên":
                                Collections.sort(mPhoneProducts, new Comparator<ItemPhoneProduct>() {
                                    @Override
                                    public int compare(ItemPhoneProduct one, ItemPhoneProduct other) {
                                        return one.getTitle().compareToIgnoreCase(other.getTitle());
                                    }
                                });
                                mAllPhoneAdapter.notifyDataSetChanged();
                                break;
                            case "Sắp xếp theo giá":
                                Collections.sort(mPhoneProducts, new Comparator<ItemPhoneProduct>() {
                                    @Override
                                    public int compare(ItemPhoneProduct one, ItemPhoneProduct other) {
                                        return one.getPrice().compareToIgnoreCase(other.getPrice());
                                    }
                                });
                                mAllPhoneAdapter.notifyDataSetChanged();
                                break;
                            case "Sắp xếp theo đánh giá":
                                Collections.sort(mPhoneProducts, new Comparator<ItemPhoneProduct>() {
                                    @Override
                                    public int compare(ItemPhoneProduct one, ItemPhoneProduct other) {
                                        return String.valueOf(one.getRating()).compareToIgnoreCase(String.valueOf(other.getRating()));
                                    }
                                });
                                mAllPhoneAdapter.notifyDataSetChanged();
                                break;
                        }
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
                break;
            case R.id.ic_grid:
                if (mIsViewWithCatalog) {
                    mImageGrid.setImageResource(R.drawable.ic_view_module_black_24dp);
                } else {
                    mImageGrid.setImageResource(R.drawable.ic_format_list_bulleted_black_24dp);
                }
                mIsViewWithCatalog = !mIsViewWithCatalog;
                mRecyclerPhone.setLayoutManager(mIsViewWithCatalog ? new LinearLayoutManager(getContext()) : new GridLayoutManager(getContext(), 2));
                mRecyclerPhone.setAdapter(mAllPhoneAdapter);
                break;
        }
    }
}
