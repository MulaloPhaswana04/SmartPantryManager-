package com.richfield.smartpantrymanager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvIng = findViewById(R.id.tvDetailIngredients);
        TextView tvSteps = findViewById(R.id.tvDetailSteps);

        tvName.setText(getIntent().getStringExtra("name"));
        tvIng.setText("Ingredients: \n" + getIntent().getStringExtra("ingredients"));
        tvSteps.setText("Steps: \n" + getIntent().getStringExtra("steps"));
    }
}