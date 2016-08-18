package com.ghostlabs.slowdown;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Hp on 06-08-2016.
 */
public class StartDrinking extends MainActivity {
    ImageView drinkImage;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE); //load the preferences
        Boolean saved = preferences.getBoolean("saved", false); //see if it's run before, default no
        if (!saved) {

            Intent i = new Intent(getApplicationContext(),UserDetails.class);
            startActivity(i);
            //apply
            //code for if this is the first time the app has run
        }
        else {
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_start_drinking, null, false);
        frameLayout.addView(contentView, 0);

        drinkImage = (ImageView)findViewById(R.id.drink_image);
        if(drinkImage!=null)
        drinkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AlarmSet.class);
                startActivity(i);

            }
        });}
    }
}