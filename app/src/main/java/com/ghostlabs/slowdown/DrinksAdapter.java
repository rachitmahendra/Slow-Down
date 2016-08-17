package com.ghostlabs.slowdown;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.MyViewHolder> {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private List<Drink> drinksList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView drinkName, drinkCount;
        ImageView drinkImage, plus,minus;

        public MyViewHolder(View view) {
            super(view);
            drinkName = (TextView) view.findViewById(R.id.drink_name);
            drinkCount = (TextView) view.findViewById(R.id.drink_count);
            drinkImage= (ImageView) view.findViewById(R.id.drink_image);
            plus=(ImageView)view.findViewById(R.id.plus_button);
            minus=(ImageView)view.findViewById(R.id.minus_button);
        }
    }


    public DrinksAdapter(List<Drink> drinksList) {
        this.drinksList = drinksList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drink_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Drink drink = drinksList.get(position);
        holder.drinkName.setText(drink.getName());
        holder.drinkCount.setText( String.valueOf(drink.getStandardDrinks()));
         int drawableId=holder.drinkImage.getContext().getResources().getIdentifier(drink.getName().toLowerCase(), "drawable", holder.drinkImage.getContext().getPackageName());
        holder.drinkImage.setImageResource(drawableId);

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drink.standardDrinks ++;
                holder.drinkCount.setText(String.valueOf(drink.getStandardDrinks()));
                SharedPreferences preferences = holder.plus.getContext().getSharedPreferences(MY_PREFS_NAME,Context.MODE_PRIVATE);
                float alcohol = preferences.getFloat("alcohol",0);
                SharedPreferences.Editor editor = holder.plus.getContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
                editor.putFloat("alcohol",alcohol+drink.alcohol);
                editor.apply();


            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.drinkCount.getText().toString().equals("0")) {
                    drink.standardDrinks--;
                    holder.drinkCount.setText(String.valueOf(drink.getStandardDrinks()));
                    SharedPreferences preferences = holder.minus.getContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
                    float alcohol = preferences.getFloat("alcohol", 0);
                    SharedPreferences.Editor editor = holder.minus.getContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
                    editor.putFloat("alcohol", alcohol - drink.alcohol);
                    editor.apply();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return drinksList.size();
    }
}