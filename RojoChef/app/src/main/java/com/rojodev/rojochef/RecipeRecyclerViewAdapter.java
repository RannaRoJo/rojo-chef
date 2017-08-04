package com.rojodev.rojochef;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeImageViewHolder> {
    private static final String TAG = "RecipeRecyclerViewAdapt";
    private List<Recipe> recipesList;
    private Context context;

    public RecipeRecyclerViewAdapter(Context context, List<Recipe> recipesList) {
        this.recipesList = recipesList;
        this.context = context;
    }

    @Override
    public RecipeImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //inflates and returns view
        //Called by layout manager when it needs new view
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);
        return new RecipeImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeImageViewHolder holder, int position) {
        //Called by the layout manager when it wants new data in an existing row
        Recipe recipeItem = recipesList.get(position);
        Log.d(TAG, "onBindViewHolder:  " + recipeItem.getTitle() + "--> " + position);
        Picasso.with(context).load(recipeItem.getMedia().getThumbnailUrl())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

        holder.title.setText(recipeItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return ((recipesList != null) && (recipesList.size() != 0) ? recipesList.size() : 0);
    }

    void loadNewData(List<Recipe> newRecipes) {
        recipesList = newRecipes;
        notifyDataSetChanged();
    }

    public Recipe getRecipe(int position) {
        if(recipesList == null) {
            Log.d(TAG, "getRecipe: is NULL!!");
        }
        if (recipesList.size() == 0) {
            Log.d(TAG, "getRecipe: is ZERO!!");
        }
        return ((recipesList != null) && (recipesList.size() != 0) ? recipesList.get(position) : null);
    }

    static class RecipeImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "RecipeImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;

        public RecipeImageViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "RecipeImageViewHolder: starts");
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
