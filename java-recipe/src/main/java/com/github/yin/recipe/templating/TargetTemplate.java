package com.github.yin.recipe.templating;

import com.google.auto.value.AutoValue;

import java.io.InputStream;
import java.util.Set;

/**
 * Runs the code-generation and produces a hierarchical collection of resources
 * encapsulating the outputs.
 */
@FunctionalInterface
public interface TargetTemplate {
    /**
     * Generates the outputs based on target-template and the data provided by
     * recipe ingredient.
     * @returns A {@link Set} of resources located in a hierarchy.
     * This is likely to be change to a proper model to be queried.
     */
    Set<TargetElement> generate(Object data);

    /**
     * Maps code-generator output to a location in a hierarchical structure, so
     * each output can be materialized in a proper location in the target system.
     */
    @AutoValue
    abstract class TargetElement {
        /** Locates a resource in 1+ hierarchies, syntax TBD */
        public abstract String locator();
        /**
         * Enables to query the resource collection for dependecy relations and
         * determine which resources actually need to be materialized
         */
        public abstract Iterable<String> depenencySelectors();
        public abstract InputStream content();
    }
}
