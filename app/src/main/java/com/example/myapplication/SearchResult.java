package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class SearchResult extends AppCompatActivity {
    public String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        String res = intent.getStringExtra("resultStr");
        System.out.println("check res" + " " + res);

        ListView listView = (ListView) findViewById(R.id.searcList);
        ArrayList result_arr = new ArrayList<>();
//        result_arr.add("result 1");
//        result_arr.add("result 2");


//        assert res != null;
        if (res.length() >= 1) {
            String[] tokens = res.split("Property");
            Collections.addAll(result_arr, tokens);
            listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result_arr));
        }

    }


}