package com.example.ravikiranpathade.bakingapp;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.singleList.Ingredients;
import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;
import com.example.ravikiranpathade.bakingapp.singleList.StepForRecipe;
import com.example.ravikiranpathade.bakingapp.utils.QueryUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import layout.RecipeListFragment;

public class MainActivity extends AppCompatActivity {

    List<RecipeList> recipeLists;
   // @BindView(R.id.sampleText) TextView sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        ButterKnife.bind(this);
//        sample.setText("Something like this");

        RecipeListFragment recipeList = new RecipeListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, recipeList)
                .commit();

        new FetchRecipes().execute();

    }

    public class FetchRecipes extends AsyncTask<String , Void, Void>{
        @Override
        protected Void doInBackground(String... params) {
            String res =null ;
                    try {
                     res = QueryUtils.makeHTTPrequest(QueryUtils.createURL(QueryUtils.QUERY_URL));
        } catch (IOException e) {
            e.printStackTrace();
        }

            recipeLists = new ArrayList<>();
            recipeLists = QueryUtils.extractRecipes(res);

            for(int i = 0 ; i< recipeLists.size();i++){
                RecipeList recipeList = recipeLists.get(i);
                Log.d("ID Here "+recipeList.getId(),recipeList.getRecipeName());
                List<Ingredients> sample = recipeList.getIngredients();
//                for(int j = 0; j < sample.size() ; j++){
//                    Ingredients ig = sample.get(j);
//                    Log.d(String.valueOf(ig.getQuantity()+ig.getQuantity()),ig.getIngredient_name());
//                }
//                List<StepForRecipe> sampleS = recipeList.getSteps();
//                for(int j = 0; j < sampleS.size() ; j++){
//                    StepForRecipe stepForRecipes = sampleS.get(j);
//                    Log.d(stepForRecipes.getDesc(),stepForRecipes.getVideoUrl());
//                }
            }
            return null;
        }
    }
}
