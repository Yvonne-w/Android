package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HelpIntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    HelpIntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button nextButton;
    int position = 0;
    Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        // make activity on full screen
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // check if we already had intro before
        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);

        }

        setContentView(R.layout.activity_help_intro);

        // ini views
        tabIndicator = findViewById(R.id.tab_indicator);
        nextButton = findViewById(R.id.nextButton);
        getStartedButton = findViewById(R.id.getStartedButton);
        // fill list screen
        List<ScreenItem> screenItemList = new ArrayList<>();
        screenItemList.add(new ScreenItem("Use key words", "Use key words like price, bedroom, carspace to refine your choices of preferences", R.drawable.keywords));
        screenItemList.add(new ScreenItem("Use symbols", "Use symbols like <, >, = to limit your scope and ; to separate your conditions.", R.drawable.symbols));
        screenItemList.add(new ScreenItem("Have fun!", "We also support blurry search, e.g., if you only type bel, we can still get you perfect houses in Belconnen!", R.drawable.havefun));

        // set up the viewpager2(diff between viewpager and vp2?)
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new HelpIntroViewPagerAdapter(this, screenItemList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);
        // next button click listener
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                position = screenPager.getCurrentItem();
                if (position < screenItemList.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position == screenItemList.size()-1){
                    // getstarted button and 
                    loadLastScreen();
                }
            }
        });

        // tablayout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == screenItemList.size()-1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getStartedButton.setOnClickListener(view -> {
            // open main activi
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            // next time do not pop out help screen
            savePrefsData();
            finish();
        });
    }

    private boolean restorePrefData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroOpenedBefore = preferences.getBoolean("isIntroOpened", false);
        return isIntroOpenedBefore;
    }

    private void savePrefsData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }


    private void loadLastScreen() {
        nextButton.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        getStartedButton.setVisibility(View.VISIBLE);
    }
}