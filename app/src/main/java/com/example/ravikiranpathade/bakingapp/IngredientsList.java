package com.example.ravikiranpathade.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.List;

import widget.IngredientsWidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsList extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int sample, int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_list);

        //views.setTextViewText(R.id.appwidget_text, String.valueOf(sample));

        Intent widgetIntent = new Intent(context, ListWidgetService.class);
        widgetIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        widgetIntent.setData(Uri.parse(widgetIntent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setTextViewText(R.id.appwidget_text,"No Recipes Selected");
        if(PreferenceManager.getDefaultSharedPreferences(context).getInt("recipe_id",-1)<0){
            views.setTextViewText(R.id.appwidget_text,"No Recipes Selected");
        }else{
            String recipename = PreferenceManager.getDefaultSharedPreferences(context).getString("recipe_name","");
            views.setTextViewText(R.id.appwidget_text,"Ingredients For "+recipename);
            views.setRemoteAdapter(R.id.list_all_ingredients_view,widgetIntent);
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static void updatePlantWidgets(Context context, AppWidgetManager appWidgetManager, int sample, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, sample, appWidgetId);
            //Log.d("Check W L","Tapped");

        }
    }
}

