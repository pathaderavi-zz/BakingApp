package com.example.ravikiranpathade.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.ravikiranpathade.bakingapp.singleList.StepForRecipe;

/**
 * Created by ravikiranpathade on 10/23/17.
 */

public class RecipeStepListAdapter extends ArrayAdapter<StepForRecipe> {
    public RecipeStepListAdapter(@NonNull Context context,  @NonNull StepForRecipe[] objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(position == 0){

        }else{

        }
        return super.getView(position, convertView, parent);
    }
}
