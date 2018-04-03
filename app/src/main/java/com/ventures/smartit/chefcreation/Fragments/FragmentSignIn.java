package com.ventures.smartit.chefcreation.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.ventures.smartit.chefcreation.Models.User;
import com.ventures.smartit.chefcreation.R;
import com.ventures.smartit.chefcreation.Response.signInPackage.SignIn;
import com.ventures.smartit.chefcreation.RestClient.RestClient1;
import com.ventures.smartit.chefcreation.UI.Menu;
import com.ventures.smartit.chefcreation.Utility.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.ventures.smartit.chefcreation.R.drawable.email;


public class FragmentSignIn extends Fragment implements View.OnClickListener {

    //Declarations of Views
    private EditText edEmail;
    private EditText edPassword;
    private Button ButtonForSignIn;
    private TextView textViewForgotPassword;
    private CheckBox show_hide_password;

    LoginButton fb_login_btn;
    CallbackManager callbackManager;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    ProgressDialog progress;

    private String facebook_id, f_name, l_name, gender, full_name, email_id;

    SharedPreferences preferences;

    //private TextView username;

//    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
//        @Override
//        public void onSuccess(LoginResult loginResult) {
//            progress.show();
//            Profile profile = Profile.getCurrentProfile();
//
//            if(profile!=null)
//            {
//                facebook_id = profile.getId();
//                f_name=profile.getFirstName();
//                l_name=profile.getLastName();
//                full_name=profile.getName();
//            }
//            // App code
//            GraphRequest request = GraphRequest.newMeRequest(
//                    loginResult.getAccessToken(),
//                    new GraphRequest.GraphJSONObjectCallback() {
//                        @Override
//                        public void onCompleted(JSONObject object, GraphResponse response) {
//                            Log.v("LoginActivity", response.toString());
//
//                            // Application code
//                            try {
//                                String email_id = object.getString("email");
//                                String name=object.getString("name");
//
//                                long fb_id=object.getLong("id"); //use this for logout
//                                //Start new activity or use this info in your project.
//
//                                Intent i=new Intent(getActivity(), Menu.class);
//                                i.putExtra("type","facebook");
//                                i.putExtra("facebook_id",facebook_id);
//                                i.putExtra("f_name",f_name);
//                                i.putExtra("l_name",l_name);
//                                i.putExtra("full_name",full_name);
//                                i.putExtra("email_id",email_id);
//                                i.putExtra("gender",gender);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            // 01/31/1980 format
//                        }
//                    });
//            Bundle parameters = new Bundle();
//            parameters.putString("fields", "id,name,email,gender, birthday");
//            request.setParameters(parameters);
//            request.executeAsync();
//
//
//            Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_LONG).show();
//            Log.d("FB", "access token got.");
//            //send request and call graph api
//        }
//
//        @Override
//        public void onCancel() {
//            Toast.makeText(getActivity(), "Login Cancel", Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onError(FacebookException error) {
//            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    };

    public FragmentSignIn() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //for facebook
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayMessage(newProfile);
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edEmail = (EditText) view.findViewById(R.id.edEmail);
        edPassword = (EditText) view.findViewById(R.id.edPassword);
        ButtonForSignIn = (Button) view.findViewById(R.id.ButtonForSignIn);

        ButtonForSignIn.setOnClickListener(this);

        show_hide_password = (CheckBox) view.findViewById(R.id.show_hide_password);

        textViewForgotPassword = (TextView) view.findViewById(R.id.textViewForgotPassword);

        textViewForgotPassword.setOnClickListener(this);
        show_hide_password.setOnClickListener(this);

//        Google Login Code
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        GoogleLoginFragment googleLoginFragment;
//        googleLoginFragment = new GoogleLoginFragment();
//        fragmentTransaction.replace(R.id.my_layout, googleLoginFragment);
//        fragmentTransaction.commit();


        //username = (TextView) view.findViewById(R.id.username);

        //Facebook Login Code
        fb_login_btn = (LoginButton) view.findViewById(R.id.fb_login_btn);

