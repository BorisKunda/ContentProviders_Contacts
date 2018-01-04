package com.example.jbt.contentproviders_contacts;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jbt on 02/01/2018.
 */

public class MyPeopleRecyclerAdapter extends RecyclerView.Adapter<MyPeopleRecyclerAdapter.MyPersonVH > {
    Context context;
    Cursor contactsCursor;

    public MyPeopleRecyclerAdapter(Context context, Cursor contactsCursor) {
        this.context = context;
        this.contactsCursor = contactsCursor;
    }

    @Override
    public MyPersonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.single_item, null);
        MyPersonVH myPersonVH= new MyPersonVH(v);
        return  myPersonVH;
    }

    @Override
    public void onBindViewHolder(MyPersonVH myPersonVH, int position) {

        contactsCursor.moveToPosition(position);
        String name= contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        myPersonVH.bindText(name);

    }

    @Override
    public int getItemCount() {
        return contactsCursor.getCount();
    }

    public class MyPersonVH extends RecyclerView.ViewHolder
    {
        TextView nameTV;

        public MyPersonVH(View itemView) {
            super(itemView);
            nameTV= itemView.findViewById(R.id.nameTV);

        }

        public void bindText(String contactName)
        {
            nameTV.setText(contactName);
        }
    }
}
