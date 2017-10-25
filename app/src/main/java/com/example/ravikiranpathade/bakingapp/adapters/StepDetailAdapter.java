package com.example.ravikiranpathade.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.singleList.StepForRecipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ravikiranpathade on 10/25/17.
 */

public class StepDetailAdapter extends RecyclerView.Adapter<StepDetailAdapter.StepDetailViewHolder> {

    StepForRecipe allDetails;
    Context context;

    public StepDetailAdapter(StepForRecipe details){
       allDetails = details;

    }
    @Override
    public StepDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.step_detail_item;
        context = parent.getContext();
        boolean shouldAttach = false;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutId,parent,shouldAttach);
        StepDetailViewHolder stepDetails = new StepDetailViewHolder(view);
        return stepDetails;
    }

    @Override
    public void onBindViewHolder(StepDetailViewHolder holder, int position) {
            holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class StepDetailViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.step_description)
        TextView stepDescriptionItem;
        Context holderContext;
        public StepDetailViewHolder(View itemView) {
            super(itemView);
            holderContext = itemView.getContext();
            ButterKnife.bind(this,itemView);
            //Set Click Listener here if required

        }

        public void bind(int position) {
            stepDescriptionItem.setText(allDetails.getDesc());
        }
    }
}
