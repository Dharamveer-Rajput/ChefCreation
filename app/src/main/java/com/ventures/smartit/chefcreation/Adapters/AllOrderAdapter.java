package com.ventures.smartit.chefcreation.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ventures.smartit.chefcreation.Models.OrdersInformation;
import com.ventures.smartit.chefcreation.R;

import java.util.Collections;
import java.util.List;

public class AllOrderAdapter extends RecyclerView.Adapter<AllOrderAdapter.MyViewHolder> {

    private LayoutInflater inflater;

    //Handles null pointer exceptions
    List<OrdersInformation> ordersInformations = Collections.emptyList();


    public AllOrderAdapter(Context context, List<OrdersInformation> ordersInformations) {
        inflater = LayoutInflater.from(context);
        this.ordersInformations = ordersInformations;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.one_row_all_order, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final OrdersInformation current = ordersInformations.get(position);

        holder.customerIdText.setText(current.Info_customerId);
        holder.acceptedText.setText(current.Info_accepted);
        holder.priceText.setText(current.Info_price);
        holder.timeText.setText(current.Info_time);

    }

    @Override
    public int getItemCount() {
        return ordersInformations.size();
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
