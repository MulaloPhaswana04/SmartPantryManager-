package com.richfield.smartpantrymanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<Recipe> recipes;
    private Context context;

    public RecipeAdapter(List<Recipe> recipes, Context context) {
        this.recipes = recipes; this.context = context;
    }

    @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.name.setText(recipe.getName());
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, RecipeDetailActivity.class);
            i.putExtra("name", recipe.getName());
            i.putExtra("ingredients", recipe.getIngredients());
            i.putExtra("steps", recipe.getSteps());
            context.startActivity(i);
        });
    }

    @Override public int getItemCount() { return recipes.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name; ViewHolder(View itemView) {
            super(itemView); name = itemView.findViewById(R.id.recipeName);
        }
    }
}