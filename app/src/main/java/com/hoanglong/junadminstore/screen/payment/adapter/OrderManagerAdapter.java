package com.hoanglong.junadminstore.screen.payment.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.order.Order;
import com.hoanglong.junadminstore.service.EventUpdate;
import com.hoanglong.junadminstore.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hoanglong.junadminstore.data.model.TypeConfirmOrder.ORDER_CONFIRMED;
import static com.hoanglong.junadminstore.data.model.TypeConfirmOrder.ORDER_DELIVERY;
import static com.hoanglong.junadminstore.data.model.TypeConfirmOrder.ORDER_DONE;
import static com.hoanglong.junadminstore.data.model.TypeConfirmOrder.ORDER_WAITING_CONFIRM;

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
        void onUpdate();
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

    public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
        @BindView(R.id.image_status)
        ImageView mImageStatus;
        @BindView(R.id.ic_status)
        RelativeLayout mRelativeStatus;
        @BindView(R.id.constraint_order)
        ConstraintLayout mConstraintOrder;

        OrderViewHolder(@NonNull View itemView, OnClickOrderListener onClickOrderListener) {
            super(itemView);
            mOnClickOrderListener = onClickOrderListener;
            ButterKnife.bind(this, itemView);
            mConstraintOrder.setOnClickListener(this);
            mRelativeStatus.setOnClickListener(this);
        }

        public void bindData(Order order) {
            if (order == null) {
                return;
            }
            mOrder = order;
            String codeOrder = order.getIdOrder()
                    .substring(order.getIdOrder().length() - 7, order.getIdOrder().length() - 1);
            mTextTileOrder.setText(String.format("Mã đơn hàng: %s", codeOrder));
            mTextCodeOrder.setText(String.format("Người đặt hàng: %s", order.getNameUser()));
            mTextDateOrder.setText(String.format("Ngày đặt hàng: %s", order.getDateOrder()));
            mTextStatusOrder.setText(String.format("Trạng thái: %s", order.getStatusOrder()));

            switch (order.getStatusOrder()) {
                case ORDER_WAITING_CONFIRM:
                    mImageStatus.setImageResource(R.drawable.ic_filter_tilt_shift_black_24dp);
                    break;
                case ORDER_CONFIRMED:
                    mImageStatus.setImageResource(R.drawable.ic_cofirm);
                    break;
                case ORDER_DELIVERY:
                    mImageStatus.setImageResource(R.drawable.ic_delivery);
                    break;
                case ORDER_DONE:
                    mImageStatus.setImageResource(R.drawable.ic_done);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ic_status:
                    if (mOrder.getStatusOrder().equals(ORDER_WAITING_CONFIRM)) {
                        Utils.uploadStatus(mOrder.getIdOrder(), ORDER_CONFIRMED);
                        mImageStatus.setImageResource(R.drawable.ic_cofirm);
                        mOnClickOrderListener.onUpdate();
                    } else if (mOrder.getStatusOrder().equals(ORDER_CONFIRMED)) {
                        Utils.uploadStatus(mOrder.getIdOrder(), ORDER_DELIVERY);
                        mImageStatus.setImageResource(R.drawable.ic_delivery);
                        mOnClickOrderListener.onUpdate();
                        EventBus.getDefault().postSticky(new EventUpdate("Update"));
                    }
                    break;
                case R.id.constraint_order:
                    mOnClickOrderListener.onClickOrder(mOrder);
                    break;
            }
        }
    }
}
