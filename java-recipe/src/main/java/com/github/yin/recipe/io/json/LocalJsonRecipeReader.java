package com.github.yin.recipe.io.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yin.recipe.io.DataReader;
import com.github.yin.recipe.model.Recipe;
import com.github.yin.recipe.model.bootstrap.BootstrapRecipe;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

/**
 * Reads a local json file for a recipe.
 */
public class LocalJsonRecipeReader implements DataReader<Recipe> {
    private final ObjectMapper mapper;
    private final Path inputFile;

    public LocalJsonRecipeReader(ObjectMapper mapper, Path inputFile) {
        this.mapper = mapper;
        this.inputFile = inputFile;
    }

    @Override
    public Recipe read() throws IOException {
        try (InputStream in = Files.newInputStream(inputFile, StandardOpenOption.READ)) {
            JavaType recipeType = mapper.constructType(BootstrapRecipe.class);
            Object value = mapper.readValue(in, recipeType);
            return (Recipe) value;
        }
    }
}
