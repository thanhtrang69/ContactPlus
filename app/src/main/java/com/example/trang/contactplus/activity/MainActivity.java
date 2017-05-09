package com.example.trang.contactplus.activity;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.trang.contactplus.R;
import com.example.trang.contactplus.fragment.ItemContactFragment;

public class MainActivity extends AppCompatActivity {
    private ItemContactFragment itemContactFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragmentContact();
    }


    public void showFragmentContact() {
        if (itemContactFragment == null) {
            itemContactFragment = new ItemContactFragment();
        }
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, itemContactFragment)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }


}
