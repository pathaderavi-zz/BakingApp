package com.example.ravikiranpathade.bakingapp.singleList;

import java.util.List;

/**
 * Created by ravikiranpathade on 10/13/17.
 */

public class RecipeList {

    String id;
    String recipeName;

    public RecipeList(String id, String recipeName, List<Ingredients> ingredients, List<StepForRecipe> steps, int servings) {
        this.id = id;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.steps = steps;
        Servings = servings;
    }


    public int getServings() {
        return Servings;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<StepForRecipe> getSteps() {
        return steps;
    }

    List<Ingredients> ingredients;
    List<StepForRecipe> steps;
    int Servings;

    public RecipeList() {

    }

    public String getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }



    public RecipeList(String mId,String mRecipeName){
        this.id = mId;
        this.recipeName = mRecipeName;
    }
}
