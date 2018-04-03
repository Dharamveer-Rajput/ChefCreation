package com.ventures.smartit.chefcreation.Fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ventures.smartit.chefcreation.Adapters.Wishlist_Adapter;
import com.ventures.smartit.chefcreation.DatabaseHandler.DBHelper;
import com.ventures.smartit.chefcreation.Models.Save_model;
import com.ventures.smartit.chefcreation.R;
import com.ventures.smartit.chefcreation.UI.Menu;

import java.util.ArrayList;


public class FragmentWishlist extends Fragment {

    private Wishlist_Adapter adapter;

    private RecyclerView recyclerView;
    DBHelper dbHelper;

    private Button btnProceedBasket;
    int fragNum;
    private static final String TAG = "MyApp";

    ArrayList<Save_model> save_models;
    public static ViewPager mPager;

    public static FragmentWishlist init(int val) {
        FragmentWishlist fragmentWishlist = new FragmentWishlist();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        fragmentWishlist.setArguments(args);

        return fragmentWishlist;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragNum = getArguments() != null ? getArguments().getInt("val") : 1;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + e.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_wishlist, container, false);

        dbHelper = new DBHelper(getActivity());

        recyclerView = (RecyclerView) layout.findViewById(R.id.wishlist_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        save_models = dbHelper.getAllValue_Wishlist();
        adapter = new Wishlist_Adapter(getActivity(), save_models);


        recyclerView.setAdapter(adapter);
        recyclerView.invalidate();
        adapter.notifyDataSetChanged();

        btnProceedBasket = (Button) layout.findViewById(R.id.btnProceedBasket);
        btnProceedBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Menu.mPager.setCurrentItem(2);

            }
        });
        return layout;
    }

}

