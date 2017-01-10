package com.github.yin.recipe.model;

/**
 * Represents an ingredient to cook in a recipe.
 */
public interface Ingredient {
    /** Name of the ingredient, i.e.: message */
    NameIngredient name();

    /** TODO: The data to put into recipe template. */
    Object data();
}