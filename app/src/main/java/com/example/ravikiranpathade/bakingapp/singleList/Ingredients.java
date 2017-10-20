package com.example.ravikiranpathade.bakingapp.singleList;

/**
 * Created by ravikiranpathade on 10/20/17.
 */

public class Ingredients {

    public Ingredients(int quantity, String measure, String ingredient_name) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient_name = ingredient_name;
    }

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

}
