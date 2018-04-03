package com.ventures.smartit.chefcreation.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ventures.smartit.chefcreation.Models.PendingOrdersInformation;
import com.ventures.smartit.chefcreation.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Fragment_Pending_Order extends Fragment {


    private PendingOrderAdapter pendingOrderAdapter;
    private RecyclerView recyclerView;
    int fragVal;


    public static Fragment_Pending_Order init(int val) {
        Fragment_Pending_Order fragment_pending_order = new Fragment_Pending_Order();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        fragment_pending_order.setArguments(args);
        return fragment_pending_order;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pending_order_fragment_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.pending_order_recycler_view);
        pendingOrderAdapter = new PendingOrderAdapter(getActivity(), getData());
        recyclerView.setAdapter(pendingOrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public static List<PendingOrdersInformation> getData() {

        List<PendingOrdersInformation> pendingOrdersInformationList = new ArrayList<>();

        String[] customerid = {"Order No.196", "Order No.195", "Order No.194", "Order No.193"};

        String[] accepted = {"Pending", "Pending", "Pending", "Pending"};

        String[] Rs = {"Rs.120", "Rs.200", "Rs.350", "Rs.150"};

        String[] time = {"11:45 AM", "11:00 AM", "10:30 AM", "10:15 AM"};

        for (int i = 0; i < customerid.length && i < accepted.length && i < Rs.length && i < time.length; i++) {
            PendingOrdersInformation current = new PendingOrdersInformation();
            current.Info_customerId = customerid[i];
            current.Info_accepted = accepted[i];
            current.Info_price = Rs[i];
            current.Info_time = time[i];

            pendingOrdersInformationList.add(current);

        }
        return pendingOrdersInformationList;
    }
}


class PendingOrderAdapter extends RecyclerView.Adapter<PendingOrderAdapter.MyViewHolder> {

    private LayoutInflater inflater;

    //Handles null pointer exceptions
    List<PendingOrdersInformation> pendingOrdersInformationList = Collections.emptyList();


    public PendingOrderAdapter(Context context, List<PendingOrdersInformation> pendingOrdersInformationList) {
        inflater = LayoutInflater.from(context);
        this.pendingOrdersInformationList = pendingOrdersInformationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.one_row_pending_order, parent, false);

        PendingOrderAdapter.MyViewHolder holder = new PendingOrderAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final PendingOrdersInformation current = pendingOrdersInformationList.get(position);
        holder.customerIdText.setText(current.Info_customerId);
        holder.acceptedText.setText(current.Info_accepted);
        holder.priceText.setText(current.Info_price);
        holder.timeText.setText(current.Info_time);
    }

    @Override
    public int getItemCount() {
        return pendingOrdersInformationList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView customerIdText, acceptedText, priceText, timeText;


        public MyViewHolder(View itemView) {
            super(itemView);

            customerIdText = (TextView) itemView.findViewById(R.id.customerIdText);
            acceptedText = (TextView) itemView.findViewById(R.id.acceptedText);
            priceText = (TextView) itemView.findViewById(R.id.priceText);
            timeText = (TextView) itemView.findViewById(R.id.timeText);

        }
    }
}
