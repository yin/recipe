package com.github.yin.recipe.model.bootstrap;

import com.github.yin.recipe.model.OutputsIngredient;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

/**
 * Represents recipe outputs for bootstrapping purposes.
 */
@AutoValue
public abstract class BootstrapOutputsIngredient implements OutputsIngredient {
    public OutputsIngredient create(ImmutableList<String> values) {
        return builder().values(values).build();
    }

    public Builder builder() {
        return new AutoValue_BootstrapOutputsIngredient.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder values(ImmutableList<String> values);

        public abstract BootstrapOutputsIngredient build();
    }
}
