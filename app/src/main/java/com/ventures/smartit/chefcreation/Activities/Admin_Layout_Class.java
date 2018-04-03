package com.ventures.smartit.chefcreation.Activities;


import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ventures.smartit.chefcreation.Fragments.Admin_pending_delivered_fragment;
import com.ventures.smartit.chefcreation.Fragments.Fragment_All_Order;
import com.ventures.smartit.chefcreation.R;

public class Admin_Layout_Class extends FragmentActivity implements View.OnClickListener {

    private ImageButton menuImage;
    TextView tvMenu, tvWishlist, tvBasket, tvAccount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);

        menuImage = (ImageButton) findViewById(R.id.menuImage);
        tvMenu = (TextView) findViewById(R.id.tvMenu);


        if (savedInstanceState == null) {

            menuImage.setBackgroundResource(R.drawable.menured);
            tvMenu.setTextColor(getResources().getColorStateList(R.color.fastred));
            Admin_pending_delivered_fragment admin_pending_delivered_fragment = new Admin_pending_delivered_fragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.frame, admin_pending_delivered_fragment, "Admin Pending Fragment").commit();


            Fragment_All_Order fragment_all_order = new Fragment_All_Order();
            fragmentManager.beginTransaction().replace(R.id.ordersRecyclerViewFragments, fragment_all_order, "All order fragment").commit();

        }
        menuImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == menuImage) {
            menuImage.setBackgroundResource(R.drawable.menured);
            tvMenu.setTextColor(getResources().getColorStateList(R.color.fastred));

            tvMenu.setTextColor(getResources().getColorStateList(R.color.fastred));
            Admin_pending_delivered_fragment admin_pending_delivered_fragment = new Admin_pending_delivered_fragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.frame, admin_pending_delivered_fragment, "Admin Pending Fragment").commit();


        }
    }
}
