package com.ventures.smartit.chefcreation.Models;


public class Contact {

    //private variables
    int _id;
    String _name;
    String _surname;
    int _marks;

    // Empty constructor
    public Contact() {

    }

    // constructor
    public Contact(int id, String name, String surname, int marks) {
        this._id = id;
        this._name = name;
        this._surname = surname;
        this._marks = marks;
    }

    public Contact(String ravi, String kumar, String s) {
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_surname() {
        return _surname;
    }

    public void set_surname(String _surname) {
        this._surname = _surname;
    }

    public int get_marks() {
        return _marks;
    }

    public void set_marks(int _marks) {
        this._marks = _marks;
    }
}
