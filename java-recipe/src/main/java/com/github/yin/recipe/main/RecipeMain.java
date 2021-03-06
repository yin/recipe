package com.github.yin.recipe.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yin.recipe.cookbooks.Cookbooks;
import com.github.yin.recipe.cookbooks.CookbooksModule;
import com.github.yin.recipe.io.json.JacksonModule;
import com.github.yin.recipe.io.json.LocalJsonRecipeReader;
import com.github.yin.recipe.model.Recipe;
import com.github.yin.recipe.processing.Chef;
import com.google.inject.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Runs Recipe, at least what is implemented of it.
 */
public class RecipeMain {
    private final static Logger log = LoggerFactory.getLogger(RecipeMain.class);

    public static void main(String[] args) {
        for (String arg : args) {
            try {
                handleInputFile(arg);
            } catch (Exception e) {
                log.error("Processing recipe file resulted in error", e);
            }
        }
    }

    private static void handleInputFile(String inputFile) throws IOException {
        Injector injector = Guice.createInjector(new RecipeModule(inputFile));
        log.info("Loading file: {}", inputFile);
        LocalJsonRecipeReader reader = injector.getInstance(LocalJsonRecipeReader.class);
        Recipe recipe = reader.read();
        log.info("Loaded recipe: {}", recipe);
        log.info("Running Chef");
        Chef chef = injector.getInstance(Chef.class);
        chef.process(recipe);
        log.info("Chef done processing recipe");
    }

    private static class RecipeModule extends AbstractModule {
        private final String recipeFile;
        public RecipeModule(String recipeFile) {
            this.recipeFile = recipeFile;
        }

        @Override
        protected void configure() {
            install(new JacksonModule());
            install(new CookbooksModule());
        }

        @Provides
        public LocalJsonRecipeReader jsonReader(ObjectMapper mapper) {
            return new LocalJsonRecipeReader(mapper, Paths.get(recipeFile));
        }

        @Provides
        public Chef chef(Cookbooks cookbooks) {
            return new Chef(cookbooks);
        }
    }
}
