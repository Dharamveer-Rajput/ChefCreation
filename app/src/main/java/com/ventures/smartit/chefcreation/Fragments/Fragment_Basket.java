package com.ventures.smartit.chefcreation.Fragments;


import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ventures.smartit.chefcreation.Adapters.ViewPagerAdapterBasket;
import com.ventures.smartit.chefcreation.DatabaseHandler.DBHelper;
import com.ventures.smartit.chefcreation.Models.Save_model;
import com.ventures.smartit.chefcreation.R;
import com.ventures.smartit.chefcreation.UI.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import me.relex.circleindicator.CircleIndicator;

import static com.ventures.smartit.chefcreation.R.string.view;

public class Fragment_Basket extends Fragment{

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private String[] textArray;
    private ImageButton leftNav, rightNav;
    private Button basket_button_order;
    int fragNum;

    DBHelper dbHelper;
    NumberPicker mnumberPicker,mnumberpickerPrice;

    //- and + buttons declarations
    public Button minusButton;
    public Button plusButton;
    int count = 1;
    public TextView counterTextView;

    public TextView mlargeSize,msmallSize,mmediumSize;
    //Image Button
    ImageButton menuImage;
    ImageButton wishlistImage;
    ImageButton imageBasket;
    ImageButton imageAccount;

    TextView mpricespinner;

    TextView mtotalPrice;

    public static ViewPager mPager;
    //circle indicator variables
    private static int currentpage = 0;

    private static final Integer[] IMAGES =
            {R.drawable.burger,
                    R.drawable.vegtaco,
                    R.drawable.vegpasta,};

    private int dotsCount = 3;    //No of tabs  or images
    private ImageView[] dots;
    LinearLayout linearLayout;

    public CheckBox mCheckBoxChili, mCheckBoxSweet, mCheckBoxSalty, mCheckBoxExtChees;

    private ArrayList<Save_model> save_models;

    public static Fragment_Basket init(int val) {
        Fragment_Basket fragment_basket = new Fragment_Basket();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        fragment_basket.setArguments(args);

        return fragment_basket;
    }

    private void drawPageSelectionIndicators(int mPosition) {
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            if (i == mPosition)
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
            else
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            linearLayout.addView(dots[i], params);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        fragNum = getArguments() != null ? getArguments().getInt("val") : 1;
        linearLayout = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);


        return view;
    }




    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }




    private class PickerListener implements NumberPicker.OnScrollListener, NumberPicker.OnValueChangeListener
    {
        private int scrollState=0;
        @Override
        public void onScrollStateChange(NumberPicker view, int scrollState) {
            this.scrollState=scrollState;
            if (scrollState==SCROLL_STATE_IDLE){
            }
        }
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
          if(newVal==mnumberPicker.getValue()){
              mnumberpickerPrice.setValue(0);
            }
        }
    }

    String size[]={"Large","Medium","Small"};


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //mtotalPrice = (TextView) view.findViewById(R.id.totalPrice);

        dbHelper = new DBHelper(getActivity());

        final Spinner spinnersize = (Spinner) view.findViewById(R.id.spinnersize);
        mpricespinner = (TextView) view.findViewById(R.id.priceSpinner);
        spinnersize.setPrompt("Select Size");

        final ArrayAdapter<String> ia=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,size);

        ia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersize.setAdapter(ia);


        spinnersize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (parent.getId()){
                    case R.id.spinnersize:{
                        if(size[position].equals("Large")){

                            mpricespinner.setText("120");

                        }
                        if(size[position].equals("Medium")){

                            mpricespinner.setText("60");


                        }
                        if(size[position].equals("Small")){

                            mpricespinner.setText("30");


                        }

                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnersize.setPrompt("Select Size");

            }
        });

   /*     List<String> listSize = new ArrayList<String>();
        listSize.add("Large");
        listSize.add("Medium");
        listSize.add("Small");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listSize);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(dataAdapter);



        List<String> listPrice = new ArrayList<String>();
        listPrice.add("120");
        listPrice.add("60");
        listPrice.add("30");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listPrice);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrice.setAdapter(dataAdapter2);*/

//        mnumberPicker = (NumberPicker) view.findViewById(R.id.numberpicker);
//        mnumberpickerPrice = (NumberPicker) view.findViewById(R.id.numberpickerPrice);

