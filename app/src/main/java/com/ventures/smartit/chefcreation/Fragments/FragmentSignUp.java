package com.ventures.smartit.chefcreation.Fragments;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ventures.smartit.chefcreation.R;
import com.ventures.smartit.chefcreation.Response.Example;
import com.ventures.smartit.chefcreation.RestClient.RestClient1;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSignUp extends Fragment implements View.OnClickListener {

    //Declaration of views
    private Button btnSignUp;

    //Edit Text
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextFoodieNo;
    private EditText editTextPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialization of views
        btnSignUp = (Button) view.findViewById(R.id.btnSignUp);

        //Edit Text
        editTextFirstName = (EditText) view.findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) view.findViewById(R.id.editTextLastName);
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        editTextFoodieNo = (EditText) view.findViewById(R.id.editTextFoodieNo);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);

        btnSignUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btnSignUp) {
            validateForm();
        }
    }

    public void validateForm() {
        final String email = editTextEmail.getText().toString();
        final String pass = editTextPassword.getText().toString();
        if (editTextFirstName.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "Please enter your name", Toast.LENGTH_LONG).show();
            editTextFirstName.setError("Please enter your name");
        } else if (editTextLastName.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "Please enter your last name", Toast.LENGTH_LONG).show();
            editTextLastName.setError("Please enter your last name");
        } else if (!isValidEmail(email)) {
            editTextEmail.setError("Invalid Email");
        } else if (editTextFoodieNo.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "Please enter your foodie no.", Toast.LENGTH_LONG).show();
            editTextFoodieNo.setError("Please enter your foodie no.");
        } else if (!isValidPassword(pass)) {
            editTextPassword.setError("Invalid Password");
        } else {

            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Saving Data");
            progressDialog.show();

            HashMap<String, String> stringStringHashMap = new HashMap<>();

            stringStringHashMap.put("fname", editTextFirstName.getText().toString());
            stringStringHashMap.put("lname", editTextLastName.getText().toString());
            stringStringHashMap.put("email", editTextEmail.getText().toString());
            stringStringHashMap.put("foodie_no", editTextFoodieNo.getText().toString());
            stringStringHashMap.put("password", editTextPassword.getText().toString());


            final RestClient1.GitApiInterface restClient = RestClient1.getClient();

            restClient.signUpInterface(stringStringHashMap).enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });


            FragmentSignIn fragmentSignIn = new FragmentSignIn();
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction().add(R.id.my_layout, fragmentSignIn, "Sign_In").commit();
        }
    }


    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;

    }
}


