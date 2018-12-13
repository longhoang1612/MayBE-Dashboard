package com.hoanglong.junadminstore;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanglong.junadminstore.base.BaseFragment;
import com.hoanglong.junadminstore.data.model.cart.CartItem;
import com.hoanglong.junadminstore.data.model.order.Order;
import com.hoanglong.junadminstore.screen.payment.adapter.CartConfirmAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailOrderFragment extends BaseFragment implements CartConfirmAdapter.OnUpdatePrice {

    public static final String TAG = DetailOrderFragment.class.getName();
    private static final String BUNDLE_ORDER = "BUNDLE_ORDER";
    private Order mOrder;
    @BindView(R.id.text_name_confirm)
    TextView mTextName;
    @BindView(R.id.text_phone_confirm)
    TextView mTextPhone;
    @BindView(R.id.text_address_confirm)
    TextView mTextAddress;
    @BindView(R.id.text_delivery)
    TextView mTextDelivery;
    @BindView(R.id.text_payment)
    TextView mTextPayment;
    @BindView(R.id.recycler_order_confirm)
    RecyclerView mRecyclerOrderConfirm;
    @BindView(R.id.text_confirm_price)
    TextView mTextConfirmPrice;
    @BindView(R.id.text_date_order)
    TextView mTextDateOrder;
    @BindView(R.id.text_code_order)
    TextView mTextCodeOrder;
    @BindView(R.id.text_status_order)
    TextView mTextStatusOrder;
    @BindView(R.id.ic_back)
    ImageView mImageBack;

    public static DetailOrderFragment newInstance(Order order) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_ORDER, order);
        DetailOrderFragment fragment = new DetailOrderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        mOrder = getArguments().getParcelable(BUNDLE_ORDER);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_detail_order;
    }

    @Override
    protected void initComponent(View view) {
        ButterKnife.bind(this, view);
        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() == null) {
                    return;
                }
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        if (mOrder == null) {
            return;
        }
        mTextName.setText(mOrder.getNameUser());
        mTextPhone.setText(mOrder.getPhoneNumber());
        mTextAddress.setText(mOrder.getAddressUser());
        mTextDelivery.setText(mOrder.getTypeOrder());
        mTextPayment.setText(mOrder.getTypePayment());
        mTextDateOrder.setText("Ngày đặt hàng: " + mOrder.getDateOrder());
        mTextStatusOrder.setText("Trạng thái: " + mOrder.getStatusOrder());
        String code = mOrder.getIdOrder()
                .substring(mOrder.getIdOrder().length() - 7, mOrder.getIdOrder().length() - 1);
        mTextCodeOrder.setText("Mã đơn hàng: " + code);
        initRecyclerView();
    }

    private void initRecyclerView() {
        onUpdatePrice();
        List<CartItem> cartItems = new ArrayList<>(mOrder.getCartItems());
        CartConfirmAdapter cartAdapter = new CartConfirmAdapter(cartItems, this);
        mRecyclerOrderConfirm.setNestedScrollingEnabled(false);
        mRecyclerOrderConfirm.setAdapter(cartAdapter);
    }

    @Override
    public void onUpdatePrice() {
        int total = 0;
        for (CartItem cartItem : mOrder.getCartItems()
                ) {
            String a = cartItem.getPrice().split("₫")[0];
            String b = a.replaceAll("\\.", "");
            total += Integer.parseInt(b) * cartItem.getQuantity();
        }

        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        mTextConfirmPrice.setText(fmt.format(total));
    }
}
