package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.List;


public class SearchResult extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    private static MyAdapter adapter;
//    String res;
    ListView listView;
    String[] mTitle;
    String[] mDescription;
    int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        listView = findViewById(R.id.listView);
        try {
            SearchResult.adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = getIntent();
//        String res = intent.getStringExtra("resultStr");

        List<Property> resultList = (List<Property>) intent.getSerializableExtra("resultList");
//        System.out.println("check resultlist: "+resultList);

        assert resultList != null;
        if (resultList.size() >= 1) {
//            String[] tokens = res.split("Property");

            int n = resultList.size();
            mTitle = new String[n];
            mDescription = new String[n];
            images = new int[n];

            for (int j = 0; j < n - 1; j++) {
                mDescription[j] = resultList.get(j).toString();

                double amount = resultList.get(j).getPrice();
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                String finalAmount = formatter.format(amount);
                mTitle[j] = finalAmount;

                int id = resultList.get(j).getId();
                String picName = "img" + id;
                int id2 = getResources().getIdentifier(picName, "drawable", getPackageName());
                images[j] = id2;
            }
        }

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Toast.makeText(SearchResult.this, "Clicked", Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(SearchResult.this,ActivityWeb.class);
//                intent.putExtra("PropertyStr",resultList.get(position).toString());
                intent.putExtra("PropertyWeb",resultList.get(position));
                startActivity(intent);
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {
        public String res;
        Context context;
        String[] rTitle;
        String[] rDescription;
        int[] rImgs;

        MyAdapter(Context c, String[] title, String[] description, int[] imgs) {
            super(c, R.layout.customlayout, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View custom = layoutInflater.inflate(R.layout.customlayout, parent, false);
            ImageView images = custom.findViewById(R.id.image);
            TextView myTitle = custom.findViewById(R.id.textView1);
            TextView myDescription = custom.findViewById(R.id.textView2);

            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);

            return custom;
        }
    }
}

