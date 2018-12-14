package com.hoanglong.junadminstore.screen.delivery;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hoanglong.junadminstore.DetailOrderFragment;
import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.base.BaseFragment;
import com.hoanglong.junadminstore.data.model.TypeConfirmOrder;
import com.hoanglong.junadminstore.data.model.order.Order;
import com.hoanglong.junadminstore.data.model.order.OrderUpload;
import com.hoanglong.junadminstore.service.BaseService;
import com.hoanglong.junadminstore.service.EventUpdate;
import com.hoanglong.junadminstore.utils.FragmentTransactionUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryFragment extends BaseFragment implements OrderDeliveryAdapter.OnClickOrderListener {


    public static final String TAG = DeliveryFragment.class.getName();
    @BindView(R.id.recycler_delivery)
    RecyclerView mRecyclerOrder;
    OrderDeliveryAdapter mOrderDeliveryAdapter;
    @BindView(R.id.progress_delivery)
    ProgressBar mProgressDelivery;
    @BindView(R.id.relative_empty)
    RelativeLayout mRelativeEmpty;
    @BindView(R.id.swipe_delivery)
    SwipeRefreshLayout mSwipeRefresh;

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_delivery;
    }

    @Override
    protected void initComponent(View view) {
        ButterKnife.bind(this, view);
        mProgressDelivery.setVisibility(View.VISIBLE);
        mRecyclerOrder.setVisibility(View.GONE);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefresh.setRefreshing(false);
                        getOrderDelivery();
                    }
                },500);
            }
        });
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
    }

    @Override
    public void onResume() {
        super.onResume();
        getOrderDelivery();
    }

    private void getOrderDelivery() {
        Call<OrderUpload> call = BaseService.getService().getDelivery(TypeConfirmOrder.ORDER_DELIVERY);
        call.enqueue(new Callback<OrderUpload>() {
            @Override
            public void onResponse(@NonNull Call<OrderUpload> call, @NonNull Response<OrderUpload> response) {
                if (response.body() != null) {
                    mProgressDelivery.setVisibility(View.GONE);
                    if (response.body().getOrders().size() == 0) {
                        mRelativeEmpty.setVisibility(View.VISIBLE);
                        mRecyclerOrder.setVisibility(View.GONE);
                    } else {
                        mRelativeEmpty.setVisibility(View.GONE);
                        mRecyclerOrder.setVisibility(View.VISIBLE);
                        mOrderDeliveryAdapter = new OrderDeliveryAdapter(
                                response.body().getOrders(),
                                DeliveryFragment.this);
                        mRecyclerOrder.setAdapter(mOrderDeliveryAdapter);
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<OrderUpload> call, @NonNull Throwable t) {
                mProgressDelivery.setVisibility(View.GONE);
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

    @Override
    public void update() {
        getOrderDelivery();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventUpdate update) {
        Toast.makeText(getContext(), "Update", Toast.LENGTH_SHORT).show();
        getOrderDelivery();
    }
}
