package com.ventures.smartit.chefcreation.Response.selectProduct;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ventures.smartit.chefcreation.Response.signInPackage.Payload;

public class ExampleProduct {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("payload")
    @Expose
    private List<ProductPayload> payload = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ProductPayload> getPayload() {
        return payload;
    }

    public void setPayload(List<ProductPayload> payload) {
        this.payload = payload;
    }

}