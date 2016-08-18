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

import java.util.Calendar;

import hu.bugadani.circlepickerlib.CirclePickerView;

/**
 * Created by Hp on 06-08-2016.
 */
public class AlarmSet extends MainActivity {
    Button setButton, cancelButton;
    CirclePickerView circlePickerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_set_alarm, null, false);
        frameLayout.addView(contentView, 0);
        setButton = (Button)findViewById(R.id.button_set);
        cancelButton = (Button)findViewById(R.id.button_cancel);
         circlePickerView = (CirclePickerView)findViewById(R.id.timer_view);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = (int) circlePickerView.getValue();
                if(time>0){
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "The reminder is set for "+time+" minutes !", Snackbar.LENGTH_LONG);

                snackbar.show();
                makeAlarm(time);}
                else {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Please select a valid time", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarm();
            }
        });

    }
    public void makeAlarm(int time){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent();
        notificationIntent.setAction("com.custom.intent");


        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
         Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, time);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),time*1000*60, broadcast);
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putLong("alarmSetTime",System.currentTimeMillis());
        editor.apply();
        }
    public void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent();
        notificationIntent.setAction("com.custom.intent");


        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(broadcast);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Reminder Cancelled successfully !", Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}