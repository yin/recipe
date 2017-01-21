package com.github.yin.recipe.cookbooks;

import com.github.yin.recipe.templating.TargetTemplate;

import java.io.OutputStream;

/**
 * Locates destinations of {@link com.github.yin.recipe.templating.TargetTemplate.TargetElement}'s
 * and provides {@link java.io.OutputStream}'s for the {@link TargetMaterializer}.
 */
public interface OutStreamLocator {
    OutputStream locate(TargetTemplate.TargetElement element);
}
