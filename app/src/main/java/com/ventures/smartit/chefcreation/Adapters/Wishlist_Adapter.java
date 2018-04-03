package com.ventures.smartit.chefcreation.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ventures.smartit.chefcreation.Models.Information_Menu;
import com.ventures.smartit.chefcreation.Models.Information_Wishlist;
import com.ventures.smartit.chefcreation.Models.Save_model;
import com.ventures.smartit.chefcreation.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Wishlist_Adapter extends RecyclerView.Adapter<Wishlist_Adapter.MyViewHolder> {


    private ArrayList<Save_model> save_models;


    Context c;
    //Handles null pointer exceptions
    List<Save_model> data = Collections.emptyList();


    public Wishlist_Adapter(Context c, ArrayList<Save_model> save_models) {

        this.save_models = save_models;
        this.data = save_models;
        this.c = c;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //View v=inflater.inflate(R.layout.wishlist_row,parent,false);
        View view = inflater.inflate(R.layout.wishlist_row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Save_model current = data.get(position);

        holder.txtItemName.setText(current.getInfo_title());
        holder.txtDescrip.setText(current.getInfo_description());
        holder.itemPrice.setText(current.getInfo_txtPrice());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtItemName;
        private TextView txtDescrip;
        private TextView itemPrice;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtItemName = (TextView) itemView.findViewById(R.id.txtTitle);
            txtDescrip = (TextView) itemView.findViewById(R.id.txtDesc);
            itemPrice = (TextView) itemView.findViewById(R.id.textPriceWishlist);


        }
    }
}
