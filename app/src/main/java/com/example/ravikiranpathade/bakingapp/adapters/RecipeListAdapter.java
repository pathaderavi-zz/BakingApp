package com.example.ravikiranpathade.bakingapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    Context mHolderContext;
    Context context;
    TextView recipeListButton1;



    public interface RecipeOnClickListener {
        void onClick(String id);
    }

    private RecipeOnClickListener recipeOnClickListener;
    public List<RecipeList> mRecipeList;

    public void setRecipeOnClickListener(RecipeOnClickListener rListener) {
        recipeOnClickListener = rListener;
    }

    public RecipeListAdapter(List<RecipeList> rDetails) {
        mRecipeList = rDetails;
        Log.d("Constructor ", "Bind");
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layourId = R.layout.recipe_list_item;
         context = parent.getContext();
        //recipeListButton1 = (TextView) parent.findViewById(R.id.recipeListButton);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachImmediately = false;

        View view = layoutInflater.inflate(layourId, parent, shouldAttachImmediately);

        RecipeListViewHolder rHolder = new RecipeListViewHolder(view);
        Log.d("Holder ", "Bind");
        return rHolder;
    }

    @Override
    public void onBindViewHolder(final RecipeListViewHolder holder, int position) {
        Log.d("onCreateViewHolder "+String.valueOf(mRecipeList.get(position).getId()), String.valueOf(context!=null));

        //mRecipeListButton.setText("Check Text ");
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    //Done SetData function
    public void setData(List<RecipeList> mRList) {
        mRecipeList = mRList;
        notifyDataSetChanged();
    }

    class RecipeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipeListButton) Button mRecipeListButton;
        //@BindView(R.id.testText)
        //TextView mRecipeListButton;


        public RecipeListViewHolder(View itemView) {
            super(itemView);
            mHolderContext = itemView.getContext();
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
            Log.d("ViewHolder Here ", "Bind");

        }

        public void bind(int id) {

            Log.d("Check Bind "+String.valueOf(mRecipeListButton!=null), mRecipeList.get(id).getRecipeName());
            //mRecipeListButton.setText(mRecipeList.get(id).getRecipeName());
        }

        @Override
        public void onClick(View v) {

            int pos = getAdapterPosition();
            String rId = mRecipeList.get(pos).getId();

            Log.d("Button Clicked",String.valueOf(pos));
            Toast.makeText(v.getContext(),String.valueOf(pos),Toast.LENGTH_SHORT).show();
            //mRecipeListButton.setText(String.valueOf(pos));
            //TODO Implement Intent
//            intent = new Intent();
//            mHolderContext.startActivity(intent);

        }
    }
}
