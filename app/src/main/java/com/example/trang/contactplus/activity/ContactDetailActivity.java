package com.example.trang.contactplus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trang.contactplus.R;
import com.example.trang.contactplus.contact.Contact;
import com.example.trang.contactplus.fragment.ItemContactFragment;
import com.example.trang.contactplus.manager.ContactManager;


import de.hdodenhof.circleimageview.CircleImageView;

public class ContactDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private CircleImageView imgPhoneNumber;
    private TextView tvName;
    private TextView tvPhoneNumber;
    private TextView tvSendMessage;
    private TextView tvLock;
    private TextView tvSrechShow;
    private ImageButton imgBtCall;
    private ContactManager manager;
    private TextView tvInComingCall;
    private int countincomingCall;
    private String numberLimited;
    private boolean checkClickLock;
    private int idContact;
    private String status;


    public ContactDetailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        initComponets();
        showContactDetail();
        loadLimitContact();
    }

    private void loadLimitContact() {
        SharedPreferences sharedPreferences = getSharedPreferences("actionLock", Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            status = sharedPreferences.getString(idContact + "value", tvLock.getText().toString());
            tvLock.setText(status);
        } else {
            Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
        }
    }

    private void limitedConttact() {
        final TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                String number = incomingNumber;
                if (state == telephonyManager.CALL_STATE_RINGING) {
                    Toast.makeText(ContactDetailActivity.this, "Phone is riging", Toast.LENGTH_SHORT).show();
                    tvInComingCall.setText("Cuộc Gọi Vừa Nhận");
                    if (number.contains(numberLimited)) {
                        return;
                    }


                }

                if (state == telephonyManager.CALL_STATE_OFFHOOK) {
                    countincomingCall++;
                    Toast.makeText(ContactDetailActivity.this, "Phone is Curreny A call", Toast.LENGTH_SHORT).show();
                    tvInComingCall.setText(countincomingCall + "Cuộc Gọi Nhỡ");
                }

            }
        };
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private void initComponets() {
        imgPhoneNumber = (CircleImageView) findViewById(R.id.img_photo_show);
        tvLock = (TextView) findViewById(R.id.tv_lock);
        tvSrechShow = (TextView) findViewById(R.id.tv_back);
        tvSendMessage = (TextView) findViewById(R.id.tv_send_message);
        imgBtCall = (ImageButton) findViewById(R.id.img_bt_call);
        tvName = (TextView) findViewById(R.id.tv_name_show);
        tvPhoneNumber = (TextView) findViewById(R.id.tv_number_show);
        tvInComingCall = (TextView) findViewById(R.id.tv_countInComingCall);

        tvSendMessage.setOnClickListener(this);
        imgBtCall.setOnClickListener(this);
        tvLock.setOnClickListener(this);
        tvSrechShow.setOnClickListener(this);


    }

    private void showContactDetail() {
        idContact = getIntent().getIntExtra(ItemContactFragment.ID_CONTACT, 0);
        if (idContact != 0) {
            manager = ContactManager.getInstance();
            manager.getAllContact(this);
            Contact contactById = manager.getContactById(idContact);
            if (contactById != null) {

                tvName.setText(contactById.getName());
                tvPhoneNumber.setText(contactById.getNumber());
                imgPhoneNumber.setImageURI(contactById.getUri());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send_message:
                Contact contactByNumberM = manager.getContactById(idContact);
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("smsto:" + contactByNumberM.getNumber()));
                startActivity(intent1);
                break;
            case R.id.img_bt_call:
                Contact contactByNumberCall = manager.getContactById(idContact);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:" + contactByNumberCall.getNumber()));
                startActivity(intent);
                break;
            case R.id.tv_lock:
                if (!checkClickLock) {
                    tvLock.setText("Bỏ Chặn");

                    Contact contact = manager.getContactById(idContact);
                    numberLimited = contact.getNumber();
                    limitedConttact();
                    SharedPreferences sharedPreferences = getSharedPreferences("actionLock", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(idContact + "value", tvLock.getText().toString());
                    editor.apply();
                    checkClickLock = true;
                } else {
                    tvLock.setText("Chặn");
                    SharedPreferences sharedPreferences = getSharedPreferences("actionLock", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(idContact + "value", tvLock.getText().toString());
                    editor.apply();
                    checkClickLock = false;
                }
                break;
            case R.id.tv_back:
                onBackPressed();
                break;
        }
    }


}
