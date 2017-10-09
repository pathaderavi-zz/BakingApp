package com.example.ravikiranpathade.bakingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ravikiranpathade.bakingapp.utils.QueryUtils;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FetchRecipes().execute();

    }

    public class FetchRecipes extends AsyncTask<String , Void, Void>{
        @Override
        protected Void doInBackground(String... params) {
                    try {
            QueryUtils.makeHTTPrequest(QueryUtils.createURL(QueryUtils.QUERY_URL));
        } catch (IOException e) {
            e.printStackTrace();
        }
            return null;
        }
    }
}
