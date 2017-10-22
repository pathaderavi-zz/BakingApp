package com.example.ravikiranpathade.bakingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepsActivity extends AppCompatActivity {
    @BindView(R.id.testText) TextView testText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        Intent i = new Intent();
        ArrayList<RecipeList> check  = this.getIntent().getParcelableArrayListExtra("check");
        Integer id  = this.getIntent().getIntExtra("id",0);
        //Log.d("Check Intent",);
        ButterKnife.bind(this);
        testText.setText(check.get(id).getRecipeName());
    }
}
