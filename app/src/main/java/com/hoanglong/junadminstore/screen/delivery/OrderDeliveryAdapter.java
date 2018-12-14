package com.hoanglong.junadminstore.screen.delivery;

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

import static com.hoanglong.junadminstore.data.model.TypeConfirmOrder.ORDER_DONE;

public class OrderDeliveryAdapter extends RecyclerView.Adapter<OrderDeliveryAdapter.OrderViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Order> mOrders;
    private OnClickOrderListener mOnClickOrderListener;

    OrderDeliveryAdapter(List<Order> orders, OnClickOrderListener onClickOrderListener) {
        mOrders = orders;
        mOnClickOrderListener = onClickOrderListener;
    }

    public interface OnClickOrderListener {
        void onClickOrder(Order order);

        void update();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mLayoutInflater.inflate(R.layout.item_delivery_order, viewGroup, false);
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
        @BindView(R.id.text_done)
        TextView mTextDone;
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

            if (order.getStatusOrder().equals(ORDER_DONE)) {
                mImageStatus.setVisibility(View.VISIBLE);
                mTextDone.setVisibility(View.GONE);
            } else {
                mImageStatus.setVisibility(View.GONE);
                mTextDone.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ic_status:
                    mImageStatus.setVisibility(View.VISIBLE);
                    mTextDone.setVisibility(View.GONE);
                    Utils.uploadStatus(mOrder.getIdOrder(), ORDER_DONE);
                    mOnClickOrderListener.update();
                    EventBus.getDefault().postSticky(new EventUpdate("Update"));
                    break;
                case R.id.constraint_order:
                    mOnClickOrderListener.onClickOrder(mOrder);
                    break;
            }
        }
    }
}