//        final String[] values= {"Large","Small", "Medium"};
//        final String[] valuesprice= {"Rs.120","Rs.30", "Rs.60"};
//
//        mnumberPicker.setMinValue(0); //from array first value
//        mnumberPicker.setMaxValue(values.length-1);
//        mnumberPicker.setDisplayedValues(values);
//        mnumberPicker.setWrapSelectorWheel(true);
//
//        mnumberpickerPrice.setMinValue(0); //from array first value
//        mnumberpickerPrice.setMaxValue(valuesprice.length-1);
//        mnumberpickerPrice.setDisplayedValues(valuesprice);
//        mnumberpickerPrice.setWrapSelectorWheel(true);
//
//        setDividerColor(mnumberPicker, Color.RED);
//        setDividerColor(mnumberpickerPrice, Color.RED);
//
//        PickerListener listener = new PickerListener();
//
//       mnumberPicker.setOnScrollListener(listener);
//        mnumberPicker.setOnValueChangedListener(listener);

        menuImage = (ImageButton) view.findViewById(R.id.menuImage);
        wishlistImage = (ImageButton) view.findViewById(R.id.wishlistImage);
        imageBasket = (ImageButton) view.findViewById(R.id.imageBasket);
        imageAccount = (ImageButton) view.findViewById(R.id.imageAccount);
        mPager = (ViewPager) view.findViewById(R.id.viewPager4);


        //check box
        mCheckBoxChili = (CheckBox) view.findViewById(R.id.checkBoxChili);
        mCheckBoxSweet = (CheckBox) view.findViewById(R.id.checkBoxSweet);
        mCheckBoxSalty = (CheckBox) view.findViewById(R.id.checkBoxSalty);
        mCheckBoxExtChees = (CheckBox) view.findViewById(R.id.checkBoxExtChees);


        mCheckBoxChili.setOnClickListener(checkboxClickListener);
        mCheckBoxSweet.setOnClickListener(checkboxClickListener);
        mCheckBoxSalty.setOnClickListener(checkboxClickListener);
        mCheckBoxExtChees.setOnClickListener(checkboxClickListener);

//        mCheckBoxChili.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                mCheckBoxSweet.setChecked(false);
//                mCheckBoxSalty.setChecked(false);
//                mCheckBoxExtChees.setChecked(false);
//
//
//            }
//        });
//        mCheckBoxSweet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                mCheckBoxChili.setChecked(false);
//                mCheckBoxSalty.setChecked(false);
//                mCheckBoxExtChees.setChecked(false);
//
//
//            }
//        });
//        mCheckBoxSalty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                mCheckBoxChili.setChecked(false);
//                mCheckBoxSweet.setChecked(false);
//                mCheckBoxExtChees.setChecked(false);
//
//
//            }
//        });
//        mCheckBoxExtChees.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                mCheckBoxChili.setChecked(false);
//                mCheckBoxSweet.setChecked(false);
//                mCheckBoxSalty.setChecked(false);
//
//
//            }
//        });

        // Locate the ViewPager in viewpager_main.xml
        //Pager for Images
        viewPager = (ViewPager) view.findViewById(R.id.pager);

        leftNav = (ImageButton) view.findViewById(R.id.left_nav);
        rightNav = (ImageButton) view.findViewById(R.id.right_nav);
        basket_button_order = (Button) view.findViewById(R.id.basket_button_order);

        minusButton = (Button) view.findViewById(R.id.minusButton);
        plusButton = (Button) view.findViewById(R.id.plusButton);
        counterTextView = (TextView) view.findViewById(R.id.counterTextView);


        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count > 1 ? --count : 1;
                counterTextView.setText(String.valueOf(count));
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                counterTextView.setText(String.valueOf(count));
            }
        });

        basket_button_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu.mPager.setCurrentItem(3);

            }
        });


        // Images left navigation
        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    viewPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    viewPager.setCurrentItem(tab);
                }
            }
        });

        // Images right navigatin
        leftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                tab++;
                viewPager.setCurrentItem(tab);
            }
        });

        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        textArray = new String[]
                {"Burger", "Veg Taco",
                        "Veg Pasta"};

        //save_models= dbHelper.getAllValue_Wishlist();

        // Pass results to ViewPagerAdapterBasket Class
        adapter = new ViewPagerAdapterBasket(getActivity(), ImagesArray, textArray);
        viewPager.setAdapter(adapter);
        drawPageSelectionIndicators(0);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentpage = 1;
                drawPageSelectionIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    int pagecount = IMAGES.length;
                    if (currentpage == 0) {
                        viewPager.setCurrentItem(pagecount - 1, false);
                    } else if (currentpage == pagecount - 1) {
                        viewPager.setCurrentItem(0, false);
                    }
                }
            }
        });

    }


    View.OnClickListener checkboxClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            boolean checked = ((CheckBox) view).isChecked();

            if (checked) {
                String text = null;

                switch (view.getId()) {

                    case R.id.checkBoxChili:
                        text = "Chilli";
                        break;
                    case R.id.checkBoxSweet:
                        text = "Sweet";
                        break;
                    case R.id.checkBoxSalty:
                        text = "Salty";
                        break;
                    case R.id.checkBoxExtChees:
                        text = "Extra Chees";
                        break;
                }

                Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();

            }

        }
    };


}



