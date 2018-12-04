package com.hoanglong.junadminstore;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hoanglong.junadminstore.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNewActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ic_back)
    ImageView mImageBack;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_add_new;
    }

    @Override
    protected void initComponent() {
        ButterKnife.bind(this);
        mImageBack.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_back:
                onBackPressed();
                break;
        }
    }
}
