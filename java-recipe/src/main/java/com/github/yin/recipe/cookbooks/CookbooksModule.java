package com.github.yin.recipe.cookbooks;

import com.github.yin.recipe.model.Recipe;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import javax.inject.Provider;
import java.util.ServiceLoader;

/**
 * Creates Target Templates on-the-fly.
 */
public class CookbooksModule extends AbstractModule {
    public static class CookbooksProvider implements Provider {
        @Override public Object get() {
            return ServiceLoader.load(Cookbook.class);
        }
    }

    private final Recipe recipe;

    public CookbooksModule(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    protected void configure() {
        bind(Cookbooks.class).to(Cookbooks.class).in(Scopes.SINGLETON);
    }
}
