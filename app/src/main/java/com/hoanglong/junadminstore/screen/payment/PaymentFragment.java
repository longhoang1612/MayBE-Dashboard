package com.hoanglong.junadminstore.screen.payment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hoanglong.junadminstore.DetailOrderFragment;
import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.base.BaseFragment;
import com.hoanglong.junadminstore.data.model.order.Order;
import com.hoanglong.junadminstore.data.model.order.OrderUpload;
import com.hoanglong.junadminstore.screen.payment.adapter.OrderManagerAdapter;
import com.hoanglong.junadminstore.service.BaseService;
import com.hoanglong.junadminstore.utils.FragmentTransactionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentFragment extends BaseFragment implements OrderManagerAdapter.OnClickOrderListener {

    public static final String TAG = PaymentFragment.class.getName();
    @BindView(R.id.recycler_order)
    RecyclerView mRecyclerOrder;
    @BindView(R.id.progress_payment)
    ProgressBar mProgressPayment;
    @BindView(R.id.relative_empty)
    RelativeLayout mRelativeEmpty;
    OrderManagerAdapter mOrderManagerAdapter;

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_payment;
    }

    @Override
    protected void initComponent(View view) {
        ButterKnife.bind(this, view);
        mProgressPayment.setVisibility(View.VISIBLE);
        mRecyclerOrder.setVisibility(View.GONE);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        getAllOrder();
    }

    private void getAllOrder() {
        Call<OrderUpload> call = BaseService.getService().getAllOrder();
        call.enqueue(new Callback<OrderUpload>() {
            @Override
            public void onResponse(@NonNull Call<OrderUpload> call, @NonNull Response<OrderUpload> response) {
                if (response.body() != null) {
                    mProgressPayment.setVisibility(View.GONE);
                    if(response.body().getOrders().size() == 0){
                        mRelativeEmpty.setVisibility(View.VISIBLE);
                        mRecyclerOrder.setVisibility(View.GONE);
                    }else{
                        mRelativeEmpty.setVisibility(View.GONE);
                        mRecyclerOrder.setVisibility(View.VISIBLE);
                        mRecyclerOrder.setVisibility(View.VISIBLE);
                        mOrderManagerAdapter = new OrderManagerAdapter(
                                response.body().getOrders(),
                                PaymentFragment.this);
                        mRecyclerOrder.setAdapter(mOrderManagerAdapter);
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<OrderUpload> call, @NonNull Throwable t) {
                mProgressPayment.setVisibility(View.GONE);
                mRecyclerOrder.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClickOrder(Order order) {
        if (getFragmentManager() != null) {
            FragmentTransactionUtils.addFragment(
                    getFragmentManager(),
                    DetailOrderFragment.newInstance(order), R.id.frame_home, DetailOrderFragment.TAG,
                    true, -1, -1);
        }
    }
}
