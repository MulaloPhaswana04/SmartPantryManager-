package com.richfield.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView nameTv = findViewById(R.id.detailName);
        TextView ingTv = findViewById(R.id.detailIngredients);
        TextView stepsTv = findViewById(R.id.detailSteps);

        String name = getIntent().getStringExtra("name");
        String ingredients = getIntent().getStringExtra("ingredients");
        String steps = getIntent().getStringExtra("steps");

        nameTv.setText(name);
        ingTv.setText("Ingredients:\n" + ingredients);
        stepsTv.setText("Steps:\n" + (steps != null ? steps : "No steps available"));
    }
}