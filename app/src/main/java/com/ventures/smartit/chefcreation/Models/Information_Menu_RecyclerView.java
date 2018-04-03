package com.ventures.smartit.chefcreation.Models;

/**
 * Created by Dell on 22-05-2017.
 */

public class Information_Menu_RecyclerView {

    String Info_item;
    String Info_description;
    String Info_price;

    public Information_Menu_RecyclerView(String info_item, String info_description, String info_price) {
        Info_item = info_item;
        Info_description = info_description;
        Info_price = info_price;
    }

    public String getInfo_item() {
        return Info_item;
    }

    public void setInfo_item(String info_item) {
        Info_item = info_item;
    }

    public String getInfo_description() {
        return Info_description;
    }

    public void setInfo_description(String info_description) {
        Info_description = info_description;
    }

    public String getInfo_price() {
        return Info_price;
    }

    public void setInfo_price(String info_price) {
        Info_price = info_price;
    }
}
