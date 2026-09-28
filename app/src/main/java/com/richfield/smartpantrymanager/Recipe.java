package com.richfield.smartpantrymanager;

public class Recipe {
    int id;
    String name;
    String ingredients;
    String steps;

    public Recipe(int id, String name, String ingredients, String steps) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }
    public String getName() { return name; }
    public String getIngredients() { return ingredients; }
    public String getSteps() { return steps; }
}