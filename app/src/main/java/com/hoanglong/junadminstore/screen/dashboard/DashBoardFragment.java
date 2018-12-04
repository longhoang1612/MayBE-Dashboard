package com.hoanglong.junadminstore.screen.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.base.BaseFragment;
import com.hoanglong.junadminstore.data.model.category.Category;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.data.model.phone_product.PhoneProduct;
import com.hoanglong.junadminstore.data.repository.CategoryRepository;
import com.hoanglong.junadminstore.data.repository.HomeRepository;
import com.hoanglong.junadminstore.data.source.local.CategoryLocalDataSource;
import com.hoanglong.junadminstore.data.source.remote.HomeDataSource;
import com.hoanglong.junadminstore.screen.addnew.AddNewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoardFragment extends BaseFragment implements View.OnClickListener,
        DashBoardContract.View,
        CategoriesAdapter.OnClickCategoryItem,
        PhoneAdapter.OnClickProductListener {

    public static final String TAG = DashBoardFragment.class.getName();
    @BindView(R.id.fab_add_new)
    FloatingActionButton mFabAddNewItem;
    @BindView(R.id.recycler_category)
    RecyclerView mRecyclerCategory;
    @BindView(R.id.recycler_all_item)
    RecyclerView mRecyclerAllItem;
    @BindView(R.id.linear_dashboard)
    LinearLayout mLinearDashboard;
    @BindView(R.id.progress_dashboard)
    ProgressBar mProgressDashboard;

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_dash_board;
    }

    @Override
    protected void initComponent(View view) {
        ButterKnife.bind(this, view);
        mFabAddNewItem.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        HomeDataSource homeDataSource = HomeDataSource.getInstance();
        HomeRepository homeRepository = HomeRepository.getInstance(homeDataSource);

        CategoryLocalDataSource localDataSource = CategoryLocalDataSource.getInstance();
        CategoryRepository categoryRepository = CategoryRepository.getInstance(localDataSource);

        DashBoardPresenter dashBoardPresenter = new DashBoardPresenter(categoryRepository, homeRepository);
        dashBoardPresenter.setView(this);
        dashBoardPresenter.getCategoryHome();
        dashBoardPresenter.getAllItems();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_new:
                Intent intent = new Intent(getActivity(), AddNewActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onGetAllItem(PhoneProduct phoneProduct) {
        List<ItemPhoneProduct> itemPhoneProducts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            itemPhoneProducts.add(phoneProduct.getPhoneProduct().get(i));
        }
        mRecyclerAllItem.setAdapter(new PhoneAdapter(itemPhoneProducts, this));
    }

    @Override
    public void hideProgressBar() {
        mProgressDashboard.setVisibility(View.GONE);
        mLinearDashboard.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetDataError(String error) {
        mProgressDashboard.setVisibility(View.VISIBLE);
        mLinearDashboard.setVisibility(View.GONE);
    }

    @Override
    public void onGetCategoryHomeSuccess(List<Category> categories) {
        mRecyclerCategory.setAdapter(new CategoriesAdapter(categories, this));
    }

    @Override
    public void onClickItem(Category category) {

    }

    @Override
    public void onClickItemProduct(ItemPhoneProduct itemPhoneProduct) {

    }
}
