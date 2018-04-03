package com.ventures.smartit.chefcreation.DatabaseHandler;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ventures.smartit.chefcreation.Models.Contact;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Student.db";

    // Contacts table name
    private static final String TABLE_NAME = "student_table";

    // Contacts Table Columns names
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "SURNAME";
    private static final String COL_4 = "MARKS";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT,MARKS INTEGER");

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);


    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Student
    public boolean insertData(String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2, name); //  Name
        values.put(COL_3, surname); //  surname
        values.put(COL_4, marks); //  marks

        // Inserting Row
        long result = db.insert(TABLE_NAME, null, values);

        if (result == -1)
            return false;
        else
            return true;
    }

    // Getting single Student
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_1,
                        COL_2, COL_3}, COL_1 + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        // return contact
        return contact;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Select All Query
        Cursor res = db.rawQuery("SELECT  * FROM " + TABLE_NAME, null);

        return res;

    }


    // Getting All Student
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));
                contact.set_surname(cursor.getString(2));
                contact.set_marks(Integer.parseInt(cursor.getString(2)));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return Student list
        return contactList;
    }

    // Updating single Student
    public int updateStudent(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2, contact.get_name());
        values.put(COL_3, contact.get_surname());
        values.put(COL_4, contact.get_marks());


        // updating row
        return db.update(TABLE_NAME, values, COL_1 + " = ?",
                new String[]{String.valueOf(contact.get_id())});
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + " = ?",
                new String[]{String.valueOf(contact.get_id())});
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

//    public boolean insertData(String name, String surname, String marks) {
//
//
//    }
}
