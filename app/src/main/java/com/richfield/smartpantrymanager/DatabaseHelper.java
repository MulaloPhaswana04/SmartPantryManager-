package com.richfield.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "SmartPantry.db";
    private static final int DB_VERSION = 2; // bumped to 2

    // Pantry Table
    private static final String TABLE_PANTRY = "pantry_items";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_QTY = "qty";
    private static final String COL_UNIT = "unit";
    private static final String COL_EXPIRY = "expiry";

    // Recipe Table - REQUIRED
    private static final String TABLE_RECIPES = "recipes";
    private static final String R_COL_ID = "id";
    private static final String R_COL_NAME = "name";
    private static final String R_COL_INGREDIENTS = "ingredients";
    private static final String R_COL_STEPS = "steps";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPantry = "CREATE TABLE " + TABLE_PANTRY + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " + COL_QTY + " INTEGER, " +
                COL_UNIT + " TEXT, " + COL_EXPIRY + " TEXT)";
        db.execSQL(createPantry);

        String createRecipes = "CREATE TABLE " + TABLE_RECIPES + " (" +
                R_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                R_COL_NAME + " TEXT, " + R_COL_INGREDIENTS + " TEXT, " +
                R_COL_STEPS + " TEXT)";
        db.execSQL(createRecipes);

        seedRecipes(db); // Pre-load 20 recipes
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    private void seedRecipes(SQLiteDatabase db) {
        addRecipeSeed(db, "Tomato Pasta", "tomatoes, pasta, onion, garlic, salt", "1. Boil pasta. 2. Fry onion & garlic. 3. Add tomatoes & salt. 4. Mix with pasta.");
        addRecipeSeed(db, "Fried Eggs", "eggs, salt, oil", "1. Heat oil. 2. Crack eggs. 3. Add salt. 4. Fry.");
        addRecipeSeed(db, "Maize Meal Porridge", "maize meal, water, salt", "1. Boil water. 2. Add salt. 3. Stir in maize meal. 4. Cook until thick.");
        addRecipeSeed(db, "Rice and Beans", "rice, beans, water, salt, oil", "1. Boil beans. 2. Cook rice. 3. Mix with oil and salt.");
        addRecipeSeed(db, "Peanut Butter Sandwich", "bread, peanut butter", "1. Spread peanut butter on bread. 2. Close sandwich.");
        addRecipeSeed(db, "Tomato and Onion Salad", "tomatoes, onion, salt, oil", "1. Chop tomatoes and onion. 2. Mix with oil and salt.");
        addRecipeSeed(db, "Boiled Eggs", "eggs, water, salt", "1. Boil water. 2. Add eggs. 3. Cook 10 mins.");
        addRecipeSeed(db, "Garlic Bread", "bread, garlic, butter", "1. Mix garlic with butter. 2. Spread on bread. 3. Toast.");
        addRecipeSeed(db, "Simple Omelette", "eggs, onion, tomatoes, salt, oil", "1. Chop veg. 2. Beat eggs. 3. Fry veg. 4. Add eggs.");
        addRecipeSeed(db, "Rice Porridge", "rice, water, salt, milk", "1. Boil rice in water. 2. Add milk and salt. 3. Cook until soft.");
        addRecipeSeed(db, "Beans Stew", "beans, tomatoes, onion, oil, salt", "1. Boil beans. 2. Fry onion. 3. Add tomatoes. 4. Mix.");
        addRecipeSeed(db, "Pasta with Oil", "pasta, oil, salt, garlic", "1. Boil pasta. 2. Fry garlic in oil. 3. Mix.");
        addRecipeSeed(db, "Tomato Soup", "tomatoes, onion, water, salt", "1. Boil tomatoes and onion. 2. Blend. 3. Add salt.");
        addRecipeSeed(db, "Sugar Porridge", "maize meal, sugar, water", "1. Boil water. 2. Add maize meal. 3. Add sugar.");
        addRecipeSeed(db, "Bread and Eggs", "bread, eggs, oil, salt", "1. Fry eggs. 2. Toast bread. 3. Serve together.");
        addRecipeSeed(db, "Onion Fried Rice", "rice, onion, oil, salt, water", "1. Cook rice. 2. Fry onion in oil. 3. Mix.");
        addRecipeSeed(db, "Boiled Maize Meal with Tomato", "maize meal, tomatoes, onion, salt", "1. Cook maize meal. 2. Make tomato relish. 3. Serve.");
        addRecipeSeed(db, "Garlic Rice", "rice, garlic, oil, salt, water", "1. Fry garlic in oil. 2. Add rice and water. 3. Add salt and cook.");
        addRecipeSeed(db, "Milk Bread", "bread, milk, sugar", "1. Warm milk. 2. Add sugar. 3. Dip bread or pour over.");
        addRecipeSeed(db, "Salted Tomatoes", "tomatoes, salt", "1. Slice tomatoes. 2. Add salt. Ready.");
    }

    private void addRecipeSeed(SQLiteDatabase db, String name, String ingredients, String steps) {
        ContentValues cv = new ContentValues();
        cv.put(R_COL_NAME, name);
        cv.put(R_COL_INGREDIENTS, ingredients);
        cv.put(R_COL_STEPS, steps);
        db.insert(TABLE_RECIPES, null, cv);
    }

    // --- PANTRY CRUD ---
    public long addItem(PantryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, item.getName().toLowerCase().trim());
        cv.put(COL_QTY, item.getQuantity());
        cv.put(COL_UNIT, item.getUnit());
        cv.put(COL_EXPIRY, item.getExpiryDate());
        long result = db.insert(TABLE_PANTRY, null, cv);
        db.close();
        return result;
    }

    public void updateItem(PantryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, item.getName().toLowerCase().trim());
        cv.put(COL_QTY, item.getQuantity());
        cv.put(COL_UNIT, item.getUnit());
        cv.put(COL_EXPIRY, item.getExpiryDate());
        db.update(TABLE_PANTRY, cv, COL_ID + "=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public ArrayList<PantryItem> getAllItems() {
        ArrayList<PantryItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PANTRY, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new PantryItem(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        cursor.close(); db.close();
        return list;
    }

    public void deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PANTRY, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // --- STRICT MATCHING LOGIC - CORE OF ASSIGNMENT ---
    public ArrayList<Recipe> getSuggestedRecipes() {
        ArrayList<Recipe> suggested = new ArrayList<>();
        ArrayList<PantryItem> pantry = getAllItems();

        // Build list of pantry names lowercase for robust matching
        ArrayList<String> pantryNames = new ArrayList<>();
        for (PantryItem p : pantry) pantryNames.add(p.getName().toLowerCase().trim());

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RECIPES, null);
        if (cursor.moveToFirst()) {
            do {
                Recipe r = new Recipe(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                String[] required = r.getIngredients().toLowerCase().split(",");
                boolean allFound = true;
                for (String req : required) {
                    req = req.trim();
                    // handle plural: tomato vs tomatoes
                    if (!pantryNames.contains(req) && !pantryNames.contains(req + "s") && !pantryNames.contains(req.replace("es",""))) {
                        boolean partial = false;
                        for (String pName : pantryNames) { if (pName.contains(req) || req.contains(pName)) { partial = true; break; } }
                        if (!partial) { allFound = false; break; }
                    }
                }
                if (allFound) suggested.add(r);
            } while (cursor.moveToNext());
        }
        cursor.close(); db.close();
        return suggested;
    }

    public ArrayList<Recipe> getAllRecipes() {
        ArrayList<Recipe> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RECIPES, null);
        if (cursor.moveToFirst()) {
            do { list.add(new Recipe(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3))); } while (cursor.moveToNext());
        }
        cursor.close(); db.close();
        return list;
    }
}