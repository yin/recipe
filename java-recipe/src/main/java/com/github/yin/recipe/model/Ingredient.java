package com.github.yin.recipe.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a value class for bootstrapping purposes.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AutoValue_Message.class, name = "message"),
        @JsonSubTypes.Type(value = AutoValue_Service.class, name = "service") })
public abstract class Ingredient {
    /**
     * Name of the ingredient, i.e.: message
     */
    @JsonProperty("name")
    public abstract String name();
}
