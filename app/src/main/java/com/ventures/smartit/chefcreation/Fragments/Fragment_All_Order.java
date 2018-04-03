package com.ventures.smartit.chefcreation.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ventures.smartit.chefcreation.Adapters.AllOrderAdapter;
import com.ventures.smartit.chefcreation.Models.OrdersInformation;
import com.ventures.smartit.chefcreation.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_All_Order extends Fragment {

    private AllOrderAdapter allOrderAdapter;
    private RecyclerView recyclerView;

    int fragVal;


    public static Fragment_All_Order init(int val) {
        Fragment_All_Order fragment_all_order = new Fragment_All_Order();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        fragment_all_order.setArguments(args);
        return fragment_all_order;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_order_fragment_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.all_order_recycler_view);
        allOrderAdapter = new AllOrderAdapter(getActivity(), getData());
        recyclerView.setAdapter(allOrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public static List<OrdersInformation> getData() {

        List<OrdersInformation> ordersInformations = new ArrayList<>();

        String[] customerid = {"Customer ID196", "Customer ID195", "Customer ID194", "Customer ID193"};

        String[] accepted = {"accepted", "pending", "accepted", "delivered"};

        String[] Rs = {"Rs.120", "Rs.200", "Rs.350", "Rs.150"};

        String[] time = {"11:45 AM", "11:00 AM", "10:30 AM", "10:15 AM"};

        for (int i = 0; i < customerid.length && i < accepted.length && i < Rs.length && i < time.length; i++) {
            OrdersInformation current = new OrdersInformation();
            current.Info_customerId = customerid[i];
            current.Info_accepted = accepted[i];
            current.Info_price = Rs[i];
            current.Info_time = time[i];

            ordersInformations.add(current);

        }
        return ordersInformations;
    }
}