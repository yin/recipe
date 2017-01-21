package com.github.yin.recipe.templating.st4;

import com.github.yin.recipe.model.Ingredient;

/**
 * Contains utility functions for StringTemplate engine.
 */
public class St4Templates {
    public static String locate(Ingredient ingredient) {
        //TODO yin: Figure out how we want to manage recipe templates
        return "st4/java/" + ingredient.name() + ".stg";
    }
}
