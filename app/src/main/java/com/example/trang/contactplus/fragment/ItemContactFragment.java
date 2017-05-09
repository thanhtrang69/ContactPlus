package com.example.trang.contactplus.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.trang.contactplus.R;
import com.example.trang.contactplus.activity.ContactDetailActivity;
import com.example.trang.contactplus.adapter.ContactAdapter;
import com.example.trang.contactplus.contact.Contact;
import com.example.trang.contactplus.dialog.NewContactDiaLog;
import com.example.trang.contactplus.manager.ContactManager;
import java.util.ArrayList;

/**
 * Created by Trang on 5/7/2017.
 */

public class ItemContactFragment extends Fragment implements ContactAdapter.OnClickContactListner {
    public static final String ID_CONTACT = "contact_position";
    private View view;
    private ContactManager contactManager;
    private ArrayList<Contact> arrayList;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private android.widget.SearchView searchView;
    private TextView tvAdd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_contact, container, false);
        initView();
        return view;
    }

    private void initView() {
        searchView = (android.widget.SearchView) view.findViewById(R.id.sreach_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        tvAdd = (TextView) view.findViewById(R.id.tvAdd);
        contactManager = ContactManager.getInstance();
        arrayList = contactManager.getAllContact(getActivity());
        contactAdapter = new ContactAdapter(getActivity(), arrayList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(contactAdapter);


        contactAdapter.setOnClickContactListner(this);

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.onActionViewCollapsed();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactAdapter.findContactByName(newText);
                return true;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    contactAdapter.showAllContact(contactManager.getAllContact(getActivity()));
                }
            }
        });
    }


    @Override
    public void onClickContact(int idContact) {
        Intent intent = new Intent(getActivity(), ContactDetailActivity.class);
        intent.putExtra(ID_CONTACT, idContact);
        startActivity(intent);
    }

}
