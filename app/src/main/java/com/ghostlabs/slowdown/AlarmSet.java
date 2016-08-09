package com.ghostlabs.slowdown;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import hu.bugadani.circlepickerlib.CirclePickerView;

/**
 * Created by Hp on 06-08-2016.
 */
public class AlarmSet extends MainActivity {
    Button nextButton;
    CirclePickerView circlePickerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_set_alarm, null, false);
        frameLayout.addView(contentView, 0);
        nextButton = (Button)findViewById(R.id.button_next);
         circlePickerView = (CirclePickerView)findViewById(R.id.timer_view);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = (int) circlePickerView.getValue();
                makeToast("You have selected " + String.valueOf(time));
                makeAlarm(time);
            }
        });

    }
    public void makeAlarm(int time){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent();
        notificationIntent.setAction("com.custom.intent");


        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
         Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, time);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),10000, broadcast);
        }
}