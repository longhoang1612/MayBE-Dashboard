package com.hoanglong.junadminstore.screen.phone.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.SaleHolder> {

    private List<String> mSales;
    private LayoutInflater mInflater;

    public SaleAdapter(List<String> sales) {
        mSales = sales;
    }

    @NonNull
    @Override
    public SaleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_sale_product, viewGroup, false);
        return new SaleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleHolder saleHolder, int i) {
        String sale = mSales.get(i);
        saleHolder.bindData(sale);
    }

    @Override
    public int getItemCount() {
        return mSales != null ? mSales.size() : 0;
    }

    public static class SaleHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_item_sale)
        TextView mTextSaleItem;

        SaleHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bindData(String sale) {
            if(sale==null){
                return;
            }
            sale = sale.replaceAll("\\n","")
                    .replaceAll(" {30}","");
            mTextSaleItem.setText(sale);
        }
    }
}
