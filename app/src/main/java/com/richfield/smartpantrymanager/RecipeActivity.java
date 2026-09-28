package com.richfield.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    private TextView tvRecipes;
    private DatabaseHelper db;
    private ArrayList<Recipe> suggestedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        tvRecipes = findViewById(R.id.tvRecipes);
        db = new DatabaseHelper(this);

        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {
        try {
            ArrayList<PantryItem> pantry = db.getAllItems();

            if (pantry.isEmpty()) {
                tvRecipes.setText("Pantry is empty! Add items first.\n\nGo back and add at least 2-3 ingredients like 'tomatoes, onion, eggs, bread'");
                return;
            }

            suggestedList = db.getSuggestedRecipes();

            StringBuilder sb = new StringBuilder();
            sb.append("You have ").append(pantry.size()).append(" items in pantry:\n");
            for (PantryItem item : pantry) {
                sb.append("• ").append(item.getName()).append("\n");
            }

            sb.append("\n--------------------------\n");
            sb.append("SUGGESTED RECIPES (Strict Match)\n");
            sb.append("--------------------------\n\n");

            if (suggestedList.isEmpty()) {
                sb.append("No recipes match your pantry yet - add more ingredients.\n\n");
                sb.append("Tip: Try adding: tomatoes, onion, eggs, bread, rice, beans, pasta, garlic, oil, salt\n");
                sb.append("These cover 20 pre-loaded recipes.");
            } else {
                sb.append("Found ").append(suggestedList.size()).append(" recipe(s) you can make NOW:\n\n");
                for (int i = 0; i < suggestedList.size(); i++) {
                    Recipe r = suggestedList.get(i);
                    sb.append((i+1) + ". " + r.getName() + "\n");
                    sb.append("   Needs: " + r.getIngredients() + "\n");
                    sb.append("   Tap to view steps ->\n\n");
                }
                sb.append("\n(Tap a recipe number? We will add detail screen next)");
            }

            tvRecipes.setText(sb.toString());

            // Simple tap to open detail of first recipe - for demo
            // We'll improve this to RecyclerView after you confirm it works
            tvRecipes.setOnClickListener(v -> {
                if (!suggestedList.isEmpty()) {
                    Intent intent = new Intent(RecipeActivity.this, RecipeDetailActivity.class);
                    intent.putExtra("name", suggestedList.get(0).getName());
                    intent.putExtra("ingredients", suggestedList.get(0).getIngredients());
                    intent.putExtra("steps", suggestedList.get(0).getSteps());
                    startActivity(intent);
                }
            });

        } catch (Exception e) {
            tvRecipes.setText("Error: " + e.getMessage());
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}