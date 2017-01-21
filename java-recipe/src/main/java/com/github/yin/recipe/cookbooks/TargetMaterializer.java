package com.github.yin.recipe.cookbooks;

import com.github.yin.recipe.templating.TargetTemplate;

/**
 * Materializes {@link TargetTemplate.TargetElement}'s by invoking the code-generator
 * and streaming the content into an {@link java.io.OutputStream} provided by
 * {@link OutStreamLocator}.
 */
public interface TargetMaterializer {
    //TODO yin: Use proper Exception usage and use it right
    void materialize(TargetTemplate.TargetElement element) throws Exception;

    class MaterializerException extends Exception {
        public MaterializerException(String msg) {
            super(msg);
        }
        public MaterializerException(String msg, Throwable t) {
            super(msg, t);
        }
    }
}