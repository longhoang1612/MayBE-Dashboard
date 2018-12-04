package com.hoanglong.junadminstore.screen.delivery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoanglong.junadminstore.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryFragment extends Fragment {


    public static final String TAG = DeliveryFragment.class.getName();

    public DeliveryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery, container, false);
    }

}
