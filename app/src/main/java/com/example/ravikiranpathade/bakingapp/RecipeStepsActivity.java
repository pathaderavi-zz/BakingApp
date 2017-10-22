package com.example.ravikiranpathade.bakingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import layout.RecipeStepFragment;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepFragment.OnStepClickListener{

    RecipeStepFragment stepFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        stepFragment = new RecipeStepFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.stepsFragmentContainer,stepFragment).commit();

    }

    @Override
    public void onStepSelected(int position) {

    }
}
