package com.github.yin.recipe.cookbooks;

import com.github.yin.recipe.model.Ingredient;
import com.github.yin.recipe.templating.TargetTemplate;

import java.io.OutputStream;
import java.util.ServiceLoader;

/**
 * Serves as plugin API for all Recipe cookbooks. Implementing classes will be
 * loaded into normal Recipe run using {@link ServiceLoader}, so all
 * implementing classes must properly register with {@link ServiceLoader}.
 */
public interface Cookbook<T extends Ingredient> {
    Class<T> ingredientClass();
    IngredientProcessor processor();
    OutStreamLocator outStreamLocator();
    TargetMaterializer materializer();
    TargetTemplate targetTemplate(String target);

    /**
     * Processes {@link Ingredient}'s and produces TargetElements to be materialized.
     */
    @FunctionalInterface
    interface IngredientProcessor<T extends Ingredient> {
        void process(T ingredient, String target) throws Exception;
    }

    /**
     * Locates destinations of {@link TargetTemplate.TargetElement}'s
     * and provides {@link java.io.OutputStream}'s for the {@link TargetMaterializer}.
     */
    interface OutStreamLocator {
        OutputStream locate(TargetTemplate.TargetElement element);
    }
}
