package com.ventures.smartit.chefcreation.Fragments;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ventures.smartit.chefcreation.Adapters.MenuAdapter;
import com.ventures.smartit.chefcreation.ItemDecoration.SimpleDividerItemDecoration;
import com.ventures.smartit.chefcreation.MyInterface.ItemClickListener;
import com.ventures.smartit.chefcreation.R;
import com.ventures.smartit.chefcreation.Response.selectProduct.ExampleProduct;
import com.ventures.smartit.chefcreation.Response.selectProduct.ProductPayload;
import com.ventures.smartit.chefcreation.RestClient.RestClient1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMenu extends Fragment implements ItemClickListener {

    private List<ProductPayload> listProduct;

    private ArrayList<ProductPayload> productPayloads;
    private MenuAdapter adapter;
    private RecyclerView recyclerView;
    int fragVal;


    public static FragmentMenu init(int val) {
        FragmentMenu fragmentMenu = new FragmentMenu();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        fragmentMenu.setArguments(args);
        return fragmentMenu;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.menu_recycler_view);


        // initData();
        HashMap<String, String> stringStringHashMap = new HashMap<>();

        final RestClient1.GitApiInterface restClient = RestClient1.getClient();

        restClient.selectProduct(stringStringHashMap).enqueue(new Callback<ExampleProduct>() {
            @Override
            public void onResponse(Call<ExampleProduct> call, Response<ExampleProduct> response) {

                if (response.body().getSuccess().toString().equalsIgnoreCase("true")) {
                    listProduct = response.body().getPayload();
                    Log.e("res-->", listProduct + "");
                    productPayloads = new ArrayList<ProductPayload>();

                    for (ProductPayload obj : listProduct) {
                        productPayloads.add(obj);
                    }

                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    adapter = new MenuAdapter(getActivity(), productPayloads);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<ExampleProduct> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });


//        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        return layout;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View view, int position) {

    }
}
