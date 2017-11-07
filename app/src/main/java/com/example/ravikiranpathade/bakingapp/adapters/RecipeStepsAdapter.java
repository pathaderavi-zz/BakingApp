package com.example.ravikiranpathade.bakingapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.singleList.Ingredients;
import com.example.ravikiranpathade.bakingapp.singleList.StepForRecipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ravikiranpathade on 10/22/17.
 */

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.RecipeStepsViewHolder> {

    List<StepForRecipe> stepForRecipe;

    Context context;
    RecipeStepsViewHolder stepsViewHolder;
    View view;
    int p;
    boolean tabletMode;
    public ArrayList<Integer> getTrackClick() {
        return trackClick;
    }

    ArrayList<Integer> trackClick;

    public interface RecipeStepAdapterOnClick {
        void onClick(int position);
    }

    public RecipeStepAdapterOnClick mClickHandler;

    public RecipeStepsAdapter(List<StepForRecipe> recipeSteps, Context mContext, RecipeStepAdapterOnClick mOnClick) {
        stepForRecipe = recipeSteps;
        context = mContext;
        mClickHandler = mOnClick;

    }


    @Override
    public RecipeStepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.steps_list_item;
        context = parent.getContext();
        tabletMode = parent.findViewById(R.id.stepDetailItemLandscapeTablet)!=null;
        Log.d("Tablet Mode",String.valueOf(tabletMode));
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttach = false;
        view = layoutInflater.inflate(layoutId, parent, shouldAttach);
        ButterKnife.bind(this, view);

        stepsViewHolder = new RecipeStepsViewHolder(view);

        return stepsViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecipeStepsViewHolder holder, int position) {
        //Log.d(String.valueOf(holder.getOldPosition())+" Here Holder Pos",String.valueOf(holder.getAdapterPosition()));
        //holder.itemView.setSelected();

        holder.bind(position);


    }

    @Override
    public int getItemCount() {
        return stepForRecipe.size() + 1;
    }

    public void setData(List<StepForRecipe> steps) {
        stepForRecipe = steps;
        notifyDataSetChanged();
    }

    public List<StepForRecipe> getDataRecipes() {
        return stepForRecipe;
    }

    class RecipeStepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.stepsListButton)
        Button stepsListButton;
        Context mHolderContext;


        public RecipeStepsViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            if(trackClick==null) {
                trackClick = new ArrayList<>();
                trackClick.add(0);
            }
            int ix = trackClick.get(trackClick.size()-1);

            mHolderContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
            stepsListButton.setOnClickListener(this);
        }

        public void bind(final int position) {
            if (position == 0) {
                stepsListButton.setText("All Ingredients");
               // stepsListButton.setBackgroundColor(context.getResources().getColor(R.color.ingredientsButton));

            } else {
                //TODO Change color
                //stepsListButton.setBackgroundColor(context.getResources().getColor(R.color.ingredientsButton));
                if (position == 1) {
                    stepsListButton.setText(getDataRecipes().get(position - 1).getShortDesc());
                     } else {
                    stepsListButton.setText(String.valueOf(position-1) + ". " + getDataRecipes().get(position - 1).getShortDesc());


                }
            }


        }

        //Done CLICKLISTENER
        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            mClickHandler.onClick(position);
        }
    }
}
