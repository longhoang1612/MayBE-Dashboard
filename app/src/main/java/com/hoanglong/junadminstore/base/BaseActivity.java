package com.hoanglong.junadminstore.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getLayoutResources();

    protected abstract void initComponent();

    protected abstract void initData(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResources());
        initComponent();
        initData(savedInstanceState);
    }
}
