package com.github.yin.recipe.model.bootstrap;

import com.github.yin.recipe.model.Message;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

/**
 * Represents a value class for bootstrapping purposes.
 */
@AutoValue
public abstract class BootstrapMessage implements Message {
    public Message create(String name, ImmutableList<Field> fields) {
        return builder().name(name).fields(fields).build();
    }

    public Builder builder() {
        return new AutoValue_BootstrapMessage.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder name(String name);

        public abstract Builder fields(ImmutableList<Field> fields);

        public abstract BootstrapMessage build();
    }
}
