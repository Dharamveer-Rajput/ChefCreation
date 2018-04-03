package com.ventures.smartit.chefcreation.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ventures.smartit.chefcreation.R;

/**
 * Created by Dell on 10-05-2017.
 */

public class SwipeFragment0 extends Fragment {

    int fragVal;


    public static SwipeFragment0 init(int val) {
        SwipeFragment0 swipeFragment0 = new SwipeFragment0();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        swipeFragment0.setArguments(args);

        return swipeFragment0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipe_fragment_0, container, false);
        return view;

    }
}
