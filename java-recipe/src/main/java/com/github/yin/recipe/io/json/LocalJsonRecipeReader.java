package com.github.yin.recipe.io.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yin.recipe.io.FileReader;
import com.github.yin.recipe.model.Recipe;
import com.github.yin.recipe.model.bootstrap.BootstrapRecipe;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
public class LocalJsonRecipeReader implements FileReader<Recipe> {
    private final Path inputFile;

    public LocalJsonRecipeReader(Path inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public Recipe read() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream in = Files.newInputStream(inputFile, null)) {
            JavaType recipeType = mapper.constructType(BootstrapRecipe.class);
            Object value = mapper.readValue(in, recipeType);
            return (Recipe) value;
        }
    }
}
