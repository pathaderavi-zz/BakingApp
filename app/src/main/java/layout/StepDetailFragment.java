package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public StepDetailFragment() {
        // Required empty public constructor
    }

 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<RecipeList> check = getActivity().getIntent().getParcelableArrayListExtra("check");
        Integer id_s = getActivity().getIntent().getIntExtra("id", 0);
        recipeStepDetails = check.get(id_s).getSteps();
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);
        ButterKnife.bind(this,view);
        int step_id = getArguments().getInt("id_Step");
        stepDetail.setText(recipeStepDetails.get(step_id).getDesc());
        // Inflate the layout for this fragment
        return view;
    }

}
