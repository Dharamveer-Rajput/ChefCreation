package com.ventures.smartit.chefcreation.UI;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ventures.smartit.chefcreation.Activities.SigningUserActivity;
import com.ventures.smartit.chefcreation.Fragments.FragmentMenu;
import com.ventures.smartit.chefcreation.Fragments.FragmentWishlist;
import com.ventures.smartit.chefcreation.Fragments.Fragment_Account;
import com.ventures.smartit.chefcreation.Fragments.Fragment_Basket;
import com.ventures.smartit.chefcreation.Models.Save_model;
import com.ventures.smartit.chefcreation.Models.User;
import com.ventures.smartit.chefcreation.R;
import com.ventures.smartit.chefcreation.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    static final int ITEMS = 4;
    MyAdapter mAdapter;

    //Image Button
    ImageButton menuImage;
    ImageButton wishlistImage;
    ImageButton imageBasket;
    ImageButton imageAccount;

    //Text Views
    TextView tvMenu, tvWishlist, tvBasket, tvAccount;
    public static ViewPager mPager;

    DataReceivedListener listener;



    public interface DataReceivedListener {
        void onReceived(ArrayList<Save_model> save_models);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_logout:
                new User(Menu.this).removeUser();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Menu.this);

                // Setting Dialog Title
                alertDialog.setTitle("Confirm Logout...");

                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want Logout?");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.ic_power_settings);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Menu.this, SigningUserActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent sameIntent = new Intent(Menu.this,
