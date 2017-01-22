package com.github.yin.recipe.processing;

import com.github.yin.recipe.cookbooks.Cookbook;
import com.github.yin.recipe.cookbooks.Cookbooks;
import com.github.yin.recipe.model.Ingredient;
import com.github.yin.recipe.model.Recipe;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cooks all the ingredients in load {@link Recipe}.
 */
public class Chef {
    private static final Logger log = LoggerFactory.getLogger(Chef.class);

    private final Cookbooks cookbooks;

    @Inject
    public Chef(Cookbooks cookbooks) {
        this.cookbooks = cookbooks;
    }

    public void process(Recipe recipe) {
        for (Ingredient ingredient : recipe.ingredients()) {
            Cookbook cookbook = cookbooks.forIngredient(ingredient.getClass());
            if (cookbook != null) {
                for (String target : recipe.targets().values()) {
                    try {
                        cookbook.process(ingredient, target);
                    } catch (Exception e) {
                        log.error("Error processing recipe", e);
                        // this is ok, for now
                    }
                }
            } else {
                log.error("No Cookbook for ingredient {}", ingredient);
            }
            //TODO yin: Collect processing results/errors
        }
    }
}
