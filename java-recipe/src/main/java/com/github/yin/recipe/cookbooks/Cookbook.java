package com.github.yin.recipe.cookbooks;

import com.github.yin.recipe.model.Ingredient;
import com.github.yin.recipe.templating.TargetTemplate;

import java.util.ServiceLoader;

/**
 * Serves as plugin API for all Recipe cookbooks. Implementing classes will be
 * loaded into normal Recipe run using {@link ServiceLoader}, so all
 * implementing classes must properly register with {@link ServiceLoader}.
 */
public interface Cookbook<T extends Ingredient> {
    Class<T> ingredientClass();
    IngredientProcessor processor(Ingredient ingredient);
    OutStreamLocator outStreamLocatorFor(Ingredient ingredient);
    TargetMaterializer targetFor(Ingredient ingredient);

    TargetTemplate targetTemplate(String target);
}
