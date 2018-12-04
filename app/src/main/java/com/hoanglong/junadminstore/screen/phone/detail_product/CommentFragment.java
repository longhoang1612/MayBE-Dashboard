package com.hoanglong.junadminstore.screen.phone.detail_product;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.base.BaseFragment;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CommentFragment extends BaseFragment {
    public static final String TAG = CommentFragment.class.getName();
    public static final String BUNDLE_PHONE = "BUNDLE_PHONE";

    @BindView(R.id.image_phone)
    ImageView mImagePhone;
    @BindView(R.id.text_phone)
    TextView mTextPhone;
    @BindView(R.id.ic_back)
    ImageView mImageBack;

    private Listener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (Listener) context;
    }

    public static CommentFragment newInstance(ItemPhoneProduct itemPhoneProduct) {
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_PHONE, itemPhoneProduct);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initComponent(View view) {
        ButterKnife.bind(this, view);
        mListener.onHideButtonCart(View.GONE);
        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            return;
        }
        ItemPhoneProduct phoneProduct = bundle.getParcelable(BUNDLE_PHONE);
        if (phoneProduct == null) {
            return;
        }
        mTextPhone.setText(phoneProduct.getTitle());
        if (getContext() == null) {
            return;
        }
        Glide.with(getContext()).load(phoneProduct.getImage()).into(mImagePhone);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onHideButtonCart(View.VISIBLE);
    }
}
