package com.ventures.smartit.chefcreation;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.ventures.smartit.chefcreation.Response.updateAccount.SuccessModel;
import com.ventures.smartit.chefcreation.Response.updateUser.UpdateUser;
import com.ventures.smartit.chefcreation.RestClient.RestClient1;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountUpdateDialog extends Dialog implements View.OnClickListener, OnEditorActionListener {


    public static EditText mfirstName, mlastName, memail, mfoodieNo;
    public EditNameDialogListener listener;
    Context context;

    public AccountUpdateDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public interface EditNameDialogListener {
        void onFinishEditDialog(HashMap<String, String> response);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account_update_dialog);

        Button customDialog_Save = (Button) findViewById(R.id.dialogsave);
        mfirstName = (EditText) findViewById(R.id.ed_first_name);
        mlastName = (EditText) findViewById(R.id.ed_last_name);
        memail = (EditText) findViewById(R.id.ed_email);
        mfoodieNo = (EditText) findViewById(R.id.ed_foodie);
        customDialog_Save.setOnClickListener(this);


        // Show soft keyboard automatically
        mfirstName.requestFocus();
        mfirstName.setOnEditorActionListener(this);

        final HashMap<String, String> stringStringHashMap1 = new HashMap<>();


        stringStringHashMap1.put("email", "mca2012veer@gmail.com");

        final RestClient1.GitApiInterface restClient = RestClient1.getClient();

        restClient.updateAccount(stringStringHashMap1).enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {


                if (response.isSuccessful()) {

                    String firstname = response.body().getPayload().getFname();
                    String lastname = response.body().getPayload().getLname();
                    String email = response.body().getPayload().getEmail();
                    String foodieno = response.body().getPayload().getFoodieNo();

                    mfirstName.setText(firstname);
                    mlastName.setText(lastname);
                    memail.setText(email);
                    mfoodieNo.setText(foodieno);

                }
            }

            @Override
            public void onFailure(Call<SuccessModel> call, Throwable t) {

            }

        });

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListener activity = (EditNameDialogListener) context;
            this.dismiss();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Saving Data");
        progressDialog.show();

        final HashMap<String, String> stringStringHashMap1 = new HashMap<>();

        stringStringHashMap1.put("id", "6");
        stringStringHashMap1.put("fname", mfirstName.getText().toString());
        stringStringHashMap1.put("lname", mlastName.getText().toString());
        stringStringHashMap1.put("email", memail.getText().toString());
        stringStringHashMap1.put("foodie_no", mfoodieNo.getText().toString());

        final RestClient1.GitApiInterface restClient = RestClient1.getClient();

        restClient.updateuser(stringStringHashMap1).enqueue(new Callback<UpdateUser>() {
            @Override
            public void onResponse(Call<UpdateUser> call, Response<UpdateUser> response) {

                progressDialog.dismiss();
                listener.onFinishEditDialog(stringStringHashMap1);
                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                dismiss();
            }

            @Override
            public void onFailure(Call<UpdateUser> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

    }

}



