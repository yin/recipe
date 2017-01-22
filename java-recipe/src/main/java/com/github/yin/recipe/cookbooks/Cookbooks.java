package com.github.yin.recipe.cookbooks;

import com.github.yin.recipe.model.Ingredient;
import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

import javax.inject.Provider;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ServiceLoader;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Loads and keeps track of recipe cookbooks.
 */
public class Cookbooks {
    private final Iterable<Cookbook> cookbooks;

    @Inject
    public Cookbooks(@CookbookRegistry Iterable<Cookbook> serviceLoader) {
        this.cookbooks = serviceLoader;
    }
    public Cookbook forIngredient(Class<? extends Ingredient> clazz) {
        for (Cookbook module : cookbooks) {
            if (module.ingredientClass().isAssignableFrom(clazz)) {
                return module;
            }
        }
        return null;
    }

    public static class CookbooksProvider implements Provider<Iterable<Cookbook>> {
        @Override public ServiceLoader<Cookbook> get() {
            return ServiceLoader.load(Cookbook.class);
        }
    }

    /**
     * Annotation for {@link Iterable}'s of available {@link Cookbook}'s.
     */
    @BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
    public @interface CookbookRegistry {
    }
}
