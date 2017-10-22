package layout;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.adapters.RecipeListAdapter;
import com.example.ravikiranpathade.bakingapp.singleList.Ingredients;
import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;
import com.example.ravikiranpathade.bakingapp.utils.QueryUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.v7.recyclerview.R.attr.layoutManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment {
    List<RecipeList> recipeLists;
    RecipeListAdapter recipeListAdapter;
    View view;
    @BindView(R.id.recipeListRecycler)
    RecyclerView recipeListRecycler;
    LinearLayoutManager linearLayoutManager;
    Bundle bundle;
    boolean tablet;
    Unbinder bind;
    public RecipeListFragment() {
        // Required empty public constructor
    }

    //@BindView(R.id.recipeListButton)Button recipeListButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        bundle = savedInstanceState;


        view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        tablet = (view.findViewById(R.id.fragmentContainerTablet)!=null);
        ButterKnife.bind(this, view);
        if(!tablet) {
            recipeListRecycler.setLayoutManager(linearLayoutManager);
            recipeListRecycler.setHasFixedSize(true);
        }else{
            recipeListRecycler.setLayoutManager(new GridLayoutManager(view.getContext(),3));
        }

        recipeLists = new ArrayList<>();
        if(bundle!=null) {
            //Log.d("Fetch Executed There",bundle.toString());
            recipeLists = savedInstanceState.getParcelableArrayList("wholeArray");
            recipeListAdapter = new RecipeListAdapter(recipeLists);
            recipeListRecycler.setAdapter(recipeListAdapter);

        }
        //Log.d("Fetch Execute Check",String.valueOf(bundle==null));
        if(bundle==null) {

            new FetchRecipes().execute();
        }
        //recipeListButton.setText("Fragment Set");
        return view;
    }

    public class FetchRecipes extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String res = null;
            try {
               // Log.d("Check Bundle",String.valueOf(bundle==null));

                    res = QueryUtils.makeHTTPrequest(QueryUtils.createURL(QueryUtils.QUERY_URL));


            } catch (IOException e) {
                e.printStackTrace();
            }

                recipeLists = QueryUtils.extractRecipes(res);


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recipeListAdapter = new RecipeListAdapter(recipeLists);
            recipeListRecycler.setAdapter(recipeListAdapter);


            //bundle.putParcelableArrayList("recipes", (ArrayList<? extends Parcelable>) recipeLists);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("wholeArray", (ArrayList<? extends Parcelable>) recipeLists);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //bind.unbind();
    }
}
