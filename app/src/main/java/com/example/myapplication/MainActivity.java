package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    public List<Property> properties;
    public BSTree bsTree;
    public List<Property> resultProperties;
    String temp;
    String resultApp;
    Button helpButton;
    ImageView imageView1;
    TextView pick1TV;
    private Handler handler = new Handler();
    int randomSelect1;
    String[] textSe;
    int v = 0;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helpButton = findViewById(R.id.helpButton);
//        imageView1 = findViewById(R.id.pick1ImgView);
        pick1TV = findViewById(R.id.pick1TV);

        properties = new ArrayList<>();
        bsTree = new BSTree();
        temp = "";
        resultApp = "";
        resultProperties = new ArrayList<>();

        try {
            properties = loadData("sample_properties.xml");  //load data from xml
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < properties.size(); i++) {  //build BSTree to store data
            bsTree.add(properties.get(i));
        }


        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this::onClick);

        helpButton.setOnClickListener(view -> {
            resetPrefs();
            Intent introActivity = new Intent(getApplicationContext(), HelpIntroActivity.class);
            startActivity(introActivity);
        });

        Random random = new Random();
        randomSelect1 = random.nextInt(997);
        int id1 = getResources().getIdentifier("img" + (1001 + randomSelect1), "drawable", getPackageName());

        String p1 = "img" + id1;
        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(getResources().getIdentifier("img" + (1001 + randomSelect1), "drawable", getPackageName())), 3000);
        animation.addFrame(getResources().getDrawable(getResources().getIdentifier("img" + (1002 + randomSelect1), "drawable", getPackageName())), 3000);
        animation.addFrame(getResources().getDrawable(getResources().getIdentifier("img" + (1003 + randomSelect1), "drawable", getPackageName())), 3000);
        animation.setOneShot(false);

        ImageView imageAnim = findViewById(R.id.pick1ImgView);
        imageAnim.setImageDrawable(animation);
        animation.start();
        getTextSe();
        handler.post(updateTextRunnable);

    }

    public String[] getTextSe() {
        textSe = new String[3];
        textSe[0] = properties.get(randomSelect1).toString();
        textSe[1] = properties.get(randomSelect1 + 1).toString();
        textSe[2] = properties.get(randomSelect1 + 2).toString();
        return textSe;
    }

    Runnable updateTextRunnable = new Runnable() {
        public void run() {
            if(v>2){
                v=0;
            }
            pick1TV.setText(textSe[v]);
            v++;
            handler.postDelayed(this, 3001);
        }
    };

    private void resetPrefs() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isIntroOpened", false);
        editor.apply();
    }

    public List<Property> loadData(String filename) throws IOException {
        InputStream f = getAssets().open(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        List<Property> lp = new ArrayList<>();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse(f);
            d.getDocumentElement().normalize();

            NodeList nl = d.getElementsByTagName("Property");

            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;

                    String id = element.getAttribute("id");
                    String propertyState = element.getElementsByTagName("propertyState").item(0).getTextContent();
                    String propertyType = element.getElementsByTagName("propertyType").item(0).getTextContent();
                    String price = element.getElementsByTagName("price").item(0).getTextContent();
                    String suburb = element.getElementsByTagName("suburb").item(0).getTextContent();
                    String latitude = element.getElementsByTagName("latitude").item(0).getTextContent();
                    String longitude = element.getElementsByTagName("longitude").item(0).getTextContent();
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    String numBedrooms = element.getElementsByTagName("numBedrooms").item(0).getTextContent();
                    String numBathrooms = element.getElementsByTagName("numBathrooms").item(0).getTextContent();
                    String numCarspaces = element.getElementsByTagName("numCarspaces").item(0).getTextContent();
                    String postcode = element.getElementsByTagName("postcode").item(0).getTextContent();
                    String agent = element.getElementsByTagName("agent").item(0).getTextContent();
                    String allowPets = element.getElementsByTagName("allowPets").item(0).getTextContent();
                    Property p = new Property(id, propertyState, propertyType, price, suburb, latitude, longitude, address, numBedrooms, numBathrooms, numCarspaces, postcode, agent, allowPets);

                    lp.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lp;
    }

    private void onClick(View view) {
        EditText userInput = findViewById(R.id.searchText1);
        temp = userInput.getText().toString();
        bsTree.result = new ArrayList<>();

        try {
            bsTree.inOrderTraverse(bsTree, temp.toLowerCase());
            resultProperties = bsTree.result;
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Invalid (Maybe wrong keyword)", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        try {

            if (resultProperties.size() >= 1) {
                Intent intent = new Intent(MainActivity.this, SearchResult.class);
                intent.putExtra("resultList", (Serializable) resultProperties);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_LONG).show();
            }

            userInput.getText().clear();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
}