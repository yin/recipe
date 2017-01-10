package com.github.yin.recipe.model.bootstrap;

import com.github.yin.recipe.model.Ingredient;
import com.github.yin.recipe.model.NameIngredient;
import com.google.auto.value.AutoValue;

/**
 * Represents a value class for bootstrapping purposes.
 */
@AutoValue
public abstract class BootstrapIngredient implements Ingredient {
    public Ingredient create(BootstrapNameIngredient name, Object data) {
        return builder().name(name).data(data).build();
    }

    public Builder builder() {
        return new AutoValue_BootstrapIngredient.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder name(NameIngredient name);

        public abstract Builder data(Object data);

        public abstract BootstrapIngredient build();
    }
}
