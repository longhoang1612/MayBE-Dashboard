package com.hoanglong.junadminstore.screen.phone.all_phone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.base.BaseFragment;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.screen.phone.adapter.AllPhoneAdapter;
import com.hoanglong.junadminstore.screen.phone.detail_product.DetailProductActivity;
import com.hoanglong.junadminstore.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllPhoneFragment extends BaseFragment implements AllPhoneAdapter.OnClickProductListener,
        View.OnClickListener {

    public static final String TAG = AllPhoneFragment.class.getName();
    public static final String BUNDLE_ALL_PRODUCT = "BUNDLE_ALL_PRODUCT";
    private List<ItemPhoneProduct> mPhoneProducts;
    @BindView(R.id.recycler_phone)
    RecyclerView mRecyclerPhone;
    @BindView(R.id.text_result)
    TextView mTextResult;
    @BindView(R.id.progress_phone)
    ProgressBar mProgressBar;
    @BindView(R.id.ic_grid)
    ImageView mImageGrid;
    @BindView(R.id.ic_sort)
    ImageView mImageSort;
    public static boolean mIsViewWithCatalog = false;
    private AllPhoneAdapter mAllPhoneAdapter;

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
            bundle.clear();
        }
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_all_phone;
    }

    @Override
    protected void initComponent(View view) {
        ButterKnife.bind(this, view);
        if (mIsViewWithCatalog) {
            mImageGrid.setImageResource(R.drawable.ic_format_list_bulleted_black_24dp);
        } else {
            mImageGrid.setImageResource(R.drawable.ic_view_module_black_24dp);
        }
        mImageGrid.setOnClickListener(this);
        mImageSort.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        mProgressBar.setVisibility(View.GONE);
        mTextResult.setText(String.format(getString(R.string.finded_product), mPhoneProducts.size()));
        mAllPhoneAdapter = new AllPhoneAdapter(mPhoneProducts, this);
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
        if(getContext()==null) return;
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
