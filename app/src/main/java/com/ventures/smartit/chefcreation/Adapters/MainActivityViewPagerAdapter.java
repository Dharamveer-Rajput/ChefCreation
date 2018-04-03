package com.ventures.smartit.chefcreation.Adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.ventures.smartit.chefcreation.Activities.MainActivity;

public class MainActivityViewPagerAdapter extends PagerAdapter {


    public MainActivityViewPagerAdapter(MainActivity mainActivity) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
