package com.ghostlabs.slowdown;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Hp on 04-08-2016.
 */
public class UserDetails extends MainActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    EditText age, weight;
    RadioGroup radioGroup;
    RadioButton radioButton, radioMale, radioFemale, radioOther;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_user_details, null, false);
        frameLayout.addView(contentView, 0);
        age=(EditText)findViewById(R.id.age);
        weight=(EditText)findViewById(R.id.weight);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioMale= (RadioButton)findViewById(R.id.maleRadio);
        radioFemale = (RadioButton)findViewById(R.id.femaleRadio);
        radioOther = (RadioButton)findViewById(R.id.otherRadio);

        SharedPreferences runCheck = getSharedPreferences("saved", 0); //load the preferences
        Boolean saved = runCheck.getBoolean("saved", false);
         if(saved) {
            SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            int storedWeight = preferences.getInt("age", 0);
            int storedAge = preferences.getInt("weight", 0);
            String storedSex = preferences.getString("sex", null);
            age.setText(String.valueOf(storedAge));
            weight.setText(String.valueOf(storedWeight));
            switch(storedSex){
                case "Male": radioMale.setChecked(true);
                    break;
                case "Female" : radioFemale.setChecked(true);
                    break;
                case "Other": radioOther.setChecked(true);
                    break;
            }
        }
    }

public void Save(View v){
    // get selected radio button from radioGroup
    int selectedId = radioGroup.getCheckedRadioButtonId();

    // find the radiobutton by returned id
    radioButton = (RadioButton) findViewById(selectedId);

    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
    editor.putInt("age",Integer.parseInt(age.getText().toString()));
    editor.putInt("weight",Integer.parseInt(weight.getText().toString()));
    editor.putString("sex",radioButton.getText().toString());
    editor.apply();
    SharedPreferences settings = getSharedPreferences("saved", 0);
    SharedPreferences.Editor edit = settings.edit();
    edit.putBoolean("saved", true); //set to has run
    edit.apply();
    makeToast("Data Saved");
    Intent i = new Intent(this,MainActivity.class);
    startActivity(i);
}
    public void makeToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }
}
