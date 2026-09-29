package com.richfield.smartpantrymanager;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        recyclerView = findViewById(R.id.recyclerSuggested);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbHelper = new DatabaseHelper(this);

        List<PantryItem> pantryItems = dbHelper.getAllItems();
        List<String> pantryNames = new ArrayList<>();
        for (PantryItem item : pantryItems) {
            pantryNames.add(item.getName().toLowerCase().trim());
        }

        List<Recipe> allRecipes = RecipeRepository.getAllRecipes();
        List<Recipe> matched = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            String[] needed = recipe.getIngredients().toLowerCase().split(",");
            boolean allFound = true;
            for (String need : needed) {
                need = need.trim();
                // Robust matching for tomato/tomatoes
                boolean found = false;
                for (String have : pantryNames) {
                    if (have.contains(need) || need.contains(have) || have.equals(need + "s") || (have + "s").equals(need)) {
                        found = true; break;
                    }
                }
                if (!found) { allFound = false; break; }
            }
            if (allFound) matched.add(recipe);
        }

        RecipeAdapter adapter = new RecipeAdapter(matched, this);
        recyclerView.setAdapter(adapter);
    }
}