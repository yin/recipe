package com.github.yin.recipe.io;

import java.io.IOException;

/**
 * Reads objects and nothing else.
 */
public interface DataReader<T> {
    T read() throws IOException;
}
