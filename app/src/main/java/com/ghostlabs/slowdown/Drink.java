package com.ghostlabs.slowdown;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hp on 13-08-2016.
 */
public class Drink implements Parcelable {
    String name;
    float alcohol;
    int standardDrinks;


    public Drink(){}

    public Drink(String name ,float alcohol){
        this.alcohol = alcohol;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public double getAlcohol()
    {
        return alcohol;
    }
    public int getStandardDrinks(){
        return standardDrinks;
    }
    public void setAlcohol(float alcohol){
        this.alcohol = alcohol;
    }
    public void setStandardDrinks(int standardDrinks){
        this.standardDrinks = standardDrinks;
    }
    public void setName(String name){
        this.name = name;
    }

    protected Drink(Parcel in) {
        name = in.readString();
        alcohol = in.readFloat();
        standardDrinks = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(alcohol);
        dest.writeInt(standardDrinks);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Drink> CREATOR = new Parcelable.Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel in) {
            return new Drink(in);
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };
}