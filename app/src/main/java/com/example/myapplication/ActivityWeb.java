package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityWeb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        Intent intent = getIntent();
        Property pw = (Property) intent.getSerializableExtra("PropertyWeb");

        TextView textView = findViewById(R.id.textViewWeb);
        assert pw != null;
        textView.setText(pw.description());

        ImageView iv = findViewById(R.id.imgWeb);
        int id = pw.getId();
        String picName = "img" + id;
        iv.setImageResource(getResources().getIdentifier(picName, "drawable", getPackageName()));


    }
}