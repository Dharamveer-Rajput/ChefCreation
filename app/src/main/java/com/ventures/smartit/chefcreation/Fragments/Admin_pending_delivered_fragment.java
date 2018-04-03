package com.ventures.smartit.chefcreation.Fragments;


import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ventures.smartit.chefcreation.R;
import com.ventures.smartit.chefcreation.UI.Menu;

public class Admin_pending_delivered_fragment extends Fragment implements View.OnClickListener {

    public Button allOrderButton, pendingOrderButton, deliveredOrderButton;

    public static ViewPager mPager;
    public static int ITEMS = 3;
    MyAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_pending_delivered_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Buttons

        mPager = (ViewPager) view.findViewById(R.id.ordersRecyclerViewFragments);
        mAdapter = new MyAdapter(getFragmentManager());
        mPager.setAdapter(mAdapter);


        allOrderButton = (Button) view.findViewById(R.id.allOrderButton);
        pendingOrderButton = (Button) view.findViewById(R.id.pendingOrderButton);
        deliveredOrderButton = (Button) view.findViewById(R.id.deliveredOrderButton);

        allOrderButton.setOnClickListener(this);
        pendingOrderButton.setOnClickListener(this);
        deliveredOrderButton.setOnClickListener(this);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        allOrderButton.setTextColor(getResources().getColorStateList(R.color.fastred));

                        pendingOrderButton.setTextColor(getResources().getColorStateList(R.color.white));
                        deliveredOrderButton.setTextColor(getResources().getColorStateList(R.color.white));
                        break;
                    case 1:
                        pendingOrderButton.setTextColor(getResources().getColorStateList(R.color.fastred));

                        allOrderButton.setTextColor(getResources().getColorStateList(R.color.white));
                        deliveredOrderButton.setTextColor(getResources().getColorStateList(R.color.white));
                        break;
                    case 2:
                        deliveredOrderButton.setTextColor(getResources().getColorStateList(R.color.fastred));

                        allOrderButton.setTextColor(getResources().getColorStateList(R.color.white));
                        pendingOrderButton.setTextColor(getResources().getColorStateList(R.color.white));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(android.support.v4.app.FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return ITEMS;
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show Fragment All order
                    return Fragment_All_Order.init(position);
                case 1: // Fragment # 1 - This will show Delivered fragment
                    return Fragment_Pending_Order.init(position);
                default:// Fragment # 2 - Will show pending order Fragment
                    return Fragment_Delivered_Order.init(position);
            }
        }

    }


    @Override
    public void onClick(View v) {

        if (v == allOrderButton) {
            allOrderButton.setTextColor(getResources().getColorStateList(R.color.fastred));

            pendingOrderButton.setTextColor(getResources().getColorStateList(R.color.white));
            deliveredOrderButton.setTextColor(getResources().getColorStateList(R.color.white));
            Fragment_All_Order fragment_all_order = new Fragment_All_Order();
            android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.ordersRecyclerViewFragments, fragment_all_order, "All order fragment").commit();
        }
        if (v == pendingOrderButton) {
            pendingOrderButton.setTextColor(getResources().getColorStateList(R.color.fastred));

            allOrderButton.setTextColor(getResources().getColorStateList(R.color.white));
            deliveredOrderButton.setTextColor(getResources().getColorStateList(R.color.white));

            Fragment_Pending_Order fragment_pending_order = new Fragment_Pending_Order();
            android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.ordersRecyclerViewFragments, fragment_pending_order, "Pending Order fragment").commit();
        }
        if (v == deliveredOrderButton) {
            deliveredOrderButton.setTextColor(getResources().getColorStateList(R.color.fastred));

            allOrderButton.setTextColor(getResources().getColorStateList(R.color.white));
            pendingOrderButton.setTextColor(getResources().getColorStateList(R.color.white));

            Fragment_Delivered_Order fragment_delivered_order = new Fragment_Delivered_Order();
            android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.ordersRecyclerViewFragments, fragment_delivered_order, "Delivered Order fragment").commit();

        }
    }
}
