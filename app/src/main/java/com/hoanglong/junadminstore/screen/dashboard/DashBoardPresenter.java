package com.hoanglong.junadminstore.screen.dashboard;

import com.hoanglong.junadminstore.data.model.category.Category;
import com.hoanglong.junadminstore.data.model.phone_product.PhoneProduct;
import com.hoanglong.junadminstore.data.repository.CategoryRepository;
import com.hoanglong.junadminstore.data.repository.HomeRepository;
import com.hoanglong.junadminstore.data.source.CallBack;

import java.util.List;

public class DashBoardPresenter implements DashBoardContract.Presenter {

    private DashBoardContract.View mView;
    private CategoryRepository mCategoryRepository;
    private HomeRepository mHomeRepository;

    DashBoardPresenter(CategoryRepository categoryRepository, HomeRepository homeRepository) {
        mCategoryRepository = categoryRepository;
        mHomeRepository = homeRepository;
    }

    @Override
    public void getAllItems() {
        mHomeRepository.getAllItems(new CallBack<PhoneProduct>() {
            @Override
            public void getDataSuccess(PhoneProduct data) {
                mView.hideProgressBar();
                mView.onGetAllItem(data);
            }

            @Override
            public void getDataError(String error) {
                mView.onGetDataError(error);
            }
        });
    }

    @Override
    public void getCategoryHome() {
        mCategoryRepository.getCategory(new CallBack<List<Category>>() {
            @Override
            public void getDataSuccess(List<Category> data) {
                mView.onGetCategoryHomeSuccess(data);
            }

            @Override
            public void getDataError(String error) {
            }
        });
    }

    @Override
    public void setView(DashBoardContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
