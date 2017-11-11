package com.example.ravikiranpathade.bakingapp;

import android.preference.PreferenceManager;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import android.support.test.espresso.contrib.RecyclerViewActions;



/**
 * Created by ravikiranpathade on 11/10/17.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeListTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivity = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void checkRecyclerView(){

        onView(withId(R.id.recipeListRecycler)).check(matches(hasDescendant(withText("Nutella Pie"))));
    }
    //TODO This Test Case
    @Test
    public void checkNextActivityText(){
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        onData(ViewMatchers.withId(R.id.recipeListRecycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withText("All Ingredients")).check(matches(isDisplayed()));

    }



}
