package com.richfield.smartpantrymanager;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {

    public static List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe("Pap and Tomato Relish","maize meal,tomato,onion,oil,salt,water",
                "1. Boil water with salt and oil.\n2. Add maize meal while stirring.\n3. Cover 10 mins.\n4. Fry onion in oil.\n5. Add tomato, salt, simmer 8 mins.\n6. Serve pap with relish."));
        recipes.add(new Recipe("Tomato and Onion Mix","tomato,onion,oil,salt",
                "1. Heat oil.\n2. Fry onion till golden.\n3. Add tomato and salt.\n4. Cook 7 mins, serve."));
        recipes.add(new Recipe("Samp and Beans","samp,beans,oil,salt,water",
                "1. Soak samp and beans overnight.\n2. Boil in salted water 1.5 hours till soft.\n3. Add oil, mash lightly."));
        recipes.add(new Recipe("Pilchard Stew","pilchards,tomato,onion,oil,salt",
                "1. Fry onion in oil.\n2. Add tomato, cook 5 mins.\n3. Add pilchards with sauce, simmer 5 mins.\n4. Serve with pap."));
        recipes.add(new Recipe("Boerewors and Onion Fry","boerewors,onion,oil,salt",
                "1. Fry onion in oil.\n2. Add sliced boerewors, fry 10 mins.\n3. Season with salt."));
        recipes.add(new Recipe("Egg and Tomato Breakfast","egg,tomato,onion,oil,salt",
                "1. Fry onion in oil.\n2. Add tomato, cook 3 mins.\n3. Crack eggs, scramble with salt.\n4. Cook till set."));
        recipes.add(new Recipe("Cabbage and Potato Stew","cabbage,potato,onion,oil,salt,water",
                "1. Fry onion in oil.\n2. Add potato cubes, fry 5 mins.\n3. Add cabbage, salt, splash water.\n4. Cover and steam 15 mins."));
        recipes.add(new Recipe("Rice and Chakalaka Mix","rice,chakalaka,onion,oil,water,salt",
                "1. Cook rice in salted water.\n2. Fry onion in oil, add chakalaka.\n3. Mix with cooked rice."));
        recipes.add(new Recipe("Beans and Tomato","beans,tomato,onion,oil,salt",
                "1. Fry onion in oil.\n2. Add tomato, cook 5 mins.\n3. Add baked beans, simmer 5 mins."));
        recipes.add(new Recipe("Peanut Butter Porridge","maize meal,peanut butter,sugar,water,salt",
                "1. Boil water with salt.\n2. Add maize meal, stir.\n3. Add peanut butter and sugar, mix well."));
        recipes.add(new Recipe("Fried Cabbage","cabbage,onion,oil,salt",
                "1. Heat oil, fry onion.\n2. Add shredded cabbage and salt.\n3. Fry 10 mins till soft."));
        recipes.add(new Recipe("Potato and Onion Fry","potato,onion,oil,salt",
                "1. Peel and slice potatoes.\n2. Fry onion in oil, add potatoes.\n3. Add salt, fry till golden."));
        recipes.add(new Recipe("Tomato Rice","rice,tomato,onion,oil,salt,water",
                "1. Fry onion in oil.\n2. Add tomato and salt, cook 5 mins.\n3. Add rice and water, cook till rice soft."));
        recipes.add(new Recipe("Plain Pap","maize meal,water,salt",
                "1. Boil water with salt.\n2. Add maize meal slowly while stirring.\n3. Cover and cook 15 mins, stir again."));
        recipes.add(new Recipe("Egg Fried Rice","rice,egg,onion,oil,salt",
                "1. Cook rice and cool.\n2. Fry onion in oil, add rice.\n3. Push aside, scramble eggs, mix with rice and salt."));
        recipes.add(new Recipe("Onion and Noodles","noodles,onion,oil,water,salt",
                "1. Boil noodles with salt, drain.\n2. Fry onion in oil, add noodles.\n3. Toss 2 mins and serve."));
        recipes.add(new Recipe("Peanut Butter and Bread","bread,peanut butter",
                "1. Toast bread if possible.\n2. Spread peanut butter generously."));
        recipes.add(new Recipe("Chakalaka Pap","maize meal,chakalaka,water,salt,oil",
                "1. Cook pap as normal.\n2. Heat chakalaka in separate pan.\n3. Serve chakalaka over pap."));
        recipes.add(new Recipe("Potato Mash","potato,oil,salt,water",
                "1. Peel and boil potatoes in salted water.\n2. Drain, add oil and mash.\n3. Season with salt."));
        recipes.add(new Recipe("Boiled Egg and Salt","egg,water,salt",
                "1. Boil water.\n2. Add eggs, boil 10 mins.\n3. Peel and add salt."));

        return recipes;
    }
}