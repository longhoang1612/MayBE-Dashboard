package com.hoanglong.junadminstore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    fragment = getSupportFragmentManager().findFragmentByTag(DashBoardFragment.TAG);
                    if (fragment == null) {
                        fragment = new DashBoardFragment();
                    }
                    FragmentTransactionUtils.addFragment(getSupportFragmentManager(), fragment,
                            R.id.container, DashBoardFragment.TAG);
                    return true;
                case R.id.navigation_payment:
                    fragment = getSupportFragmentManager().findFragmentByTag(PaymentFragment.TAG);
                    if (fragment == null) {
                        fragment = new PaymentFragment();
                    }
                    FragmentTransactionUtils.addFragment(getSupportFragmentManager(), fragment,
                            R.id.container, PaymentFragment.TAG);
                    return true;
                case R.id.navigation_delivery:
                    fragment = getSupportFragmentManager().findFragmentByTag(DeliveryFragment.TAG);
                    if (fragment == null) {
                        fragment = new DeliveryFragment();
                    }
                    FragmentTransactionUtils.addFragment(getSupportFragmentManager(), fragment,
                            R.id.container, DeliveryFragment.TAG);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    private void openDefaultFragment() {
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        FragmentTransactionUtils.addFragment(getSupportFragmentManager(), new DashBoardFragment(),
                R.id.container, DashBoardFragment.TAG);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        openDefaultFragment();
    }

}
