package com.hoanglong.junadminstore.screen.addnew;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddSaleAdapter extends RecyclerView.Adapter<AddSaleAdapter.SaleViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<String> mSales;

    public AddSaleAdapter(List<String> sales) {
        mSales = sales;
    }

    @NonNull
    @Override
    public SaleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mLayoutInflater.inflate(R.layout.item_add_sale, viewGroup, false);
        return new SaleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleViewHolder saleViewHolder, int i) {
        String sale = mSales.get(i);
        saleViewHolder.bindData(sale, i);
    }

    @Override
    public int getItemCount() {
        return mSales != null ? mSales.size() : 0;
    }

    public class SaleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.text_add_sale)
        TextView mTextAddSale;
        @BindView(R.id.delete_sale)
        ImageView mImageDeleteSale;
        private int mPosition;

        SaleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mImageDeleteSale.setOnClickListener(this);
        }

        public void bindData(String sale, int position) {
            mTextAddSale.setText(sale);
            mPosition = position;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.delete_sale:
                    mSales.remove(mPosition);
                    notifyItemRemoved(mPosition);
                    notifyItemRangeChanged(mPosition, mSales.size());
                    notifyDataSetChanged();
                    break;
            }
        }
    }
}
