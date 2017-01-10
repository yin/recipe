package com.github.yin.recipe.model;

import com.google.common.collect.ImmutableList;

/**
 * Represents recipe's collection of outputs.
 */
public interface OutputsIngredient {
    ImmutableList<String> values();
}
