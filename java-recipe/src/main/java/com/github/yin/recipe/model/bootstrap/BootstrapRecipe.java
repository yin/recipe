package com.github.yin.recipe.model.bootstrap;

import com.github.yin.recipe.model.Ingredient;
import com.github.yin.recipe.model.NameIngredient;
import com.github.yin.recipe.model.OutputsIngredient;
import com.github.yin.recipe.model.Recipe;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

/**
 * Represents a recipe for bootstrapping purposes.
 */
@AutoValue
public abstract class BootstrapRecipe implements Recipe {

    public Recipe create(NameIngredient name, OutputsIngredient outputs, ImmutableList<Ingredient> mainIngredients) {
        return builder().name(name).outputs(outputs).mainIngredients(mainIngredients).build();
    }

    public Builder builder() {
        return new AutoValue_BootstrapRecipe.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder name(NameIngredient name);

        public abstract Builder outputs(OutputsIngredient outputs);

        public abstract Builder mainIngredients(ImmutableList<Ingredient> mainIngredients);

        public abstract BootstrapRecipe build();
    }
}
