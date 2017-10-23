package com.example.ravikiranpathade.bakingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import layout.IngredientsFragment;
import layout.RecipeStepFragment;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepFragment.OnStepClickListener{

    Fragment stepFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
    if(savedInstanceState==null) {
        stepFragment = new RecipeStepFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.stepsFragmentContainer, stepFragment).commit();
    }else{
        stepFragment = getSupportFragmentManager().getFragment(savedInstanceState,"frag_steps");
    }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState,"frag_steps",stepFragment);
    }

    //TODO onCLICK METHOD TO WORK
    @Override
    public void onStepSelected(int position) {
//        if(position==0){
//            Fragment IngredientsFragment = new IngredientsFragment();
//            getSupportFragmentManager().beginTransaction().replace(R.id.stepsFragmentContainer,IngredientsFragment).commit();
//        }
        Toast.makeText(this, "Checked", Toast.LENGTH_SHORT).show();
    }
}
