package com.ghostlabs.slowdown;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * Created by Hp on 17-08-2016.
 */
public class CalculateBAC extends MainActivity {

    int storedWeight,  storedAge;
    String storedSex;
    List<Drink> newDrinksList;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_drinks_list, null, false);
        frameLayout.addView(contentView, 0);

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
        if(storedSex.equals("Male")){
            genderConstant =0.68;
        }
        else{
            genderConstant=0.55;
        }
        BAC= (((totalDrinks*14)/ (genderConstant * storedWeight*1000))*100) - (elapsedTime*0.015);
    }
}