package com.hoanglong.junadminstore.screen.dashboard;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
import com.hoanglong.junadminstore.screen.dashboard.adapter.CategoriesAdapter;
import com.hoanglong.junadminstore.screen.dashboard.adapter.PhoneAdapter;
import com.hoanglong.junadminstore.screen.phone.detail_product.DetailProductActivity;
import com.hoanglong.junadminstore.screen.phone.phone_category.PhoneCategoryFragment;
import com.hoanglong.junadminstore.screen.search.SearchActivity;
import com.hoanglong.junadminstore.utils.EndlessRecyclerViewScrollListener;
import com.hoanglong.junadminstore.utils.Utils;

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
    @BindView(R.id.group_dashboard)
    Group mLinearDashboard;
    @BindView(R.id.progress_dashboard)
    ProgressBar mProgressDashboard;
    @BindView(R.id.relative_search)
    RelativeLayout mRelativeSearch;
    private static final int LIMIT = 4;
    private int mPage = LIMIT;
    private boolean mIsLoading = false;
    private List<ItemPhoneProduct> mItemPhoneProducts;
    private PhoneAdapter mPhoneAdapter;
    private DashBoardPresenter mDashBoardPresenter;

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_dash_board;
    }

    @Override
    protected void initComponent(View view) {
        ButterKnife.bind(this, view);
        mFabAddNewItem.setOnClickListener(this);
        mRelativeSearch.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        HomeDataSource homeDataSource = HomeDataSource.getInstance();
        HomeRepository homeRepository = HomeRepository.getInstance(homeDataSource);

        CategoryLocalDataSource localDataSource = CategoryLocalDataSource.getInstance();
        CategoryRepository categoryRepository = CategoryRepository.getInstance(localDataSource);

        mDashBoardPresenter = new DashBoardPresenter(categoryRepository, homeRepository);
        mDashBoardPresenter.setView(this);
        mDashBoardPresenter.getCategoryHome();
        initRecyclerView();
        loadData();
    }

    private void initRecyclerView() {
        mItemPhoneProducts = new ArrayList<>();
        mPhoneAdapter = new PhoneAdapter(mItemPhoneProducts, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == mItemPhoneProducts.size() - 1) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        mRecyclerAllItem.setHasFixedSize(true);
        mRecyclerAllItem.setLayoutManager(gridLayoutManager);
        mRecyclerAllItem.setAdapter(mPhoneAdapter);

        mRecyclerAllItem.addOnScrollListener(new EndlessRecyclerViewScrollListener(
                gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (mIsLoading) {
                    return;
                }
                mPage += LIMIT;
                loadData();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_new:
                Intent intent = new Intent(getActivity(), AddNewActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_search:
                Intent intent1 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onAllItemsSuccess(PhoneProduct phoneProduct) {
        if (phoneProduct == null) {
            return;
        }

        mPhoneAdapter.removeLoadingIndicator();
        mPhoneAdapter.clearData();

        List<ItemPhoneProduct> itemPhoneProducts = new ArrayList<>();
        if (mPage > phoneProduct.getPhoneProduct().size()) {
            Toast.makeText(getContext(), "Đã hết sản phẩm", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < mPage; i++) {
                itemPhoneProducts.add(phoneProduct.getPhoneProduct().get(i));
            }
        }

        mPhoneAdapter.addData(itemPhoneProducts);
        mIsLoading = false;
    }

    private void loadData() {
        if (mIsLoading) {
            return;
        }
        mPhoneAdapter.addLoadingIndicator();
        mDashBoardPresenter.getAllItems();
        mIsLoading = true;
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
        Intent intent = new Intent(getActivity(), PhoneCategoryFragment.class);
        intent.putExtra(PhoneCategoryFragment.BUNDLE_CATEGORY, category);
        startActivity(intent);
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
                        mPhoneAdapter.notifyDataSetChanged();
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
}
