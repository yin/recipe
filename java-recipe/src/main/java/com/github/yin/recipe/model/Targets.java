package com.github.yin.recipe.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

/**
 * Represents recipe targets for bootstrapping purposes.
 */
@AutoValue
public abstract class Targets {
    @JsonCreator
    public static Targets create(ImmutableList<String> values) {
        return builder().values(values).build();
    }

    public static Builder builder() {
        return new AutoValue_Targets.Builder();
    }

    @JsonValue
    public abstract ImmutableList<String> values();

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder values(ImmutableList<String> values);

        public abstract Targets build();
    }
}
