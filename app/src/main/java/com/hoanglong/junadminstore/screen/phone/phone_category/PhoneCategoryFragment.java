package com.hoanglong.junadminstore.screen.phone.phone_category;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.base.BaseActivity;
import com.hoanglong.junadminstore.data.model.category.Category;
import com.hoanglong.junadminstore.data.model.category.ItemPhoneCategory;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.data.repository.PhoneRepository;
import com.hoanglong.junadminstore.data.source.remote.PhoneDataSource;
import com.hoanglong.junadminstore.screen.phone.adapter.AccessoriesCategoryAdapter;
import com.hoanglong.junadminstore.screen.phone.adapter.PhoneAdapter;
import com.hoanglong.junadminstore.screen.phone.adapter.PhoneCategoryAdapter;
import com.hoanglong.junadminstore.screen.phone.all_phone.AllPhoneFragment;
import com.hoanglong.junadminstore.screen.phone.all_phone.PhoneFragment;
import com.hoanglong.junadminstore.screen.phone.detail_product.DetailProductActivity;
import com.hoanglong.junadminstore.utils.Constant;
import com.hoanglong.junadminstore.utils.FragmentTransactionUtils;
import com.hoanglong.junadminstore.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhoneCategoryFragment extends BaseActivity implements
        PhoneCategoryContract.View,
        PhoneAdapter.OnClickProductListener,
        PhoneCategoryAdapter.OnClickPhoneCategoryListener,
        View.OnClickListener, AccessoriesCategoryAdapter.OnClickPhoneCategoryListener {

    public static final String TAG = PhoneCategoryFragment.class.getName();
    public static final String BUNDLE_CATEGORY = "BUNDLE_CATEGORY";

    @BindView(R.id.recycler_category_phone)
    RecyclerView mRecyclerCategoryPhone;
    @BindView(R.id.recycler_phone_noibat)
    RecyclerView mRecyclerHighlight;
    @BindView(R.id.progress_phone_category)
    ProgressBar mProgressPhoneCategory;
    @BindView(R.id.progress_phone_noibat)
    ProgressBar mProgressHighlight;
    @BindView(R.id.button_see_more)
    Button mButtonSeeMore;
    private PhoneCategoryPresenter mPresenter;
    private Category mCategory;
    private List<ItemPhoneProduct> mPhoneProducts;
    private PhoneAdapter phoneAdapter;

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_phone_category;
    }

    @Override
    protected void initComponent() {
        ButterKnife.bind(this);
        mCategory = getIntent().getParcelableExtra(BUNDLE_CATEGORY);
        mProgressHighlight.setVisibility(View.VISIBLE);
        mProgressPhoneCategory.setVisibility(View.VISIBLE);
        mButtonSeeMore.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        mPhoneProducts = new ArrayList<>();
        PhoneDataSource dataSource = PhoneDataSource.getInstance();
        PhoneRepository phoneRepository = PhoneRepository.getInstance(dataSource);
        mPresenter = new PhoneCategoryPresenter(phoneRepository);
        mPresenter.setView(this);
        loadDataCategory();
        loadDataHighLight();
    }

    private void loadDataHighLight() {
        mPresenter.getPhones(mCategory.getType());
    }

    private void loadDataCategory() {
        mPresenter.getCategories(mCategory.getType());
    }

    private void setUpRecyclerProduct(List<ItemPhoneProduct> itemPhoneProducts) {
        phoneAdapter = new PhoneAdapter(itemPhoneProducts, this);
        mRecyclerHighlight.setAdapter(phoneAdapter);
    }

    private void setUpRecyclerView(List<ItemPhoneCategory> phoneCategoryList) {
        if (mCategory.getType().equals(Constant.Category.accessories_type)) {
            AccessoriesCategoryAdapter accessoriesCategoryAdapter =
                    new AccessoriesCategoryAdapter(phoneCategoryList, this);
            mRecyclerCategoryPhone.setAdapter(accessoriesCategoryAdapter);
        } else {
            PhoneCategoryAdapter phoneCategoryAdapter =
                    new PhoneCategoryAdapter(phoneCategoryList, this);
            mRecyclerCategoryPhone.setAdapter(phoneCategoryAdapter);
        }
    }

    @Override
    public void onClickItemProduct(ItemPhoneProduct itemPhoneProduct) {
        Intent intent = new Intent(PhoneCategoryFragment.this, DetailProductActivity.class);
        intent.putExtra("BUNDLE_ITEM_PRODUCT", itemPhoneProduct.getTitle());
        startActivity(intent);
    }

    @Override
    public void deleteProduct(final ItemPhoneProduct itemPhoneProduct) {
        if (getApplicationContext() == null) return;
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Xóa sản phẩm")
                .setMessage("Bạn chắc chắn muốn xóa sản phẩm này?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.deleteProduct(getApplicationContext(), itemPhoneProduct);
                        phoneAdapter.notifyDataSetChanged();
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
    public void onClickItem(ItemPhoneCategory phoneCategory) {
        if (getSupportFragmentManager() != null) {
            FragmentTransactionUtils.addFragment(
                    getSupportFragmentManager(),
                    PhoneFragment.newInstance(phoneCategory),
                    R.id.frame_detail,
                    PhoneFragment.TAG, true, -1, -1);
        }
    }

    @Override
    public void onGetDataSuccess(List<ItemPhoneCategory> categories) {
        if (categories == null) {
            return;
        }
        setUpRecyclerView(categories);
    }

    @Override
    public void hideProgressBar() {
        mProgressPhoneCategory.setVisibility(View.GONE);
    }

    @Override
    public void onGetDataError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataPhoneSuccess(List<ItemPhoneProduct> products) {
        if (products == null) {
            return;
        }
        mPhoneProducts = products;
        List<ItemPhoneProduct> highlights = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            highlights.add(products.get(i));
        }
        setUpRecyclerProduct(highlights);
    }

    @Override
    public void onGetDataPhoneError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressPhone() {
        mProgressHighlight.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_see_more:
                if (getSupportFragmentManager() != null) {
                    FragmentTransactionUtils.addFragment(
                            getSupportFragmentManager(),
                            AllPhoneFragment.newInstance(mPhoneProducts),
                            R.id.frame_detail,
                            PhoneFragment.TAG, true, -1, -1);
                }
                break;
        }
    }
}
