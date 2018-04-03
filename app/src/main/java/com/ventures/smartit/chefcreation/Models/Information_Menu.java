package com.ventures.smartit.chefcreation.Models;


import android.os.Parcelable;

import java.io.Serializable;

public class Information_Menu {

    public String Info_title;
    public String Info_description;
    public String Info_txtPrice;
    public String Info_txtTime;
    public int Info_imageId;

    public String getInfo_txtTime() {
        return Info_txtTime;
    }

    public void setInfo_txtTime(String info_txtTime) {
        Info_txtTime = info_txtTime;
    }


    private boolean favourite = false;

    public String getInfo_title() {
        return Info_title;
    }

    public void setInfo_title(String info_title) {
        Info_title = info_title;
    }

    public String getInfo_description() {
        return Info_description;
    }

    public void setInfo_description(String info_description) {
        Info_description = info_description;
    }

    public String getInfo_txtPrice() {
        return Info_txtPrice;
    }

    public void setInfo_txtPrice(String info_txtPrice) {
        Info_txtPrice = info_txtPrice;
    }

    public int getInfo_imageId() {
        return Info_imageId;
    }

    public void setInfo_imageId(int info_imageId) {
        Info_imageId = info_imageId;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
