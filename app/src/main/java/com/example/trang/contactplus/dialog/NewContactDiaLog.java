package com.example.trang.contactplus.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.trang.contactplus.R;
import com.example.trang.contactplus.contact.Contact;


/**
 * Created by Trang on 5/9/2017.
 */

public class NewContactDiaLog extends Dialog implements View.OnClickListener {
    private TextView tvNameNew;
    private TextView edPhoneNumberNew;
    private TextView edNewContact;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_new_contact);
        initComponestNew();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }

    private void initComponestNew() {
        tvNameNew = (TextView) findViewById(R.id.tv_new_name);
        edPhoneNumberNew = (TextView) findViewById(R.id.tv_new_number);
        edNewContact = (TextView) findViewById(R.id.tv_new_contact_dialog);
        edNewContact.setOnClickListener(this);
    }

    public NewContactDiaLog(Context context) {

        super(context);
    }

    public NewContactDiaLog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NewContactDiaLog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_new_contact_dialog:
                Contact contact = new Contact(edPhoneNumberNew.getText().toString(), tvNameNew.getText().toString());
                dismiss();
                break;
        }
    }

}
