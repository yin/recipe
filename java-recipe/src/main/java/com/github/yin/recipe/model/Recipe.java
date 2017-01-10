package com.github.yin.recipe.model;

import com.google.common.collect.ImmutableList;

/**
 * Represents a recipe.
 */
public interface Recipe {
    NameIngredient name();
    OutputsIngredient outputs();
    ImmutableList<Ingredient> mainIngredients();
}
