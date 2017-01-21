package com.github.yin.recipe.templating.st4;

import com.github.yin.recipe.model.Ingredient;
import com.github.yin.recipe.templating.TargetTemplate;
import com.google.common.collect.ImmutableSet;
import org.antlr.stringtemplate.StringTemplate;

import java.util.Set;

/**
 * Generates code using {@link StringTemplate}.
 */
public class St4TargetTemplate implements TargetTemplate {

    private final Ingredient ingredient;

    public St4TargetTemplate(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public Set<TargetElement> generate() {
        return ImmutableSet.of(TargetElement.create(ingredient.name(), ImmutableSet.of()));
    }
}