//                                Menu.class);
//                        startActivity(sameIntent);
//                        finish();
                    }
                });


                // Showing Alert Message
                alertDialog.show();
                return true;


            case R.id.Next:

                menuImage.setBackgroundResource(R.drawable.menuwhite);
                tvMenu.setTextColor(getResources().getColorStateList(R.color.white));

                wishlistImage.setBackgroundResource(R.drawable.heartred);
                tvWishlist.setTextColor(getResources().getColorStateList(R.color.fastred));


                mPager.setCurrentItem(1);


        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.viewPager4);
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                if (position == 0) {
                    menuImage.setBackgroundResource(R.drawable.menured);
                    wishlistImage.setBackgroundResource(R.drawable.wishlistwhite);
                    imageBasket.setBackgroundResource(R.drawable.basketwhite);
                    imageAccount.setBackgroundResource(R.drawable.accountwhite);

                    tvMenu.setTextColor(getResources().getColorStateList(R.color.fastred));

                    tvWishlist.setTextColor(getResources().getColorStateList(R.color.white));
                    tvBasket.setTextColor(getResources().getColorStateList(R.color.white));
                    tvAccount.setTextColor(getResources().getColorStateList(R.color.white));
                } else if (position == 1) {



                    mPager.getAdapter().notifyDataSetChanged();

                    wishlistImage.setBackgroundResource(R.drawable.heartred);

                    menuImage.setBackgroundResource(R.drawable.menuwhite);
                    imageBasket.setBackgroundResource(R.drawable.basketwhite);
                    imageAccount.setBackgroundResource(R.drawable.accountwhite);

                    tvWishlist.setTextColor(getResources().getColorStateList(R.color.fastred));

                    tvMenu.setTextColor(getResources().getColorStateList(R.color.white));
                    tvBasket.setTextColor(getResources().getColorStateList(R.color.white));
                    tvAccount.setTextColor(getResources().getColorStateList(R.color.white));


                } else if (position == 2) {
                    imageBasket.setBackgroundResource(R.drawable.basketred);

                    wishlistImage.setBackgroundResource(R.drawable.wishlistwhite);
                    menuImage.setBackgroundResource(R.drawable.menuwhite);
                    imageAccount.setBackgroundResource(R.drawable.accountwhite);

                    tvBasket.setTextColor(getResources().getColorStateList(R.color.fastred));

                    tvMenu.setTextColor(getResources().getColorStateList(R.color.white));
                    tvWishlist.setTextColor(getResources().getColorStateList(R.color.white));
                    tvAccount.setTextColor(getResources().getColorStateList(R.color.white));
                } else if (position == 3) {
                    imageAccount.setBackgroundResource(R.drawable.accountred);

                    menuImage.setBackgroundResource(R.drawable.menuwhite);
                    wishlistImage.setBackgroundResource(R.drawable.wishlistwhite);
                    imageBasket.setBackgroundResource(R.drawable.basketwhite);

                    tvAccount.setTextColor(getResources().getColorStateList(R.color.fastred));

                    tvMenu.setTextColor(getResources().getColorStateList(R.color.white));
                    tvWishlist.setTextColor(getResources().getColorStateList(R.color.white));
                    tvBasket.setTextColor(getResources().getColorStateList(R.color.white));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (savedInstanceState == null) {
            FragmentMenu fragment = new FragmentMenu();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.viewPager4, fragment, "Fragment Menu").commit();
        }

        //Image Button initialization
        menuImage = (ImageButton) findViewById(R.id.menuImage);
        wishlistImage = (ImageButton) findViewById(R.id.wishlistImage);
        imageBasket = (ImageButton) findViewById(R.id.imageBasket);
        imageAccount = (ImageButton) findViewById(R.id.imageAccount);


        //TextView initialization
        tvMenu = (TextView) findViewById(R.id.tvMenu);
        tvWishlist = (TextView) findViewById(R.id.tvWishlist);
        tvBasket = (TextView) findViewById(R.id.tvBasket);
        tvAccount = (TextView) findViewById(R.id.tvAccount);


        menuImage.setOnClickListener(this);
        wishlistImage.setOnClickListener(this);
        imageBasket.setOnClickListener(this);
        imageAccount.setOnClickListener(this);


    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
        FragmentMenu fragmentMenu;
        FragmentWishlist fragmentWishlist;

        public MyAdapter(android.support.v4.app.FragmentManager fragmentManager) {
            super(fragmentManager);


            fragmentMenu = new FragmentMenu();
            fragmentWishlist = new FragmentWishlist();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }


        @Override
        public int getCount() {
            return ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show Fragment menu
                    return FragmentMenu.init(position);
                case 1: // Fragment # 1 - This will show Wishlist fragment
                    return FragmentWishlist.init(position);
                case 2: // Fragment # 2 - This will show Fragment Basket
                    return Fragment_Basket.init(position);
                default:// Fragment # 3 - Will show Account Fragment
                    return Fragment_Account.init(position);
            }
        }

    }


    @Override
    public void onClick(View v) {
        if (v == menuImage) {
            mPager.setCurrentItem(0);
            menuImage.setBackgroundResource(R.drawable.menured);

            wishlistImage.setBackgroundResource(R.drawable.wishlistwhite);
            imageBasket.setBackgroundResource(R.drawable.basketwhite);
            imageAccount.setBackgroundResource(R.drawable.accountwhite);

            tvMenu.setTextColor(getResources().getColorStateList(R.color.fastred));

            tvWishlist.setTextColor(getResources().getColorStateList(R.color.white));
            tvBasket.setTextColor(getResources().getColorStateList(R.color.white));
            tvAccount.setTextColor(getResources().getColorStateList(R.color.white));


        }
        if (v == wishlistImage) {
            mPager.setCurrentItem(1);
            wishlistImage.setBackgroundResource(R.drawable.heartred);

            menuImage.setBackgroundResource(R.drawable.menuwhite);
            imageBasket.setBackgroundResource(R.drawable.basketwhite);
            imageAccount.setBackgroundResource(R.drawable.accountwhite);

            tvWishlist.setTextColor(getResources().getColorStateList(R.color.fastred));

            tvMenu.setTextColor(getResources().getColorStateList(R.color.white));
            tvBasket.setTextColor(getResources().getColorStateList(R.color.white));
            tvAccount.setTextColor(getResources().getColorStateList(R.color.white));


        }
        if (v == imageBasket) {
            mPager.setCurrentItem(2);
            imageBasket.setBackgroundResource(R.drawable.basketred);

            wishlistImage.setBackgroundResource(R.drawable.wishlistwhite);
            menuImage.setBackgroundResource(R.drawable.menuwhite);
            imageAccount.setBackgroundResource(R.drawable.accountwhite);

            tvBasket.setTextColor(getResources().getColorStateList(R.color.fastred));

            tvMenu.setTextColor(getResources().getColorStateList(R.color.white));
            tvWishlist.setTextColor(getResources().getColorStateList(R.color.white));
            tvAccount.setTextColor(getResources().getColorStateList(R.color.white));


        }
        if (v == imageAccount) {
            mPager.setCurrentItem(3);
            imageAccount.setBackgroundResource(R.drawable.accountred);

            menuImage.setBackgroundResource(R.drawable.menuwhite);
            wishlistImage.setBackgroundResource(R.drawable.wishlistwhite);
            imageBasket.setBackgroundResource(R.drawable.basketwhite);

            tvAccount.setTextColor(getResources().getColorStateList(R.color.fastred));

            tvMenu.setTextColor(getResources().getColorStateList(R.color.white));
            tvWishlist.setTextColor(getResources().getColorStateList(R.color.white));
            tvBasket.setTextColor(getResources().getColorStateList(R.color.white));


        }

    }

}