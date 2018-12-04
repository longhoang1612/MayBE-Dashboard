package com.hoanglong.junadminstore.screen.phone.detail_product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.phone_product.ListParameter;
import com.hoanglong.junadminstore.screen.phone.adapter.InfoAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoDetailActivity extends AppCompatActivity {

    public static final String BUNDLE_INFO = "BUNDLE_INFO";
    @BindView(R.id.ic_back)
    ImageView mImageBack;
    @BindView(R.id.recycler_info)
    RecyclerView mRecyclerInfo;
    private List<ListParameter> mParameterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);
        ButterKnife.bind(this);
        getData();

        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mRecyclerInfo.setAdapter(new InfoAdapter(mParameterList));
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mParameterList = bundle.getParcelableArrayList(BUNDLE_INFO);
        }
    }
}
