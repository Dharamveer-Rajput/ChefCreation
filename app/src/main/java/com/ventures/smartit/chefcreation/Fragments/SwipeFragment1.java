package com.ventures.smartit.chefcreation.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ventures.smartit.chefcreation.R;


public class SwipeFragment1 extends Fragment {

    int fragVal;


    public static SwipeFragment1 init(int val) {
        SwipeFragment1 swipeFragment1 = new SwipeFragment1();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        swipeFragment1.setArguments(args);

        return swipeFragment1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipe_fragment_1, container, false);
        return view;

    }
}
