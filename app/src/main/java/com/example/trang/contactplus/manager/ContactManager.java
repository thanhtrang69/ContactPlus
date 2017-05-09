package com.example.trang.contactplus.manager;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.trang.contactplus.App;
import com.example.trang.contactplus.contact.Contact;

import java.util.ArrayList;

/**
 * Created by Trang on 5/7/2017.
 */

public class ContactManager {
    private static ContactManager instance;
    private ArrayList<Contact> contactList;


    public static ContactManager getInstance() {
        if (instance == null) {
            instance = new ContactManager();
        }
        return instance;
    }

    public ArrayList<Contact> getAllContact(Context mContext) {
        if (contactList != null) {
            return contactList;
        }
        Cursor cursor = mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                new String[]{
                        ContactsContract.Data._ID
                        , ContactsContract.Data.PHOTO_URI
                        , ContactsContract.CommonDataKinds.Phone.NUMBER
                        , ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                }, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor == null) {
            return new ArrayList<>();
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            return new ArrayList<>();
        }
        ArrayList<Contact> arrayList = new ArrayList<>();
        cursor.moveToFirst();
        int indextId = cursor.getColumnIndex(ContactsContract.Data._ID);
        int indextNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int indextName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(indextId);
            String number = cursor.getString(indextNumber);
            String name = cursor.getString(indextName);
            if (number == null || number.length() < 8) {
                cursor.moveToNext();
                continue;
            }

        arrayList.add(new Contact(id, number, name));
        cursor.moveToNext();

    }
        cursor.close();
    contactList =new ArrayList<>();
        contactList.addAll(arrayList);
        return arrayList;
}

    public Contact getContactById(int id) {
        for (Contact contact : contactList) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }


}
