package com.example.ravikiranpathade.bakingapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.singleList.RecipeList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ravikiranpathade on 10/13/17.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {


    RecipeList recipeList;
    Intent intent;
    Context mContext;

    public interface RecipeOnClickListener{
        void onClick(String id);
    }
    private RecipeOnClickListener recipeOnClickListener;
    public List<RecipeList> mRecipeList;

    public void setRecipeOnClickListener(RecipeOnClickListener rListener){
        recipeOnClickListener = rListener;
    }
    public RecipeListAdapter(List<RecipeList> rDetails){
        mRecipeList = rDetails;
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layourId = R.layout.fragment_recipe_list;
        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachImmediately = false;

        View view = layoutInflater.inflate(layourId,parent,shouldAttachImmediately);
        RecipeListViewHolder rHolder = new RecipeListViewHolder(view);

        return rHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    //Done SetData function
    public void setData(List<RecipeList> mRList){
        mRecipeList = mRList;
        notifyDataSetChanged();
    }

    class RecipeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.recipeListButton)
        TextView mRecipeListButton;

        Context mHolderContext;

        public RecipeListViewHolder(View itemView) {
            super(itemView);
            mHolderContext = itemView.getContext();
            ButterKnife.bind((Activity) mHolderContext);
            itemView.setOnClickListener(this);

        }
        public void bind(int id){
            //Done
            mRecipeListButton.setText(mRecipeList.get(id).getRecipeName());
        }
        @Override
        public void onClick(View v) {

            int pos = getAdapterPosition();
            String rId = mRecipeList.get(pos).getId();
            //TODO Implement Intent
//            intent = new Intent();
//            mHolderContext.startActivity(intent);

        }
    }
}
