package com.github.yin.recipe.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Represents a value class for bootstrapping purposes.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_Service.Builder.class)
public abstract class Service extends Ingredient {
    @AutoValue
    @JsonDeserialize(builder = AutoValue_Service_Endpoint.Builder.class)
    public static abstract class Endpoint {
        public static Endpoint create(String name, Reference output, ImmutableMap<String, Reference> inputs) {
            return builder().name(name).output(output).inputs(inputs).build();
        }

        public static Builder builder() {
            return new AutoValue_Service_Endpoint.Builder();
        }

        @JsonProperty("name")
        public abstract String name();

        @JsonProperty("output")
        public abstract Reference output();

        @JsonProperty("inputs")
        public abstract ImmutableMap<String, Reference> inputs();

        @AutoValue.Builder
        public static abstract class Builder {
            @JsonProperty("name")
            public abstract Builder name(String name);

            @JsonProperty("output")
            public abstract Builder output(Reference output);

            @JsonProperty("inputs")
            public abstract Builder inputs(ImmutableMap<String, Reference> inputs);

            public abstract Endpoint build();
        }
    }

    public static Service create(String name, ImmutableList<Endpoint> endpoints) {
        return builder().name(name).endpoints(endpoints).build();
    }

    public static Builder builder() {
        return new AutoValue_Service.Builder();
    }

    @JsonProperty("name")
    public abstract String name();

    @JsonProperty("endpoints")
    public abstract ImmutableList<Endpoint> endpoints();

    @AutoValue.Builder
    public static abstract class Builder {
        @JsonProperty("name")
        public abstract Builder name(String name);

        @JsonProperty("endpoints")
        public abstract Builder endpoints(ImmutableList<Endpoint> methods);

        public abstract Service build();
    }
}