        progress = new ProgressDialog(getActivity());
        progress.setMessage(getResources().getString(R.string.please_wait_facebooklogin));
        progress.setIndeterminate(false);
        progress.setCancelable(false);

        //fb_login_btn.setReadPermissions("email");
        // If using in a fragment
        fb_login_btn.setFragment(this);

        // fb_login_btn.registerCallback(callbackManager, callback);
        facebook_id = f_name = l_name = gender = full_name = email_id = "";


        //FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                progress.show();
                Profile profile = Profile.getCurrentProfile();

                if (profile != null) {
                    facebook_id = profile.getId();
                    f_name = profile.getFirstName();
                    l_name = profile.getLastName();
                    full_name = profile.getName();
                }
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code
                                try {
                                    email_id = object.getString("email");
//                                    String name=object.getString("name");

                                    long fb_id = object.getLong("id"); //use this for logout

                                    //Start new activity or use this info in your project.
                                    Intent i = new Intent(getActivity(), Menu.class);
                                    i.putExtra("type", "facebook");
                                    i.putExtra("facebook_id", facebook_id);
                                    i.putExtra("f_name", f_name);
                                    i.putExtra("l_name", l_name);
                                    i.putExtra("full_name", full_name);
                                    i.putExtra("email_id", email_id);
                                    i.putExtra("gender", gender);

                                    progress.dismiss();
                                    startActivity(i);
                                    getActivity().finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // 01/31/1980 format
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();


                Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_LONG).show();
                Log.d("FB", "access token got.");
                //send request and call graph api
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Login Cancel", Toast.LENGTH_LONG).show();
                progress.dismiss();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                progress.dismiss();

            }
        });


//        fb_login_btn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                Toast.makeText(getActivity(),"Login Success",Toast.LENGTH_LONG).show();
//                Log.d("FB", "access token got.");
//                //send request and call graph api
//            }
//            @Override
//            public void onCancel() {
//                Toast.makeText(getActivity(), "Login Cancel", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void displayMessage(Profile profile) {
        if (profile != null) {
            edEmail.setText(profile.getName());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayMessage(profile);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ButtonForSignIn:
                // validateForm();


                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Logging");
                progressDialog.show();

                HashMap<String, String> stringStringHashMap = new HashMap<>();

                stringStringHashMap.put("email", edEmail.getText().toString());
                stringStringHashMap.put("password", edPassword.getText().toString());

                final RestClient1.GitApiInterface restClient = RestClient1.getClient();

                restClient.getLoginResponse(stringStringHashMap).enqueue(new Callback<SignIn>() {
                    @Override
                    public void onResponse(Call<SignIn> call, Response<SignIn> response) {
                        progressDialog.dismiss();

                        if (response.body().getSuccess().equalsIgnoreCase("true")) {
                            User user = new User(getActivity());
                            user.setEmail(String.valueOf(email));

                            Intent i = new Intent(getActivity(), Menu.class);
                            i.putExtra("email", response.body().getPayload().getEmail());
                            i.putExtra("password", response.body().getPayload().getPassword());
                            Utility.email=response.body().getPayload().getEmail();
                            startActivity(i);
                        } else
                            Toast.makeText(getActivity(), "You are not Authenticated", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<SignIn> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();

                    }

                });

                break;


            case R.id.textViewForgotPassword:
                Fragment_Forgot_Password fragment_forgot_password = new Fragment_Forgot_Password();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.my_layout, fragment_forgot_password, "Forgot Fragment").commit();
                break;

            case R.id.show_hide_password:
                show_hide_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!isChecked) {
                            //show password
                            edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        } else {
                            edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                    }
                });
        }
    }

    private void validateForm() {
        final String email = edEmail.getText().toString();
        final String pass = edPassword.getText().toString();
        if (!isValidEmail(email)) {
            edEmail.setError("Invalid Email");
        } else if (!isValidPassword(pass)) {
            edPassword.setError("Invalid Password");
        } else {
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
