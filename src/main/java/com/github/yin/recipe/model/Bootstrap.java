package com.github.yin.recipe.model;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

/**
 * Stores structure of a recipe. Before we have a working recipe te generate
 * our model classes, we need a bootstrap the implementation.
 */
@AutoValue
public class Bootstrap {
    @AutoValue
    public static abstract class Recipe implements com.github.yin.recipe.model.Recipe {
        public Recipe.Builder builder() {
            return new AutoValue_Bootstrap_Recipe.Builder();
        }

        @AutoValue.Builder
        public static abstract class Builder {
            public abstract Builder name(com.github.yin.recipe.model.NameIngredient name);

            public abstract Builder outputs(com.github.yin.recipe.model.OutputsIngredient outputs);

            public abstract Builder mainIngredients(ImmutableList<com.github.yin.recipe.model.Recipe> mainIngredients);

            public abstract Recipe build();
        }
    }

    @AutoValue
    public static abstract class NameIngredient implements com.github.yin.recipe.model.NameIngredient {
        public Builder builder() {
            return new AutoValue_Bootstrap_NameIngredient.Builder();
        }

        @AutoValue.Builder
        public static abstract class Builder {
            public abstract Builder value(String value);

            public abstract NameIngredient build();
        }
    }

    @AutoValue
    public static abstract class OutputsIngredient implements com.github.yin.recipe.model.OutputsIngredient {
        public Builder builder() {
            return new AutoValue_Bootstrap_OutputsIngredient.Builder();
        }

        @AutoValue.Builder
        public static abstract class Builder {
            public abstract Builder values(ImmutableList values);

            public abstract OutputsIngredient build();
        }
    }

    @AutoValue
    public static abstract class Message implements com.github.yin.recipe.model.Message {
        public Builder builder() {
            return new AutoValue_Bootstrap_Message.Builder();
        }

        @AutoValue.Builder
        public static abstract class Builder {
            public abstract Builder name(String name);

            public abstract Builder fields(ImmutableList<Field> fields);

            public abstract Message build();
        }
    }
}
