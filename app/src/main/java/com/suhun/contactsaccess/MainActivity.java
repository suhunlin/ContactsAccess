package com.suhun.contactsaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListView();
    }

    public void getContactFun(View view){

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