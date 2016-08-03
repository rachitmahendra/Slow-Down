package com.ghostlabs.slowdown;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Hp on 04-08-2016.
 */
public class UserDetails extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    EditText age, weight;
    public UserDetails() {
        // Required empty public constructor
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        age=(EditText)findViewById(R.id.age);
        weight=(EditText)findViewById(R.id.weight);
    }
public void Save(View v){
    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
    editor.putInt("age",Integer.parseInt(age.getText().toString()));
    editor.putInt("weight",Integer.parseInt(weight.getText().toString()));
    editor.apply();
    makeToast("Data Saved");
}
    public void makeToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }
}
