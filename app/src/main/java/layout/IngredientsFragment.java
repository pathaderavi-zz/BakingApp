package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.adapters.IngredientsAdapter;
import com.example.ravikiranpathade.bakingapp.singleList.Ingredients;
import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment {
    List<Ingredients> allIngredients;
    List<RecipeList> allRecipes;
    IngredientsAdapter adapter;
    LinearLayoutManager layoutManager;
    @BindView(R.id.ingredientsRecyclerView)
    RecyclerView ingredientsRecycler;
    public IngredientsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this,view);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        ingredientsRecycler.setLayoutManager(layoutManager);
        ingredientsRecycler.setHasFixedSize(true);
        allIngredients = new ArrayList<>();
        allRecipes = new ArrayList<>();

        Integer id_s = getActivity().getIntent().getIntExtra("id", 0);
        allRecipes = getActivity().getIntent().getParcelableArrayListExtra("check");
        allIngredients =allRecipes.get(id_s).getIngredients();

        adapter = new IngredientsAdapter(allIngredients);
        ingredientsRecycler.setAdapter(adapter);
        return view;
    }

}
