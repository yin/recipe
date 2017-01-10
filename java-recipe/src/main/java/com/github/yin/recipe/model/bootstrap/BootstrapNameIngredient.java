package com.github.yin.recipe.model.bootstrap;

import com.github.yin.recipe.model.NameIngredient;
import com.google.auto.value.AutoValue;

/**
 * Represents names for bootstraping purposes.
 */
@AutoValue
public abstract class BootstrapNameIngredient implements NameIngredient {
    public NameIngredient create(String value) {
        return builder().value(value).build();
    }

    public Builder builder() {
        return new AutoValue_BootstrapNameIngredient.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder value(String value);

        public abstract BootstrapNameIngredient build();
    }
}
