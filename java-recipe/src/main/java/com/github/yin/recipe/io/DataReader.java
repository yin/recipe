package com.github.yin.recipe.io;

import java.io.IOException;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
public interface DataReader<T> {
    T read() throws IOException;
}
