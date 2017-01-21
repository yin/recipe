package com.github.yin.recipe.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * Represents a value class for bootstrapping purposes.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_Message.Builder.class)
public abstract class Message extends Ingredient {

    @AutoValue
    public static abstract class Field {
        public enum Modifiers {
            OPTIONAL, REPEATED, DEPRECATED;

            @JsonCreator
            public static Modifiers fromString(String name) {
                return Modifiers.valueOf(name);
            }
        }

        @JsonCreator
        public static Field create(@JsonProperty("name") String name,
                                   @JsonProperty("type") String type,
                                   @JsonProperty("modifiers") ImmutableSet<Modifiers> modifiers) {
            return new AutoValue_Message_Field(name, type, modifiers);
        }

        @JsonProperty("name")
        public abstract String name();

        @JsonProperty("type")
        public abstract String type();

        @JsonProperty("modifiers")
        public abstract ImmutableSet<Modifiers> modifiers();
    }

    public static Message create(String name, ImmutableList<Field> fields) {
        return builder().name(name).fields(fields).build();
    }

    public static Builder builder() {
        return new AutoValue_Message.Builder();
    }

    @JsonProperty("name")
    public abstract String name();

    @JsonProperty("fields")
    public abstract ImmutableList<Field> fields();

    @AutoValue.Builder
    public static abstract class Builder {
        @JsonProperty("name")
        public abstract Builder name(String name);

        @JsonProperty("fields")
        public abstract Builder fields(ImmutableList<Field> fields);

        public abstract Message build();
    }
}
