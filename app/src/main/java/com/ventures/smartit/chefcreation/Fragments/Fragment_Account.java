package com.ventures.smartit.chefcreation.Fragments;


import android.support.v4.app.DialogFragment;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.app.Dialog;
import android.widget.Toast;

import com.ventures.smartit.chefcreation.AccountUpdateDialog;
import com.ventures.smartit.chefcreation.R;
import com.ventures.smartit.chefcreation.Response.updateAccount.SuccessModel;
import com.ventures.smartit.chefcreation.Response.updateUser.UpdateUser;
import com.ventures.smartit.chefcreation.RestClient.RestClient1;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.ventures.smartit.chefcreation.AccountUpdateDialog.EditNameDialogListener;
import com.ventures.smartit.chefcreation.Utility.Utility;

public class Fragment_Account extends Fragment {

    public TextView textViewaccountFirstName, textViewaccountLastName, textViewaccountEmail,
            textViewaccountFoodie;

    public static Fragment_Account init(int val) {
        Fragment_Account fragment_account = new Fragment_Account();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        fragment_account.setArguments(args);

        return fragment_account;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        Button buttonStartDialog = (Button) view.findViewById(R.id.startdialog);

        buttonStartDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountUpdateDialog accountUpdateDialog = new AccountUpdateDialog(getActivity());
                accountUpdateDialog.listener = new EditNameDialogListener() {
                    @Override
                    public void onFinishEditDialog(HashMap<String, String> response) {
                        textViewaccountFirstName.setText(response.get("fname"));
                        textViewaccountLastName.setText(response.get("lname"));
                        textViewaccountEmail.setText(response.get("email"));
                        textViewaccountFoodie.setText(response.get("foodie_no"));
                    }
                };

                accountUpdateDialog.show();
            }
        });

//        progressDialog= new ProgressDialog(getActivity());
//        progressDialog.setMessage("Displaying");
//        progressDialog.setCancelable(false);
//        progressDialog.show();


        // initData();
        final HashMap<String, String> stringStringHashMap1 = new HashMap<>();


        stringStringHashMap1.put("email",  Utility.email);

        final RestClient1.GitApiInterface restClient = RestClient1.getClient();

        restClient.updateAccount(stringStringHashMap1).enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {

                //progressDialog.dismiss();

                if (response.isSuccessful()) {

                    String firstname = response.body().getPayload().getFname();
                    String lastname = response.body().getPayload().getLname();
                    String email = response.body().getPayload().getEmail();
                    String foodieno = response.body().getPayload().getFoodieNo();

                    textViewaccountFirstName.setText(firstname);
                    textViewaccountLastName.setText(lastname);
                    textViewaccountEmail.setText(email);
                    textViewaccountFoodie.setText(foodieno);

                }
            }

            @Override
            public void onFailure(Call<SuccessModel> call, Throwable t) {

            }

        });


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewaccountFirstName = (TextView) view.findViewById(R.id.textViewaccountFirstName);
        textViewaccountLastName = (TextView) view.findViewById(R.id.textViewaccountLastName);
        textViewaccountEmail = (TextView) view.findViewById(R.id.textViewaccountEmail);
        textViewaccountFoodie = (TextView) view.findViewById(R.id.textViewaccountFoodie);

    }
}
