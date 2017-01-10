package com.github.yin.recipe.model.bootstrap;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.yin.recipe.model.NameIngredient;
import com.google.auto.value.AutoValue;

/**
 * Represents names for bootstraping purposes.
 */
@JsonDeserialize(as = AutoValue_BootstrapNameIngredient.class)
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
