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

import com.ventures.smartit.chefcreation.Models.DeliveredOrdersInformation;
import com.ventures.smartit.chefcreation.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fragment_Delivered_Order extends Fragment {


    private DeliveredOrderAdapter deliveredOrderAdapter;
    private RecyclerView recyclerView;

    int fragVal;


    public static Fragment_Delivered_Order init(int val) {
        Fragment_Delivered_Order fragment_delivered_order = new Fragment_Delivered_Order();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        fragment_delivered_order.setArguments(args);
        return fragment_delivered_order;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delivered_order_fragment_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.delivered_order_recycler_view);

        deliveredOrderAdapter = new DeliveredOrderAdapter(getActivity(), getData());
        recyclerView.setAdapter(deliveredOrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;

    }

    public static List<DeliveredOrdersInformation> getData() {

        List<DeliveredOrdersInformation> deliveredOrdersInformations = new ArrayList<>();

        String[] customerid = {"Order No.196", "Order No.195", "Order No.194", "Order No.193"};

        String[] accepted = {"Delivered", "Delivered", "Delivered", "Delivered"};

        String[] Rs = {"Rs.120", "Rs.200", "Rs.350", "Rs.150"};

        String[] time = {"11:45 AM", "11:00 AM", "10:30 AM", "10:15 AM"};

        for (int i = 0; i < customerid.length && i < accepted.length && i < Rs.length && i < time.length; i++) {
            DeliveredOrdersInformation current = new DeliveredOrdersInformation();
            current.Info_customerId = customerid[i];
            current.Info_accepted = accepted[i];
            current.Info_price = Rs[i];
            current.Info_time = time[i];

            deliveredOrdersInformations.add(current);

        }
        return deliveredOrdersInformations;
    }


}

class DeliveredOrderAdapter extends RecyclerView.Adapter<DeliveredOrderAdapter.MyViewHolder> {

    private LayoutInflater inflater;

    //Handles null pointer exceptions
    List<DeliveredOrdersInformation> deliveredOrdersInformations = Collections.emptyList();


    public DeliveredOrderAdapter(Context context, List<DeliveredOrdersInformation> deliveredOrdersInformations) {
        inflater = LayoutInflater.from(context);
        this.deliveredOrdersInformations = deliveredOrdersInformations;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.one_row_delivered, parent, false);

        DeliveredOrderAdapter.MyViewHolder holder = new DeliveredOrderAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DeliveredOrdersInformation current = deliveredOrdersInformations.get(position);
        holder.customerIdText.setText(current.Info_customerId);
        holder.acceptedText.setText(current.Info_accepted);
        holder.priceText.setText(current.Info_price);
        holder.timeText.setText(current.Info_time);
    }

    @Override
    public int getItemCount() {
        return deliveredOrdersInformations.size();
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
