package com.hoanglong.junadminstore.screen.dashboard;


import com.hoanglong.junadminstore.base.BasePresenter;
import com.hoanglong.junadminstore.data.model.category.Category;
import com.hoanglong.junadminstore.data.model.phone_product.PhoneProduct;

import java.util.List;

public class DashBoardContract {
    interface View {
        void onAllItemsSuccess(PhoneProduct phoneProduct);

        void hideProgressBar();

        void onGetDataError(String error);

        void onGetCategoryHomeSuccess(List<Category> categories);
    }

    interface Presenter extends BasePresenter<View> {
        void getAllItems();

        void getCategoryHome();
    }
}
