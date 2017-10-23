package com.example.ravikiranpathade.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.singleList.Ingredients;
import com.example.ravikiranpathade.bakingapp.singleList.StepForRecipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ravikiranpathade on 10/22/17.
 */

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.RecipeStepsViewHolder> {
    
    List<StepForRecipe> stepForRecipe;
    Context context;

    public RecipeStepsAdapter(List<StepForRecipe> recipeSteps) {
        stepForRecipe = recipeSteps;
    }


    @Override
    public RecipeStepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.steps_list_item;
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttach = false;
        View view = layoutInflater.inflate(layoutId,parent,shouldAttach);
        RecipeStepsViewHolder stepsViewHolder = new RecipeStepsViewHolder(view);
        return stepsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeStepsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return stepForRecipe.size();
    }
    public void setData(List<StepForRecipe> steps){
        stepForRecipe = steps;
        notifyDataSetChanged();
    }

    class RecipeStepsViewHolder extends RecyclerView.ViewHolder{

        Context mHolderContext;

        @BindView(R.id.stepsListButton) Button stepsListButton;

        public RecipeStepsViewHolder(View itemView) {
            super(itemView);
            mHolderContext =itemView.getContext();
            ButterKnife.bind(this,itemView);
        }

        public void bind(int position){

            stepsListButton.setText(stepForRecipe.get(position).getShortDesc());

        }
    }
}
