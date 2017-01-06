package com.github.yin.recipe.model;

import com.google.common.collect.ImmutableList;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
public interface Recipe {
    NameIngredient name();
    OutputsIngredient outputs();
    ImmutableList<Recipe> mainIngredients();
}
