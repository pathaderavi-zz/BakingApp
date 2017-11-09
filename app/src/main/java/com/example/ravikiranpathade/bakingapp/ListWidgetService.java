package com.example.ravikiranpathade.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.ravikiranpathade.bakingapp.singleList.Ingredients;
import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;
import com.example.ravikiranpathade.bakingapp.utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravikiranpathade on 11/9/17.
 */

public class ListWidgetService extends RemoteViewsService {



    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new ListRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{
        private Context mContext;
        private int mAppWidgetId;
        List<Ingredients> allIngredients;
        List<RecipeList> allRecipies;

        public ListRemoteViewsFactory(Context applicationContext, Intent intent) {
            mContext = applicationContext;
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        public ArrayList<Ingredients> getIngredients(){
             SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            String response =  s.getString("wholeResponse","");
            allRecipies = new ArrayList<>();
            allRecipies = QueryUtils.extractRecipes(response);
            allIngredients = new ArrayList<>();
            allIngredients = allRecipies.get(s.getInt("recipe_id",-1)).getIngredients();

            return (ArrayList<Ingredients>) allIngredients;

        }

        @Override
        public void onCreate() {
            getIngredients();
        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return allIngredients.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews rv = new RemoteViews(mContext.getPackageName(),R.layout.widget_list_item);

            if(allIngredients.size()!=0){

                    String name = allIngredients.get(position).getIngredient_name();
                    String measure = allIngredients.get(position).getQuantity()+" "+allIngredients.get(position).getMeasure()+" Of ";

                    rv.setTextViewText(R.id.ingredientWidgetDetails,measure+name);
                    Log.d("Check Remote",name+"measure");

            }
            return rv;
        }


        @Override
        public RemoteViews getLoadingView() {
          return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
