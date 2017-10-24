package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.singleList.Ingredients;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment {
    List<Ingredients> allIngredients;
    @BindView(R.id.checkBundle) TextView sampleText;
    public IngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        allIngredients = new ArrayList<>();
        if(savedInstanceState!=null) {
            Log.d("Ingredients Bundle", savedInstanceState.toString());
        }
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        ButterKnife.bind(this,view);
        sampleText.setText("Fragment Working");
        return view;
    }

}
