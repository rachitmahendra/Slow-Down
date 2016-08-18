package com.ghostlabs.slowdown;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hp on 08-08-2016.
 */
public class DrinksActivity extends MainActivity {

    private List<Drink> drinksList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DrinksAdapter mAdapter;
    Button Calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_drinks_list, null, false);
        frameLayout.addView(contentView, 0);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new DrinksAdapter(drinksList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Calculate = (Button)findViewById(R.id.button_calculate);

        prepareDrinksData();

        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CalculateBAC.class);
                i.putParcelableArrayListExtra("drinksList",(ArrayList<Drink>)drinksList);
                startActivity(i);
            }
        });
    }

    private void prepareDrinksData() {
        Drink drink = new Drink("Beer", 0.05f);
        drinksList.add(drink);

        drink = new Drink("Wine", 0.12f);
        drinksList.add(drink);

        drink = new Drink("Spirit",0.40f);
        drinksList.add(drink);



        mAdapter.notifyDataSetChanged();
    }

}