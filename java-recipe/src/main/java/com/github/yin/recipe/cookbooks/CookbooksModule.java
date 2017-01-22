package com.github.yin.recipe.cookbooks;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;

import javax.inject.Provider;
import java.util.ServiceLoader;

/**
 * Creates Target Templates on-the-fly.
 */
public class CookbooksModule extends AbstractModule {
    public static class CookbooksProvider implements Provider<Iterable<Cookbook>> {
        @Override public Iterable<Cookbook> get() {
            return ServiceLoader.load(Cookbook.class);
        }
    }

    @Override
    protected void configure() {
        bind(Cookbooks.class).in(Scopes.SINGLETON);
        bind(new TypeLiteral<Iterable<Cookbook>>() {})
                .annotatedWith(Cookbooks.CookbookRegistry.class)
                .toProvider(CookbooksProvider.class);
    }
}
