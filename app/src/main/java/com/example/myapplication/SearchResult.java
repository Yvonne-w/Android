package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ListView listView = (ListView)findViewById(R.id.searcList);
        ArrayList result_arr = new ArrayList();
        result_arr.add("result 1");
        result_arr.add("result 2");
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result_arr));
    }
}