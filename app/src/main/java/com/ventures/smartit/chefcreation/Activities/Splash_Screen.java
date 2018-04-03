package com.ventures.smartit.chefcreation.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ventures.smartit.chefcreation.Models.User;
import com.ventures.smartit.chefcreation.R;
import com.ventures.smartit.chefcreation.UI.Menu;

public class Splash_Screen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        final User user = new User(Splash_Screen.this);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                if (user.getEmail() != "") {
                    Intent i = new Intent(Splash_Screen.this, Menu.class);
                    i.putExtra("email", user.getEmail());
                    startActivity(i);

                    // close this activity
                    finish();
                } else {
                    Intent i = new Intent(Splash_Screen.this, MainActivity.class);
                    startActivity(i);

                }
            }

        }, SPLASH_TIME_OUT);
    }

}