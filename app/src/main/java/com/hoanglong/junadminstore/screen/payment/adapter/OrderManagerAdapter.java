package com.hoanglong.junadminstore.screen.payment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.order.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderManagerAdapter extends RecyclerView.Adapter<OrderManagerAdapter.OrderViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Order> mOrders;
    private OnClickOrderListener mOnClickOrderListener;

    public OrderManagerAdapter(List<Order> orders, OnClickOrderListener onClickOrderListener) {
        mOrders = orders;
        mOnClickOrderListener = onClickOrderListener;
    }

    public interface OnClickOrderListener {
        void onClickOrder(Order order);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mLayoutInflater.inflate(R.layout.item_manager_order, viewGroup, false);
        return new OrderViewHolder(view, mOnClickOrderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
        Order order = mOrders.get(i);
        orderViewHolder.bindData(order);
    }

    @Override
    public int getItemCount() {
        return mOrders != null ? mOrders.size() : 0;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Order mOrder;
        private OnClickOrderListener mOnClickOrderListener;
        @BindView(R.id.text_title_order)
        TextView mTextTileOrder;
        @BindView(R.id.text_code_order)
        TextView mTextCodeOrder;
        @BindView(R.id.text_date_order)
        TextView mTextDateOrder;
        @BindView(R.id.text_status_order)
        TextView mTextStatusOrder;

        OrderViewHolder(@NonNull View itemView, OnClickOrderListener onClickOrderListener) {
            super(itemView);
            mOnClickOrderListener = onClickOrderListener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindData(Order order) {
            if (order == null) {
                return;
            }
            mOrder = order;
            String codeOrder = order.getIdOrder()
                    .substring(order.getIdOrder().length()-7,order.getIdOrder().length()-1);
            mTextTileOrder.setText("Mã đơn hàng: " + codeOrder);
            mTextCodeOrder.setText("Người đặt hàng: " + order.getNameUser());
            mTextDateOrder.setText("Ngày đặt hàng: " + order.getDateOrder());
            mTextStatusOrder.setText("Trạng thái: " + order.getStatusOrder());

        }

        @Override
        public void onClick(View v) {
            mOnClickOrderListener.onClickOrder(mOrder);
        }
    }
}
