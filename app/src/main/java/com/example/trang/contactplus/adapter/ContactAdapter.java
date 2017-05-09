package com.example.trang.contactplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trang.contactplus.R;
import com.example.trang.contactplus.contact.Contact;

import java.util.ArrayList;

/**
 * Created by Trang on 5/7/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {
    private Context mContext;
    private ArrayList<Contact> contactList;
    private OnClickContactListner onClickContactListner;

    public void setOnClickContactListner(OnClickContactListner clickContactListner) {
        this.onClickContactListner = clickContactListner;
    }

    public ContactAdapter(Context mContext, ArrayList<Contact> contactList) {
        this.mContext = mContext;
        this.contactList = contactList;
    }

    @Override
    public ContactAdapter.ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*View view = View.inflate(mContext, R.layout.item_contact, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT

        );
        view.setLayoutParams(params);*/
        return new ContactHolder(LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(final ContactAdapter.ContactHolder holder, final int position) {
        holder.tvName.setText(contactList.get(position).getName());
        holder.tvNumber.setText(contactList.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return (contactList != null) ? contactList.size() : 0;
    }

    public void findContactByName(String newText) {
        ArrayList<Contact> contactFindList = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getName().trim().toLowerCase().contains(newText.trim().toLowerCase())) {
                contactFindList.add(contact);
            }
        }
        contactList = contactFindList;
        notifyDataSetChanged();
    }


    public void showAllContact(ArrayList<Contact> allContact) {
        contactList = allContact;
        notifyDataSetChanged();
    }


    public class ContactHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvNumber;

        public ContactHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickContactListner.onClickContact(contactList.get(getAdapterPosition()).getId());
                }
            });

        }
    }

    public interface OnClickContactListner {
        void onClickContact(int idContact);
    }

}
