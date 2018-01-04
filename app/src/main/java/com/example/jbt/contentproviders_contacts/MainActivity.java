package com.example.jbt.contentproviders_contacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        else
        {
            readContacts();
        }

    }

    private void readContacts() {

        //URI:
        //Content:// Package_name/ Table_name

       Cursor allContactsCursor= getContentResolver().query( ContactsContract.Contacts.CONTENT_URI , null, null, null, null);

       MyPeopleRecyclerAdapter myPeopleRecyclerAdapter= new MyPeopleRecyclerAdapter(this,allContactsCursor );

        RecyclerView recyclerView= findViewById(R.id.myRV);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView.setAdapter(myPeopleRecyclerAdapter);




    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS && grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            readContacts();
        }

    }
}
