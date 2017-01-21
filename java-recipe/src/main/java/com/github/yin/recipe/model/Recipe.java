package com.github.yin.recipe.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

/**
 * Represents a recipe for bootstrapping purposes.
 */
@JsonDeserialize(builder = AutoValue_Recipe.Builder.class)
@AutoValue
public abstract class Recipe {
    public static Recipe create(@JsonDeserialize(as = Reference.class) Reference name,
                                @JsonDeserialize(as = Targets.class) Targets targets,
                                @JsonDeserialize(contentAs = Ingredient.class) ImmutableList<Ingredient> mainIngredients) {
        return builder().name(name).targets(targets).mainIngredients(mainIngredients).build();
    }

    public static Builder builder() {
        return new AutoValue_Recipe.Builder();
    }

    @JsonProperty("name")
    public abstract Reference name();

    @JsonProperty("targets")
    public abstract Targets targets();

    @JsonProperty("ingredients")
    public abstract ImmutableList<Ingredient> mainIngredients();

    @AutoValue.Builder
    public static abstract class Builder {
        @JsonProperty("name")
        public abstract Builder name(Reference name);

        @JsonProperty("targets")
        public abstract Builder targets(Targets targets);

        @JsonProperty("ingredients")
        public abstract Builder mainIngredients(ImmutableList<Ingredient> mainIngredients);

        public abstract Recipe build();
    }
}
