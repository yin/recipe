package com.github.yin.recipe.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * Represents a value class in any target language.
 */
public interface Message {
    interface Field {
        enum Modifiers {OPTIONAL, REPEATED, DEPRECATED}

        String name();

        String type();

        ImmutableSet<Modifiers> modifiers();
    }

    String name();
    ImmutableList<Field> fields();
}
