package com.hoanglong.junadminstore.screen.phone.detail_product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.phone_product.DetailContent;
import com.hoanglong.junadminstore.screen.phone.adapter.ContentAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContentDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String BUNDLE_TITLE_CONTENT = "BUNDLE_CONTENT_TITLE";
    public static final String BUNDLE_H2_CONTENT = "BUNDLE_CONTENT_H2";
    public static final String BUNDLE_DETAIL_CONTENT = "BUNDLE_CONTENT_DETAIL";
    @BindView(R.id.recycler_content)
    RecyclerView mRecyclerContent;
    @BindView(R.id.text_title_content)
    TextView mTextTitleContent;
    @BindView(R.id.text_title_h2)
    TextView mTextH2;
    @BindView(R.id.ic_back)
    ImageView mImageBack;
    private String textH2;
    private String textTitle;
    private List<DetailContent> mContentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);
        getData();
        ButterKnife.bind(this);
        mImageBack.setOnClickListener(this);
        setData();
    }

    private void setData() {
        mTextH2.setText(textH2);
        mTextTitleContent.setText(textTitle);
        mRecyclerContent.setAdapter(new ContentAdapter(mContentList));
        mRecyclerContent.setNestedScrollingEnabled(false);
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        textH2 = bundle.getString(BUNDLE_H2_CONTENT, null);
        textTitle = bundle.getString(BUNDLE_TITLE_CONTENT, null);
        mContentList = bundle.getParcelableArrayList(BUNDLE_DETAIL_CONTENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_back:
                onBackPressed();
                break;
        }
    }
}
