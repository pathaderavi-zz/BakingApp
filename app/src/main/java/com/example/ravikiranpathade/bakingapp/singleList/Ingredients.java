package com.example.ravikiranpathade.bakingapp.singleList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ravikiranpathade on 10/20/17.
 */

public class Ingredients implements Parcelable {

    public Ingredients(int quantity, String measure, String ingredient_name) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient_name = ingredient_name;
    }

    protected Ingredients(Parcel in) {
        quantity = in.readInt();
        measure = in.readString();
        ingredient_name = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    int quantity;
    String measure;
    String ingredient_name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient_name);
    }
}
