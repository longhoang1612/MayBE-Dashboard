package com.hoanglong.junadminstore.screen.phone.phone_category;


import com.hoanglong.junadminstore.base.BasePresenter;
import com.hoanglong.junadminstore.data.model.category.ItemPhoneCategory;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;

import java.util.List;

class PhoneCategoryContract {
    interface View {
        void onGetDataSuccess(List<ItemPhoneCategory> categories);

        void hideProgressBar();

        void onGetDataError(String error);

        void onGetDataPhoneSuccess(List<ItemPhoneProduct> products);

        void onGetDataPhoneError(String error);

        void hideProgressPhone();
    }

    interface Presenter extends BasePresenter<View> {
        void getCategories(String typeCategory);

        void getPhones(String typeCategory);
    }
}
