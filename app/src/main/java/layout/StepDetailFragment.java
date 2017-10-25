package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;
import com.example.ravikiranpathade.bakingapp.singleList.StepForRecipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetailFragment extends Fragment {

    List<StepForRecipe> recipeStepDetails;
    @BindView(R.id.step_detail_desc)
    TextView stepDetail;
    @BindView(R.id.previous)
    Button previous;
    @BindView(R.id.next)
    Button next;
    Integer id_s;
    int step_id;

    public StepDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<RecipeList> check = getActivity().getIntent().getParcelableArrayListExtra("check");

        if (id_s == null) {
            id_s = getActivity().getIntent().getIntExtra("id", 0);
        } else {

        }

        recipeStepDetails = check.get(id_s).getSteps();

        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);
        ButterKnife.bind(this, view);

        step_id = getArguments().getInt("id_Step");

        Log.d("Check Previous Next", recipeStepDetails.get(step_id).getShortDesc());

            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    step_id = step_id - 1;
                    if (step_id > 0) {
                        previous.setVisibility(View.VISIBLE);
                        stepDetail.setText(recipeStepDetails.get(step_id).getDesc());
                        Log.d("Previous Button", String.valueOf(step_id));
                    } else {

                        previous.setVisibility(View.GONE);
                    }
                    if (step_id < recipeStepDetails.size() - 1) {
                        stepDetail.setText(recipeStepDetails.get(step_id).getDesc());
                        next.setVisibility(View.VISIBLE);
                    } else {
                        next.setVisibility(View.GONE);
                    }

                }

            });

        //Log.d("Button ID "+String.valueOf(step_id),String.valueOf(recipeStepDetails.size()));

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    step_id = step_id + 1;

                    if (step_id < recipeStepDetails.size()-1) {
                        next.setVisibility(View.VISIBLE);
                        stepDetail.setText(recipeStepDetails.get(step_id).getDesc());
                        //Log.d("Next Button", String.valueOf(step_id));
                    } else {
                        next.setVisibility(View.GONE);
                    }
                    if (step_id > 0) {
                        stepDetail.setText(recipeStepDetails.get(step_id).getDesc());
                        previous.setVisibility(View.VISIBLE);
                    } else {
                        previous.setVisibility(View.GONE);
                    }

                }

            });

        if (!(step_id > 0)) {
            previous.setVisibility(View.GONE);

        }
        if(!(step_id <recipeStepDetails.size()-1)){
            //else {
                next.setVisibility(View.GONE);

        }

        //Log.d("Check Previous Next",recipeStepDetails.get(step_id).getShortDesc());
        stepDetail.setText(recipeStepDetails.get(step_id).getDesc());
        // Inflate the layout for this fragment
        return view;
    }

}
