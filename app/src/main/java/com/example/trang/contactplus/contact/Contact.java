package com.example.trang.contactplus.contact;

import android.net.Uri;

import java.net.URI;

/**
 * Created by Trang on 5/7/2017.
 */

public class Contact {
    private int id;
    private Uri uri;
    private String number;
    private String name;

    public Contact(int id, Uri uri, String number, String name) {
        this.id = id;
        this.uri = uri;
        this.number = number;
        this.name = name;
    }

    public Contact(Uri uri, String number, String name) {
        this.uri = uri;
        this.number = number;
        this.name = name;
    }

    public Contact(int id, String number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public Contact(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public Contact(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
