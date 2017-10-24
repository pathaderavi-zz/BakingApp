package layout;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.adapters.RecipeStepsAdapter;
import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;
import com.example.ravikiranpathade.bakingapp.singleList.StepForRecipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepFragment extends Fragment implements RecipeStepsAdapter.RecipeStepAdapterOnClick {
    //    @BindView(R.id.ingredientsButton)
//    Button ingredientsButton;
    LinearLayoutManager layoutManager;
    List<StepForRecipe> recipeSteps;
    @BindView(R.id.stepsListRecycler)
    RecyclerView stepsRecycler;
    RecipeStepsAdapter adapter;


    public RecipeStepFragment() {
        // Required empty public constructor
    }

    //TODO FROM HERE--
    @Override
    public void onClick(int position) {
        clickListener.onStepSelected(position);
        //Toast.makeText(context,"From Fragment",Toast.LENGTH_SHORT).show();
    }

    OnStepClickListener clickListener;

    public interface OnStepClickListener {
        void onStepSelected(int position);

    }

    //TODO --TILL HERE


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        ButterKnife.bind(this, view);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        stepsRecycler.setLayoutManager(layoutManager);
        stepsRecycler.setHasFixedSize(true);
        recipeSteps = new ArrayList<>();

        // Inflate the layout for this fragment
        Intent i = new Intent();
        ArrayList<RecipeList> check = getActivity().getIntent().getParcelableArrayListExtra("check");
        Integer id = getActivity().getIntent().getIntExtra("id", 0);
        recipeSteps = check.get(id).getSteps();
        adapter = new RecipeStepsAdapter(recipeSteps, getContext(), this);

        stepsRecycler.setAdapter(adapter);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            clickListener = (OnStepClickListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
