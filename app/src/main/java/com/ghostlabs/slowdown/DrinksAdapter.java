package com.ghostlabs.slowdown;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.MyViewHolder> {

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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Drink drink = drinksList.get(position);
        holder.drinkName.setText(drink.getName());
        holder.drinkCount.setText(drink.getStandardDrinks());
         int drawableId=holder.drinkImage.getContext().getResources().getIdentifier(drink.getName(), "drawable", holder.drinkImage.getContext().getPackageName());
        holder.drinkImage.setImageResource(drawableId);
    }

    @Override
    public int getItemCount() {
        return drinksList.size();
    }
}