package com.ventures.smartit.chefcreation.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ventures.smartit.chefcreation.R;

import java.util.ArrayList;

public class ViewPagerAdapterBasket extends PagerAdapter {

    //Declare varibales
    Context context;
    ArrayList<Integer> IMAGES;
    String[] textArray;
    LayoutInflater inflater;

    public ViewPagerAdapterBasket(Context context, ArrayList<Integer> IMAGES, String[] textArray) {
        this.context = context;
        this.IMAGES = IMAGES;
        this.textArray = textArray;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //return view.equals(object);
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //Declare variables
        ImageView imgItems;
        TextView text;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        // Locate the ImageView in viewpager_item.xml
        imgItems = (ImageView) itemView.findViewById(R.id.image);
        text = (TextView) itemView.findViewById(R.id.text);

        // Capture position and set to the ImageView
        imgItems.setImageResource(IMAGES.get(position));
        text.setText(textArray[position]);

        // Add viewpager_item.xml to ViewPager
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        //container.removeView((RelativeLayout) object);
        container.invalidate();

    }
}
