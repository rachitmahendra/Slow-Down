package com.ghostlabs.slowdown;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hp on 08-08-2016.
 */
public class DrinksActivity extends MainActivity {

    private List<Drink> drinksList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DrinksAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new DrinksAdapter(drinksList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareDrinksData();
    }

    private void prepareDrinksData() {
        Drink drink = new Drink("Beer", 0.07);
        drinksList.add(drink);

        drink = new Drink("Liquor", 0.07);
        drinksList.add(drink);



        mAdapter.notifyDataSetChanged();
    }
}