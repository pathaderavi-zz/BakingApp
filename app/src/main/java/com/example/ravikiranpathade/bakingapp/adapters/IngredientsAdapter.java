package com.example.ravikiranpathade.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.singleList.Ingredients;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ravikiranpathade on 10/24/17.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    List<Ingredients> ingredientsList;
    Context contextAdapter;

    public IngredientsAdapter(List<Ingredients> list){
        ingredientsList = list;
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layourId = R.layout.ingredients_list_item;
        contextAdapter = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(contextAdapter);
        boolean shouldAttach = false;
        View view = inflater.inflate(layourId,parent,shouldAttach);
        IngredientsViewHolder ingredientsViewHolder = new IngredientsViewHolder(view);
        return ingredientsViewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ingredientCount)
        TextView ingredientsCount;
        @BindView(R.id.ingredientName) TextView ingredientsName;

        Context viewHolderContext;

        public IngredientsViewHolder(View itemView) {
                super(itemView);
            viewHolderContext  = itemView.getContext();
            ButterKnife.bind(this,itemView);

            }

        public void bind(int id) {

            ingredientsCount.setText(String.valueOf(ingredientsList.get(id).getQuantity())+"\n"+ingredientsList.get(id).getMeasure());
            ingredientsName.setText(ingredientsList.get(id).getIngredient_name());

        }
}
}
