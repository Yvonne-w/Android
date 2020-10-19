package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    public List<Property> properties;
    public BSTree bsTree;
    public List<Property> resultProperties;
    String temp;
    String resultApp;
    Button helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helpButton = findViewById(R.id.helpButton);

//        ImageView bg = findViewById(R.id.background);
//        bg.setImageResource(getResources().getIdentifier("img1278", "drawable", getPackageName()));

        List<Property> properties = new ArrayList<>();
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


        Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this::onClick);

        helpButton.setOnClickListener(view -> {
            resetPrefs();
            Intent introActivity = new Intent(getApplicationContext(), HelpIntroActivity.class);
            startActivity(introActivity);
        });
    }

    private void resetPrefs() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isIntroOpened", false);
        editor.commit();
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
        bsTree.inOrderTraverse(bsTree, temp.toLowerCase());
//        resultApp = bsTree.resultStr;
        resultProperties = bsTree.result;

        Intent intent = new Intent(MainActivity.this, SearchResult.class);
//        intent.putExtra("resultStr", resultApp);
        intent.putExtra("resultList", (Serializable) resultProperties);
        startActivity(intent);
        userInput.getText().clear();
    }
}