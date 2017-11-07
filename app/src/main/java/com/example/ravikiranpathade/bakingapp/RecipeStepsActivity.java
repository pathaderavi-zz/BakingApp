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
import layout.StepDetailFragment;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepFragment.OnStepClickListener {

    Fragment stepFragment;
    Fragment IngredientsFragment;

    Bundle bundle = new Bundle();
    FragmentManager fragmentManager;
    StepDetailFragment stepDetailFrag;
    long seekTime;
    boolean tabletPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_steps);
        if (findViewById(R.id.tablet_detail_activity) != null) {
            tabletPane = true;
            //TODO Implement Tablet fragments
            Intent i = new Intent();
            ArrayList<RecipeList> check = getIntent().getParcelableArrayListExtra("check");
            Integer id_s = getIntent().getIntExtra("id", 0);
            fragmentManager = getSupportFragmentManager();
            if (savedInstanceState == null) {
                stepFragment = new RecipeStepFragment();

                fragmentManager.beginTransaction().add(R.id.steps_detail_tablet, new IngredientsFragment()).commit();
            } else {
                //TODO
                fragmentManager.getFragment(savedInstanceState, "frag_steps");
            }

        } else {
            tabletPane = false;
            Intent i = new Intent();
            ArrayList<RecipeList> check = getIntent().getParcelableArrayListExtra("check");
            Integer id_s = getIntent().getIntExtra("id", 0);
            fragmentManager = getSupportFragmentManager();
            if (savedInstanceState == null) {
                stepFragment = new RecipeStepFragment();

                fragmentManager.beginTransaction().add(R.id.stepsFragmentContainer, stepFragment).commit();
            } else {
                //TODO
                fragmentManager.getFragment(savedInstanceState, "frag_steps");
            }
            //TODO Change everytime the fragment Changes


//        if(stepDetailFrag!=null && stepDetailFrag.getAdapter().getExoplayer!=null){
//            stepDetailFrag.getAdapter().getExoplayer.seekTo(seekTime);
//        }
        }
        getSupportActionBar().setTitle("Recipe Steps");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!tabletPane) {
            fragmentManager.putFragment(outState, "frag_steps", fragmentManager.findFragmentById(R.id.stepsFragmentContainer));

        } else {
            if (tabletPane) {
                if (stepDetailFrag != null && stepDetailFrag.getAdapter().getExoplayer != null) {
                    stepDetailFrag.getAdapter().getExoplayer.stop();
                    stepDetailFrag.getAdapter().getExoplayer.release();
                }
            }
            //TODO Check tapped button id and store
            //TODO add the fragment
            fragmentManager.putFragment(outState, "frag_steps", fragmentManager.findFragmentById(R.id.steps_detail_tablet));

        }
    }


    @Override
    public void onStepSelected(int position) {
        //TODO Implemet onStepSelected change fragment on tap
        if (!tabletPane) {
            if (position == 0) {

                IngredientsFragment = new IngredientsFragment();

                fragmentManager.beginTransaction().replace(R.id.stepsFragmentContainer, IngredientsFragment)
                        .addToBackStack(null).commit();


            } else {
                //Toast.makeText(this, String.valueOf(position - 1), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("id_Step", position - 1);
                stepDetailFrag = new StepDetailFragment();
                stepDetailFrag.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.stepsFragmentContainer, stepDetailFrag)
                        .addToBackStack(null).commit();
                //Change Button Color


            }
        } else {

            if (position == 0) {

                IngredientsFragment = new IngredientsFragment();

                fragmentManager.beginTransaction().replace(R.id.steps_detail_tablet, IngredientsFragment)
                        .addToBackStack(null).commit();


            } else {
                //Toast.makeText(this, String.valueOf(position - 1), Toast.LENGTH_SHORT).show();
                if (stepDetailFrag != null && stepDetailFrag.getAdapter().getExoplayer != null) {
                    stepDetailFrag.getAdapter().getExoplayer.stop();
                    stepDetailFrag.getAdapter().getExoplayer.release();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("id_Step", position - 1);
                stepDetailFrag = new StepDetailFragment();
                stepDetailFrag.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.steps_detail_tablet, stepDetailFrag)
                        .addToBackStack(null).commit();
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    public void onBackPressed() {
        if (!tabletPane) {
            if (stepDetailFrag != null && stepDetailFrag.getAdapter().getExoplayer != null) {
                stepDetailFrag.getAdapter().getExoplayer.stop();
                stepDetailFrag.getAdapter().getExoplayer.release();
            }
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
            } else {
                super.onBackPressed();


                finish();
            }

        }
        //super.onBackPressed();
        finish();
    }
}
