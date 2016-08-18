package com.ghostlabs.slowdown;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hp on 17-08-2016.
 */
public class CalculateBAC extends MainActivity {

    int storedWeight,  storedAge;
    String storedSex;
    List<Drink> newDrinksList;
    SharedPreferences preferences;
    ImageView bacImage;
    TextView bacLevel, bacComment;
    Button stopDrinking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_bac, null, false);
        frameLayout.addView(contentView, 0);

        stopDrinking = (Button)findViewById(R.id.button_stop_drinking);
        stopDrinking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent notificationIntent = new Intent();
                notificationIntent.setAction("com.custom.intent");


                PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.cancel(broadcast);
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Drinking Reminder stopped!", Snackbar.LENGTH_LONG);

                snackbar.show();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putInt("totalDrinks",0);
                editor.apply();
            }
        });

        bacComment= (TextView)findViewById(R.id.bac_comment);
        bacImage=(ImageView)findViewById(R.id.bac_image);
        bacLevel= (TextView)findViewById(R.id.bac_level);

        Intent mIntent = getIntent();
        newDrinksList = mIntent.getParcelableArrayListExtra("drinksList");
        preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE); //load the preferences
        Boolean saved = preferences.getBoolean("saved", false);
        if(saved) {

             storedWeight = preferences.getInt("age", 0);
             storedAge = preferences.getInt("weight", 0);
            storedSex = preferences.getString("sex", null);
        }
        calculate();
}
    public void calculate(){
        double BAC = 0.0;
        double elapsedTime = preferences.getLong("alarmSetTime",0) - System.currentTimeMillis();
        elapsedTime = elapsedTime/(1000*60*60);
        int totalDrinks=0;
        double genderConstant =0.0;
        int prefDrinksNumber = preferences.getInt("totalDrinks",0);
        totalDrinks += prefDrinksNumber;
        for(Drink d : newDrinksList){
            totalDrinks+= d.getStandardDrinks();
        }

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("totalDrinks",totalDrinks);
        editor.apply();
        if(storedSex.equals("Male")){
            genderConstant =0.68;
        }
        else{
            genderConstant=0.55;
        }
        BAC= (((totalDrinks*14)/ (genderConstant * storedWeight*1000))*100) - (elapsedTime*0.015);

        if(BAC>0 && BAC<=0.06){
            bacImage.setImageResource(R.drawable.ic_buzzed);
            bacLevel.setText("Your BAC level = "+ BAC);
            bacComment.setText("You are drinking responsibly. Make sure you have a safe ride home.");
        }
        else if(BAC>0.06 && BAC<=0.15){
            bacImage.setImageResource(R.drawable.ic_tipsy);
            bacLevel.setText("Your BAC level = "+ BAC);
            bacComment.setText("You are nearly or slightly above legal limit. Drink some water. Make sure you have a safe ride home.");
        }
        else  {
            bacImage.setImageResource(R.drawable.ic_drunk);
            bacLevel.setText("Your BAC level = "+ BAC);
            bacComment.setText("You are far above the legal limit and your health maybe in danger. Stop drinking and have some water. Make sure you have a safe ride home.");
        }
    }
}