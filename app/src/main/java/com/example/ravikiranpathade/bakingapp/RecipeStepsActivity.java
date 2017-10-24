package com.example.ravikiranpathade.bakingapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ravikiranpathade.bakingapp.adapters.RecipeStepsAdapter;
import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;

import java.util.ArrayList;
import java.util.List;

import layout.IngredientsFragment;
import layout.RecipeStepFragment;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepFragment.OnStepClickListener {

    Fragment stepFragment;
    Fragment IngredientsFragment;

    Bundle bundle = new Bundle();
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_steps);
        Intent i = new Intent();
        ArrayList<RecipeList> check = getIntent().getParcelableArrayListExtra("check");
        Integer id_s = getIntent().getIntExtra("id", 0);

        if (savedInstanceState == null) {
            stepFragment = new RecipeStepFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.stepsFragmentContainer, stepFragment).commit();
        } else {
            stepFragment = getSupportFragmentManager().getFragment(savedInstanceState, "frag_steps");
        }
        getSupportActionBar().setTitle("Recipe Steps");

        //Log.d("Fragment Status",String.valueOf(stepFragment==null)+" "+String.valueOf(IngredientsFragment==null));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Entered ","onSaveInstance");
        fragmentManager.putFragment(outState, "frag_steps", stepFragment);


    }

    //TODO onCLICK METHOD TO WORK
    @Override
    public void onStepSelected(int position) {
        if (position == 0) {

            IngredientsFragment = new IngredientsFragment();

            fragmentManager.beginTransaction().replace(R.id.stepsFragmentContainer, IngredientsFragment)
                    .addToBackStack(null).commit();

        } else {
            Toast.makeText(this, String.valueOf(position - 1), Toast.LENGTH_SHORT).show();
        }

        Log.d("Pop Stack", String.valueOf(fragmentManager.getBackStackEntryCount()));
    }

    @Override
    public void onBackPressed() {

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
            finish();
       }

        Log.d("Pop Stack", String.valueOf(fragmentManager.getBackStackEntryCount()));
    }
}
