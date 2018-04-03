package com.ventures.smartit.chefcreation.Models;

import android.graphics.Bitmap;

import java.io.Serializable;


public class Save_model implements Serializable {

    int id;
    public String Info_title;
    public String Info_description;
    public String Info_txtPrice;
    public String Info_Image;


    public Save_model() {
    }

    public Save_model(int id, String info_title, String info_description, String info_txtPrice, String info_Image) {
        this.id = id;
        Info_title = info_title;
        Info_description = info_description;
        Info_txtPrice = info_txtPrice;
        Info_Image = info_Image;
    }


    public String getInfo_Image() {
        return Info_Image;
    }

    public Bitmap setInfo_Image(String info_Image) {
        Info_Image = info_Image;
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


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
}
