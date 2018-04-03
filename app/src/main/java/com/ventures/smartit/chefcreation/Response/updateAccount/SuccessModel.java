package com.ventures.smartit.chefcreation.Response.updateAccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ventures.smartit.chefcreation.Response.signInPackage.Payload;

/**
 * Created by Dell on 16-06-2017.
 */

public class SuccessModel {


    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("payload")
    @Expose
    private UpdateAccountExample updateAccountExample;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public UpdateAccountExample getPayload() {
        return updateAccountExample;
    }

    public void setPayload(UpdateAccountExample updateAccountExample) {
        this.updateAccountExample = updateAccountExample;
    }

}

