package com.example.ravikiranpathade.bakingapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.example.ravikiranpathade.bakingapp.singleList.Ingredients;
import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;
import com.example.ravikiranpathade.bakingapp.singleList.StepForRecipe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravikiranpathade on 10/9/17.
 */

public class QueryUtils {

    public static final String QUERY_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";


    public QueryUtils() {
    }

    public static URL createURL(String URL){
        //URL = QUERY_URL;
        URL url = null;

        try{
            url = new URL(URL);

        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if(inputStream!=null){
            InputStreamReader isReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            BufferedReader bufferdReader = new BufferedReader(isReader);

            String line ;



            while((line = bufferdReader.readLine())!=null){
                //Log.d("Line "," Entered");

                output.append(line);
               // line = bufferdReader.readLine();

            }
       // Log.d("Reponse ", output.toString());


        }
        return output.toString();
    }

    public static String makeHTTPrequest(URL url) throws IOException {
        String response = null;
        Log.d("URL",url.toString());
        if(url == null){
            return response;
        }
        HttpURLConnection connection = null;

        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if(connection.getResponseCode()==200){
                inputStream = connection.getInputStream();
                response = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }

       // Log.d("Response ",response);
        return response;
    }


    public static List<RecipeList> extractRecipes(String json){
        List<RecipeList> recipeListList = new ArrayList<>();
        List<Ingredients> ingredientsList =null;
        List<StepForRecipe> stepForRecipeList=null;

        if(json==null ||json.isEmpty()){
            return null;
        }

        try{

            JSONArray baseArray = new JSONArray(json);

            //Done Convert JSONArray -- Error Here


            for(int i = 0 ; i < baseArray.length() ; i++){
                ingredientsList = new ArrayList<>();
                stepForRecipeList = new ArrayList<>();
                JSONObject baseObject = baseArray.getJSONObject(i);
               // Log.d("Check",sample.toString());
                String id = baseObject.getString("id");
                String name = baseObject.getString("name");

                JSONArray baseArrayIngredients = baseObject.getJSONArray("ingredients");
                //Log.d("Base Ing",baseArrayIngredients.toString());
                for(int j = 0 ;j < baseArrayIngredients.length() ; j++){

                    JSONObject current = baseArrayIngredients.getJSONObject(j);

                    int quantity = current.getInt("quantity");
                    String measure = current.getString("measure");
                    String ingredient = current.getString("ingredient");

                    ingredientsList.add(new Ingredients(quantity,measure,ingredient));
                    //Log.d(quantity+" "+measure,ingredient);
                }

                JSONArray baseArraySteps = baseObject.getJSONArray("steps");
                Log.d("Base Steps",baseArraySteps.toString());
                for(int j = 0 ;j < baseArraySteps.length() ; j++){

                    JSONObject current = baseArraySteps.getJSONObject(j);
                    //Log.d("Base Ing Insiede",current.toString());
                    int id_step = current.getInt("id");
                    String shortDescription = current.getString("shortDescription");
                    String description = current.getString("description");
                    String videoURL = current.getString("videoURL");
                    String thumbnailURL = current.getString("thumbnailURL");
                    stepForRecipeList.add(new StepForRecipe(id_step,shortDescription,description,videoURL,thumbnailURL));
                    //Log.d(quantity+" "+measure,ingredient);
                }
                int servings = baseObject.getInt("servings");
                String imageUrl = baseObject.getString("image");
//                if(ingredientsList!=null && stepForRecipeList!=null) {
//                    Log.d("Check Before" + String.valueOf(ingredientsList.size()), String.valueOf(stepForRecipeList.size()));
//                }
                recipeListList.add(new RecipeList(id,name,ingredientsList,stepForRecipeList,servings,imageUrl));



                //Log.d("Check After"+String.valueOf(ingredientsList.size()),String.valueOf(recipeListList.get(i).getIngredients().size()));
            }



        }catch (Exception e){
            e.printStackTrace();
        }
        
        return recipeListList;
    }


}


