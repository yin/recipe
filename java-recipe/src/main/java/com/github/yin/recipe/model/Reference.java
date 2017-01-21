package com.github.yin.recipe.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.auto.value.AutoValue;

/**
 * Represents names for bootstraping purposes.
 */
@AutoValue
public abstract class Reference {
    @JsonCreator
    public static Reference create(String value) {
        return builder().value(value).build();
    }

    public static Builder builder() {
        return new AutoValue_Reference.Builder();
    }

    @JsonValue
    public abstract String value();

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder value(String value);

        public abstract Reference build();
    }
}
