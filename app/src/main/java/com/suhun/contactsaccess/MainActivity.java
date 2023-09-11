package com.suhun.contactsaccess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();
    private ListView showContact;
    private SimpleAdapter simpleAdapter;
    private ArrayList<HashMap<String, String>> data = new ArrayList<>();
    private String[] from = {"itemKey"};
    private int[] to = {R.id.contact_item};
    private ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListView();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            //user have already agree access contact
            contentResolver = getContentResolver();
        }else{
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //user have already agree access contact
                contentResolver = getContentResolver();
            }else{
                finish();
            }
        }
    }

    public void getContactFun(View view){
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            try {
                String namePresent = name.replace(name.charAt(1), '0');
                String phonePresent = phone.substring(0, 7);
                String result = String.format("%s:%s", namePresent, phonePresent);
                HashMap<String, String> contant = new HashMap<>();
                contant.put(from[0], result);
                data.add(contant);
                simpleAdapter.notifyDataSetChanged();
            }catch (Exception e){

            }
        }
        cursor.close();
    }

    private void initListView(){
        showContact = findViewById(R.id.lid_contactDatas);
        simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        showContact.setAdapter(simpleAdapter);
        //test ListView
//        HashMap<String, String> test = new HashMap<>();
//        test.put(from[0], "Suhun is Handsome");
//        data.add(test);
//        simpleAdapter.notifyDataSetChanged();
    }
}