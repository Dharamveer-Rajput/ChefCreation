package com.ventures.smartit.chefcreation.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.ventures.smartit.chefcreation.Models.Information_Menu_RecyclerView;
import com.ventures.smartit.chefcreation.Models.Save_model;
import com.ventures.smartit.chefcreation.R;
import com.ventures.smartit.chefcreation.Utility.Utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

import static android.provider.MediaStore.Images.Thumbnails.IMAGE_ID;
import static com.ventures.smartit.chefcreation.DatabaseHandler.Constants.IMAGE;


public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;
    Bitmap downloadedImage;

    Context context;

    public DBHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constants.CREATE_TB);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TB_NAME);
        onCreate(db);
    }


    public Boolean insertDataIntoWishlist(Save_model save_model) throws SQLiteException {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.ITEM_ID, save_model.getId());
        values.put(Constants.IMAGE_NAME, save_model.getInfo_title());
        values.put(Constants.DESCRIPTION, save_model.getInfo_description());
        values.put(Constants.PRICE, save_model.getInfo_txtPrice());


        values.put(Constants.IMAGE, save_model.getInfo_Image());


        long result = sqLiteDatabase.insert(Constants.TB_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;

    }


    public ArrayList<Save_model> getAllValue_Wishlist() {

        ArrayList<Save_model> save_models = new ArrayList<Save_model>();
        sqLiteDatabase = this.getReadableDatabase();
        String[] columns = {Constants.IMAGE_NAME, Constants.DESCRIPTION, Constants.PRICE};
        Cursor cursor = sqLiteDatabase.query(true, Constants.TB_NAME, columns, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Save_model save_model = new Save_model();
                save_model.setInfo_title(cursor.getString(cursor.getColumnIndex(Constants.IMAGE_NAME)));
                save_model.setInfo_description(cursor.getString(cursor.getColumnIndex(Constants.DESCRIPTION)));
                save_model.setInfo_txtPrice(cursor.getString(cursor.getColumnIndex(Constants.PRICE)));

                save_models.add(save_model);
            } while (cursor.moveToNext());

        }
        return save_models;
    }
}