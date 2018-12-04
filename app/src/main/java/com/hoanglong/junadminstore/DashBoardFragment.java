package com.hoanglong.junadminstore;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoanglong.junadminstore.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoardFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = DashBoardFragment.class.getName();
    @BindView(R.id.fab_add_new)
    FloatingActionButton mFabAddNewItem;

    public static DashBoardFragment newInstance() {
        Bundle args = new Bundle();
        DashBoardFragment fragment = new DashBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_dash_board;
    }

    @Override
    protected void initComponent(View view) {
        ButterKnife.bind(this, view);
        mFabAddNewItem.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_new:
                Intent intent = new Intent(getActivity(), AddNewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
