package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.adapters.StepDetailAdapter;
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
    //    @BindView(R.id.step_detail_desc)
//    TextView stepDetail;
    @BindView(R.id.previous)
    Button previous;
    @BindView(R.id.next)
    Button next;
    Integer id_s;
    int step_id;
    @BindView(R.id.stepDetailRecycler)
    RecyclerView stepDetailsRecycler;

    public StepDetailAdapter getAdapter() {
        return adapter;
    }

    StepDetailAdapter adapter;
    LinearLayoutManager layoutManager;

    public StepDetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<RecipeList> check = getActivity().getIntent().getParcelableArrayListExtra("check");

        id_s = getActivity().getIntent().getIntExtra("id", 0);


        recipeStepDetails = check.get(id_s).getSteps();

        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        ButterKnife.bind(this, view);
        stepDetailsRecycler.setLayoutManager(layoutManager);
        stepDetailsRecycler.setHasFixedSize(true);

        step_id = getArguments().getInt("id_Step");

        //TODO ----------------------- Might have to change clickListener
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getExoplayer != null) {
                    Log.d("ExoPlayer", String.valueOf(adapter.getExoplayer == null));
                    adapter.getExoplayer.stop();
                    adapter.getExoplayer.release();
                }
                step_id = step_id - 1;
                if (step_id > 0) {
                    previous.setVisibility(View.VISIBLE);


                    adapter = new StepDetailAdapter(recipeStepDetails.get(step_id));

                    stepDetailsRecycler.setAdapter(adapter);

                } else {

                    previous.setVisibility(View.GONE);
                }
                if (step_id < recipeStepDetails.size() - 1) {

                    adapter = new StepDetailAdapter(recipeStepDetails.get(step_id));

                    stepDetailsRecycler.setAdapter(adapter);


                    next.setVisibility(View.VISIBLE);
                } else {
                    next.setVisibility(View.GONE);
                }

            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getExoplayer != null) {
                    Log.d("ExoPlayer", String.valueOf(adapter.getExoplayer == null));
                    adapter.getExoplayer.stop();
                    adapter.getExoplayer.release();
                }
                step_id = step_id + 1;

                if (step_id < recipeStepDetails.size() - 1) {
                    next.setVisibility(View.VISIBLE);

                    adapter = new StepDetailAdapter(recipeStepDetails.get(step_id));

                    stepDetailsRecycler.setAdapter(adapter);

                } else {
                    next.setVisibility(View.GONE);
                }
                if (step_id > 0) {
                    adapter = new StepDetailAdapter(recipeStepDetails.get(step_id));

                    stepDetailsRecycler.setAdapter(adapter);

                    previous.setVisibility(View.VISIBLE);
                } else {
                    previous.setVisibility(View.GONE);
                }

            }

        });

        if (!(step_id > 0)) {
            previous.setVisibility(View.GONE);

        }
        if (!(step_id < recipeStepDetails.size() - 1)) {

            next.setVisibility(View.GONE);

        }

        adapter = new StepDetailAdapter(recipeStepDetails.get(step_id));
        stepDetailsRecycler.setAdapter(adapter);
        return view;
    }

}
