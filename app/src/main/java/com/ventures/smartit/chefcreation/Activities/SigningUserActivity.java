package com.ventures.smartit.chefcreation.Activities;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Button;

import com.ventures.smartit.chefcreation.Fragments.FragmentSignIn;
import com.ventures.smartit.chefcreation.Fragments.FragmentSignUp;
import com.ventures.smartit.chefcreation.R;


public class SigningUserActivity extends FragmentActivity implements View.OnClickListener {

    //Buttons
    private Button ButtonSignin;
    private Button ButtonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signing_user_activity);

        if (getIntent() != null) {
            int frg = getIntent().getIntExtra("Frag", 0);
            if (frg == 1) {
                FragmentSignUp fragment = new FragmentSignUp();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.my_layout, fragment, "Sign_Up").commit();
            } else {
                FragmentSignIn fragmentSignIn = new FragmentSignIn();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().add(R.id.my_layout, fragmentSignIn, "Sign_In").commit();

            }
        }

        //Find id for buttons
        ButtonSignin = (Button) findViewById(R.id.ButtonSignin);
        ButtonSignup = (Button) findViewById(R.id.ButtonSignup);

        //set click listeners on  buttons
        ButtonSignin.setOnClickListener(this);
        ButtonSignup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == ButtonSignin) {

            ButtonSignup.setTextColor(Color.WHITE);
            ButtonSignin.setTextColor(Color.RED);
            FragmentSignIn fragmentSignIn = new FragmentSignIn();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.my_layout, fragmentSignIn, "Sign_In").commit();


        } else if (v == ButtonSignup) {
            ButtonSignin.setTextColor(Color.WHITE);
            ButtonSignup.setTextColor(Color.RED);
            FragmentSignUp fragment = new FragmentSignUp();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.my_layout, fragment, "Sign_Up").commit();
        }

    }
}
