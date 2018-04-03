package com.ventures.smartit.chefcreation.Adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.ventures.smartit.chefcreation.DatabaseHandler.DBHelper;
import com.ventures.smartit.chefcreation.Models.Information_Menu;
import com.ventures.smartit.chefcreation.Models.Save_model;
import com.ventures.smartit.chefcreation.MyInterface.ItemClickListener;
import com.ventures.smartit.chefcreation.R;
import com.ventures.smartit.chefcreation.Response.selectProduct.ProductPayload;
import com.ventures.smartit.chefcreation.Response.signInPackage.Payload;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private ArrayList<ProductPayload> productList;
    private Context context;
    DBHelper dbHelper;
    private ItemClickListener clickListener;
    Bitmap downloadedImage;

    public MenuAdapter(Context context, ArrayList<ProductPayload> productList) {
        this.productList = productList;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.custom_row_chef, parent, false);
        //View view=inflater.inflate(R.layout.custom,parent,false);

        // Return a new holder instance
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ProductPayload current = productList.get(position);

        holder.txtItem.setText(current.getName());
        holder.txtDescrip.setText(current.getDescription());
        holder.txtPrice.setText(current.getPrice());
        holder.txtTime.setText(current.getReadyto());

        final Uri uri = Uri.parse(current.getImage());
        //admin/image/product
        context = holder.image_name.getContext();
        Picasso.with(context)
                .load("http://192.168.0.45:8080/Chefcreation/admin/img/product/" + uri)
                .into(holder.image_name);



        holder.heartImageView.setImageResource(R.drawable.heartoff);
        holder.heartImageView.setTag(R.drawable.heartoff);

        holder.heartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer resource = (Integer) holder.heartImageView.getTag();

                if (resource == R.drawable.heartoff) {

                    holder.heartImageView.setImageResource(R.drawable.heartred);
                    holder.heartImageView.setTag(R.drawable.heartred);

                    Save_model save_model = new Save_model();
                    save_model.setInfo_title(current.getName());
                    save_model.setInfo_description(current.getDescription());
                    save_model.setInfo_txtPrice(current.getPrice());

                    save_model.setInfo_Image(current.getImage());


                    dbHelper.insertDataIntoWishlist(save_model);

                    Toast.makeText(v.getContext(), "You selected " + current.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    holder.heartImageView.setImageResource(R.drawable.heartoff);

                    holder.heartImageView.setTag(R.drawable.heartoff);

                    Toast.makeText(v.getContext(), "You unselected " + current.getName(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtItem;
        TextView txtDescrip;
        TextView txtPrice;
        TextView txtTime;
        ImageView image_name;
        ImageView heartImageView;


        public MyViewHolder(View itemView) {
            super(itemView);

            txtItem = (TextView) itemView.findViewById(R.id.txtItemName);
            txtDescrip = (TextView) itemView.findViewById(R.id.txtWishlistDescription);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);

            image_name = (ImageView) itemView.findViewById(R.id.image_name);
            heartImageView = (ImageView) itemView.findViewById(R.id.heartImageView);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());

            Integer resource = (Integer) heartImageView.getTag();

            if (resource == R.drawable.heartoff) {

                heartImageView.setImageResource(R.drawable.heartred);
                heartImageView.setTag(R.drawable.heartred);


            } else {
                heartImageView.setImageResource(R.drawable.heartoff);

                heartImageView.setTag(R.drawable.heartoff);


            }

        }
    }
}




