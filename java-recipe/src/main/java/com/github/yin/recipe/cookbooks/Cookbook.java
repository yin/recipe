package com.github.yin.recipe.cookbooks;

import com.github.yin.recipe.model.Ingredient;
import com.github.yin.recipe.templating.TargetTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * Serves as plugin API for all Recipe cookbooks. Implementing classes will be
 * loaded into normal Recipe run using {@link ServiceLoader}, so all
 * implementing classes must properly register with {@link ServiceLoader}.
 */
@FunctionalInterface
public interface Cookbook<T extends Ingredient> {
    /** Exception raised while processing an ingredient. */
    class CookbookError extends Exception {
    }

    default Class<T> ingredientClass() {
        throw new NotImplementedException();
    }

    void process(T ingredient, String target) throws Exception;

    /**
     * Processes {@link Ingredient}'s and produces TargetElements to be materialized.
     */
    @FunctionalInterface
    interface IngredientProcessor<T extends Ingredient> {
        Set<TargetTemplate.TargetElement> process(T ingredient, String target) throws Exception;

    }
    /**
     * Locates destinations of {@link TargetTemplate.TargetElement}'s
     * and provides {@link java.io.OutputStream}'s for the {@link TargetMaterializer}.
     */
    interface OutputLocator {
        Path locate(TargetTemplate.TargetElement element) throws IOException;
    }
}
