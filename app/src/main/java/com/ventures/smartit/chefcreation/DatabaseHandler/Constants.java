package com.ventures.smartit.chefcreation.DatabaseHandler;

public class Constants {

    //DB PROPERTIES
    static final String DB_NAME = "Chef_Creatioerns2";
    static final String TB_NAME = "Menu_Table";
    static final int DB_VERSION = 2;


    //Columns
    static final String ROW_ID = "id";
    static final String ITEM_ID = "id";
    static final String IMAGE_NAME = "item"; //Name of burger
    static final String DESCRIPTION = "description";
    static final String PRICE = "price";
    static final String IMAGE = "image";

    static final String CREATE_TB = "CREATE TABLE Menu_Table(id INTEGER ,item_id INTEGER PRIMARY KEY,item TEXT NOT NULL, description TEXT NOT NULL,price TEXT NOT NULL,image BLOB NOT NULL);";

}
