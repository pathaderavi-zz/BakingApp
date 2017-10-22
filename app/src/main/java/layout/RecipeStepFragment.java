package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepFragment extends Fragment {
    @BindView(R.id.ingredientsButton)
    Button ingredientsButton;

    public RecipeStepFragment() {
        // Required empty public constructor
    }

    public interface OnStepClickListener{
        void onStepSelected(int position);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        ButterKnife.bind(this,view);

        Intent i = new Intent();
        ArrayList<RecipeList> check  = getActivity().getIntent().getParcelableArrayListExtra("check");
        Integer id  = getActivity().getIntent().getIntExtra("id",0);
        //Done Set text of the ingredients button
        ingredientsButton.setText("See all"+String.valueOf(check.get(id).getSteps().size())+" ingredients");

        return view;
    }

}
