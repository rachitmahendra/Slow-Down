package com.ghostlabs.slowdown;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

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
                makeToast(String.valueOf(time));
            }
        });

    }
}