package com.ventures.smartit.chefcreation.Response.signInPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignIn {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("payload")
    @Expose
    private Payload payload;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

}
