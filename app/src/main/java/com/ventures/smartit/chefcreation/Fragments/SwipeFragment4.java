package com.ventures.smartit.chefcreation.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ventures.smartit.chefcreation.R;

public class SwipeFragment4 extends Fragment {


    int fragVal;


    public static SwipeFragment4 init(int val) {
        SwipeFragment4 swipeFragment4 = new SwipeFragment4();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        swipeFragment4.setArguments(args);

        return swipeFragment4;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipe_fragment_4, container, false);
        return view;

    }


}
