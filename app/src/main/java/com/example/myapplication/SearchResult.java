package com.example.myapplication;

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

import java.util.List;


public class SearchResult extends AppCompatActivity {
    public String res;
    ListView listView;
    String mTitle[];
    String mDescription[];
//    int images[] = {R.drawable.img1001, R.drawable.img1002, R.drawable.img1003, R.drawable.img1004, R.drawable.img1005};
    int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        listView = findViewById(R.id.listView);

        Intent intent = getIntent();
        String res = intent.getStringExtra("resultStr");
        System.out.println(res);

        List<Property> resultList = (List<Property>) intent.getSerializableExtra("resultList");

        if (res.length() >= 1) {
            mDescription = res.split("Property");

            int n = mDescription.length;
            mTitle = new String[n];
            images = new int[n];

            for(int j = 0;j<n-1;j++){
                mTitle[j] = String.valueOf(j);
                int id = resultList.get(j).getId();
                String picName = "img"+id;

                int id2 = getResources().getIdentifier(picName, "drawable", getPackageName());
                images[j] = id2;
            }



        }



        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Toast.makeText(SearchResult.this, "check Description", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {
        public String res;
        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.customlayout, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View custom = layoutInflater.inflate(R.layout.customlayout, parent, false);
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
//
//public class SearchResult extends AppCompatActivity {
//    public String res;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_result);
//
//        Intent intent = getIntent();
//        String res = intent.getStringExtra("resultStr");
//        System.out.println("check res" + " " + res);
//
//        ListView listView = (ListView) findViewById(R.id.listView);
//        ArrayList result_arr = new ArrayList<>();
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i==0){
//                    Toast.makeText(SearchResult.this,"Check Description",Toast.LENGTH_LONG);
//                }
//            }
//        });
//
////        if (res.length() >= 1) {
////            String[] tokens = res.split("Property");
////            Collections.addAll(result_arr, tokens);
////            listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result_arr));
////        }
//
//
//    }
//
//
//
//}
