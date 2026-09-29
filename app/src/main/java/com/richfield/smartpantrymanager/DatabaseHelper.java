package com.richfield.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "smart_pantry.db";
    private static final int DB_VERSION = 3; // bumped to add unit column

    private static final String TABLE_PANTRY = "pantry";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_QUANTITY = "quantity";
    private static final String COL_UNIT = "unit";
    private static final String COL_EXPIRY = "expiry";

    private static final String TABLE_RECIPES = "recipes";
    private static final String COL_R_ID = "rid";
    private static final String COL_R_NAME = "rname";
    private static final String COL_R_INGREDIENTS = "ingredients";
    private static final String COL_R_METHOD = "method";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PANTRY + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " TEXT," + COL_QUANTITY + " TEXT," + COL_UNIT + " TEXT," + COL_EXPIRY + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_RECIPES + " (" + COL_R_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_R_NAME + " TEXT," + COL_R_INGREDIENTS + " TEXT," + COL_R_METHOD + " TEXT)");
        seedRecipes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    public void addItem(PantryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, item.getName().toLowerCase().trim());
        cv.put(COL_QUANTITY, item.getQuantity());
        cv.put(COL_UNIT, item.getUnit());
        cv.put(COL_EXPIRY, item.getExpiryDate());
        db.insert(TABLE_PANTRY, null, cv);
        db.close();
    }

    public List<PantryItem> getAllItems() {
        List<PantryItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_PANTRY, null);
        if (c.moveToFirst()) {
            do {
                PantryItem p = new PantryItem(
                        c.getString(c.getColumnIndexOrThrow(COL_NAME)),
                        c.getString(c.getColumnIndexOrThrow(COL_QUANTITY)),
                        c.getString(c.getColumnIndexOrThrow(COL_UNIT)),
                        c.getString(c.getColumnIndexOrThrow(COL_EXPIRY))
                );
                p.setId(c.getInt(c.getColumnIndexOrThrow(COL_ID)));
                list.add(p);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public void updateItem(PantryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, item.getName().toLowerCase().trim());
        cv.put(COL_QUANTITY, item.getQuantity());
        cv.put(COL_UNIT, item.getUnit());
        cv.put(COL_EXPIRY, item.getExpiryDate());
        db.update(TABLE_PANTRY, cv, COL_ID + "=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PANTRY, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    private void addRecipeInternal(SQLiteDatabase db, String name, String ingredients, String method) {
        ContentValues cv = new ContentValues();
        cv.put(COL_R_NAME, name);
        cv.put(COL_R_INGREDIENTS, ingredients);
        cv.put(COL_R_METHOD, method);
        db.insert(TABLE_RECIPES, null, cv);
    }

    private void seedRecipes(SQLiteDatabase db) {
        addRecipeInternal(db, "Pap and Tomato Relish", "maize meal,tomato,onion,oil,salt,water", "1. Boil water with salt 2. Add maize meal and stir to make pap 3. Fry onion and tomato with oil to make relish 4. Serve together");
        addRecipeInternal(db, "Pap and Wors", "maize meal,boerewors,oil,salt,water", "1. Make pap with water and salt 2. Fry wors in pan with little oil 3. Serve");
        addRecipeInternal(db, "Fried Eggs and Bread", "egg,bread,oil,salt", "1. Heat oil 2. Fry eggs 3. Toast bread and serve with eggs");
        addRecipeInternal(db, "Tomato and Onion Mix", "tomato,onion,oil,salt", "1. Chop tomato and onion 2. Fry onion first then add tomato 3. Add salt and cook until soft");
        addRecipeInternal(db, "Samp and Beans", "samp,beans,salt,water,oil", "1. Soak samp and beans overnight 2. Boil together with salted water 3. Add little oil when soft");
        addRecipeInternal(db, "Potato and Egg Fry", "potato,egg,oil,salt", "1. Peel and cut potatoes 2. Fry potatoes till soft 3. Pour beaten egg over and mix");
        addRecipeInternal(db, "Rice and Tinned Fish", "rice,pilchards,tomato,onion,oil,salt", "1. Cook rice 2. Fry onion and tomato 3. Add pilchards and mix with rice");
        addRecipeInternal(db, "Chicken Stew with Rice", "chicken,tomato,onion,oil,salt,rice,water", "1. Fry onion 2. Add chicken pieces and brown 3. Add tomato and salt 4. Cook and serve with rice");
        addRecipeInternal(db, "Baked Beans and Bread", "baked beans,bread", "1. Heat baked beans in pot 2. Serve with bread");
        addRecipeInternal(db, "Noodles with Egg", "noodles,egg,oil,salt,water", "1. Boil noodles 2. Drain 3. Fry with egg and little oil");
        addRecipeInternal(db, "Potato Curry", "potato,onion,tomato,oil,salt,water", "1. Fry onion 2. Add potatoes and tomato 3. Add water and salt 4. Cook till soft");
        addRecipeInternal(db, "Chakalaka", "tomato,onion,baked beans,oil,salt", "1. Fry onion 2. Add tomato 3. Add baked beans and salt 4. Cook 10 mins");
        addRecipeInternal(db, "Egg and Tomato Sandwich", "bread,egg,tomato,salt", "1. Fry egg 2. Slice tomato 3. Put in bread with salt");
        addRecipeInternal(db, "Pap and Cabbage", "maize meal,cabbage,onion,oil,salt,water", "1. Make pap 2. Fry cabbage with onion and oil 3. Serve together");
        addRecipeInternal(db, "Peanut Butter Sandwich", "bread,peanut butter", "1. Spread peanut butter on bread");
        addRecipeInternal(db, "Chicken Livers and Pap", "chicken livers,onion,tomato,oil,salt,maize meal,water", "1. Make pap 2. Fry onion and tomato 3. Add livers and cook");
        addRecipeInternal(db, "Simple Vetkoek", "flour,oil,salt,water,sugar", "1. Mix flour, salt, sugar and water to dough 2. Make small balls 3. Deep fry in oil");
        addRecipeInternal(db, "Beans Curry with Pap", "beans,tomato,onion,oil,salt,maize meal,water", "1. Cook beans 2. Make curry with tomato and onion 3. Serve with pap");
        addRecipeInternal(db, "Mageu and Bread", "bread,maize meal,sugar,water", "1. Mix maize meal with water and sugar 2. Let it sour overnight 3. Serve cold with bread");
        addRecipeInternal(db, "Fried Cabbage and Rice", "cabbage,rice,onion,oil,salt,water", "1. Cook rice 2. Fry cabbage with onion and oil 3. Mix together");
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_RECIPES, null);
        if (c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndexOrThrow(COL_R_NAME));
                String ingredients = c.getString(c.getColumnIndexOrThrow(COL_R_INGREDIENTS));
                String method = c.getString(c.getColumnIndexOrThrow(COL_R_METHOD));
                list.add(new Recipe(name, ingredients, method));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public List<Recipe> getSuggestedRecipes() {
        List<PantryItem> pantryItems = getAllItems();
        Set<String> pantrySet = new HashSet<>();
        for (PantryItem p : pantryItems) {
            pantrySet.add(p.getName().toLowerCase().trim());
        }
        List<Recipe> all = getAllRecipes();
        List<Recipe> suggested = new ArrayList<>();
        for (Recipe r : all) {
            boolean canMake = true;
            List<String> req = Arrays.asList(r.getIngredients().toLowerCase().split(","));
            for (String ing : req) {
                String clean = ing.trim();
                if (!pantrySet.contains(clean)) {
                    canMake = false;
                    break;
                }
            }
            if (canMake) suggested.add(r);
        }
        return suggested;
    }
}