package com.example.ravikiranpathade.bakingapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravikiranpathade.bakingapp.IngredientsList;
import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.RecipeStepsActivity;
import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import widget.IngredientsWidgetService;

/**
 * Created by ravikiranpathade on 10/13/17.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {


    Context mHolderContext;
    Context context;
    //private RecipeOnClickListener recipeOnClickListener;
    public List<RecipeList> mRecipeList;

//    public interface RecipeOnClickListener {
//        void onClick(String id);
//    }
//
//    public void setRecipeOnClickListener(RecipeOnClickListener rListener) {
//        recipeOnClickListener = rListener;
//    }

    public RecipeListAdapter(List<RecipeList> rDetails) {
        mRecipeList = rDetails;

    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layourId = R.layout.recipe_list_item;
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachImmediately = false;

        View view = layoutInflater.inflate(layourId, parent, shouldAttachImmediately);

        RecipeListViewHolder rHolder = new RecipeListViewHolder(view);
        return rHolder;
    }

    @Override
    public void onBindViewHolder(final RecipeListViewHolder holder, int position) {


        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(mRecipeList!=null){
        return mRecipeList.size();}else{
            return 0;
        }
    }

    //Done SetData function
    public void setData(List<RecipeList> mRList) {
        mRecipeList = mRList;
        notifyDataSetChanged();
    }

    class RecipeListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipeListButton)
        Button mRecipeListButton;
        @BindView(R.id.imageRecipe)
        ImageView recipeImage;



        public RecipeListViewHolder(View itemView) {
            super(itemView);
            mHolderContext = itemView.getContext();
            ButterKnife.bind(this, itemView);


        }

        public void bind(final int id) {

            mRecipeListButton.setText(mRecipeList.get(id).getRecipeName());
            if(mRecipeList.get(id).getImageUrl().length()>5){
                recipeImage.setVisibility(View.VISIBLE);
                Picasso.with(context).load(mRecipeList.get(id).getImageUrl()).into(recipeImage);
            }
            mRecipeListButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent newIntent = new Intent(context, RecipeStepsActivity.class);

                    SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = s.edit();
                    editor.putInt("recipe_id",id);
                    editor.putString("recipe_name",mRecipeList.get(id).getRecipeName());
                    editor.apply();

                    Intent newI = new Intent(context, IngredientsWidgetService.class);
                    newI.setAction(IngredientsWidgetService.ACTION_SEE_INGREDIENTS);
                    context.startService(newI);

                    newIntent.putParcelableArrayListExtra("check", (ArrayList<? extends Parcelable>) mRecipeList);
                    newIntent.putExtra("id", getAdapterPosition());
                    context.startActivity(newIntent);

                }
            });
        }


    }
}
