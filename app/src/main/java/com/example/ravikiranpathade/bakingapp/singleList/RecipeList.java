package com.example.ravikiranpathade.bakingapp.singleList;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ravikiranpathade on 10/13/17.
 */

public class RecipeList implements Parcelable {

    String id;
    String recipeName;

    public String getImageUrl() {
        return imageUrl;
    }

    String imageUrl;

    public RecipeList(String id, String recipeName, List<Ingredients> ingredients, List<StepForRecipe> steps, int servings,String img) {
        this.id = id;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.steps = steps;
        Servings = servings;
        imageUrl = img;
    }


    protected RecipeList(Parcel in) {
        id = in.readString();
        recipeName = in.readString();
        ingredients = in.createTypedArrayList(Ingredients.CREATOR);
        steps = in.createTypedArrayList(StepForRecipe.CREATOR);
        Servings = in.readInt();
        imageUrl = in.readString();
    }

    public static final Creator<RecipeList> CREATOR = new Creator<RecipeList>() {
        @Override
        public RecipeList createFromParcel(Parcel in) {
            return new RecipeList(in);
        }

        @Override
        public RecipeList[] newArray(int size) {
            return new RecipeList[size];
        }
    };

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


    public RecipeList(String mId, String mRecipeName) {
        this.id = mId;
        this.recipeName = mRecipeName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(recipeName);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(steps);
        dest.writeInt(getServings());
        dest.writeString(imageUrl);

    }
}
