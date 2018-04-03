package com.ventures.smartit.chefcreation.RestClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ventures.smartit.chefcreation.Response.Example;
import com.ventures.smartit.chefcreation.Response.selectProduct.ExampleProduct;
import com.ventures.smartit.chefcreation.Response.signInPackage.SignIn;
import com.ventures.smartit.chefcreation.Response.updateAccount.SuccessModel;
import com.ventures.smartit.chefcreation.Response.updateAccount.UpdateAccountExample;
import com.ventures.smartit.chefcreation.Response.updateUser.UpdateUser;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class RestClient1 {


    private static GitApiInterface gitApiInterface;


    //url -- http://smartit.ventures/Chefcreation/v1/updateuser
    private static String baseUrl = "http://smartit.ventures/";

    //    http://192.168.0.29/Chefcreation/v1/selectproducts
    public static GitApiInterface getClient() {


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())

                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .build();


        gitApiInterface = retrofit.create(GitApiInterface.class);

        return gitApiInterface;


    }

    public interface GitApiInterface {

        @POST("Chefcreation/v1/createlogin")
        Call<Example> signUpInterface(@Body HashMap<String, String> hashMap);

        @POST("Chefcreation/v1/userslogin")
        Call<SignIn> getLoginResponse(@Body HashMap<String, String> hashMap);

        @POST("Chefcreation/v1/selectproducts")
        Call<ExampleProduct> selectProduct(@Body HashMap<String, String> hashMap);

        @POST("Chefcreation/v1/selectuser")
        Call<SuccessModel> updateAccount(@Body HashMap<String, String> hashMap);

        @POST("Chefcreation/v1/updateuser")
        Call<UpdateUser> updateuser(@Body HashMap<String, String> hashMap);
    }
}