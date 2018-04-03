package com.ventures.smartit.chefcreation.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.ventures.smartit.chefcreation.R;

public class Fragment_Forgot_Password extends Fragment implements View.OnClickListener {

    private EditText email_edtext;
    private Button send_button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forgotpassword_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        email_edtext = (EditText) view.findViewById(R.id.email_edtext);
        send_button = (Button) view.findViewById(R.id.send_button);


        send_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == send_button) {

        }
    }

}
