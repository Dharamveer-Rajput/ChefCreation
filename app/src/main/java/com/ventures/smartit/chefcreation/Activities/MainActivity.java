package com.ventures.smartit.chefcreation.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.ventures.smartit.chefcreation.Fragments.SwipeFragment0;
import com.ventures.smartit.chefcreation.Fragments.SwipeFragment1;
import com.ventures.smartit.chefcreation.Fragments.SwipeFragment2;
import com.ventures.smartit.chefcreation.Fragments.SwipeFragment3;
import com.ventures.smartit.chefcreation.Fragments.SwipeFragment4;
import com.ventures.smartit.chefcreation.R;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private Button swipeButton;
    private Button signButton;
    private Button signUpButton;
    private Button signInAdminButton;
    static final int ITEMS = 5;
    private ViewPager viewPager;
    MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_activity);


        //Find id
        swipeButton = (Button) findViewById(R.id.swipeButton);
        signButton = (Button) findViewById(R.id.signButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        signInAdminButton = (Button) findViewById(R.id.signInAdminButton);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        viewPager.setAdapter(mAdapter);


        signUpButton.setOnClickListener(this);
        signButton.setOnClickListener(this);


        //circle indicator <code>
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);


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
                case 0: // Fragment # 0 - This will show Fragment menu
                    return SwipeFragment0.init(position);
                case 1: // Fragment # 0 - This will show Fragment menu
                    return SwipeFragment1.init(position);
                case 2: // Fragment # 1 - This will show Wishlist fragment
                    return SwipeFragment2.init(position);
                case 3: // Fragment # 2 - This will show Fragment Basket
                    return SwipeFragment3.init(position);
                default:// Fragment # 3 - Will show Account Fragment
                    return SwipeFragment4.init(position);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == signUpButton) {
            Intent i = new Intent(MainActivity.this, SigningUserActivity.class);
            i.putExtra("Frag", 1);
            startActivity(i);

        }
        if (v == signButton) {
            Intent i = new Intent(MainActivity.this, SigningUserActivity.class);
            i.putExtra("Frag", 2);
            startActivity(i);
        }
    }
}

