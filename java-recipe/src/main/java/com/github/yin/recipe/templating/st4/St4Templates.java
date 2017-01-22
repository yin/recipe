package com.github.yin.recipe.templating.st4;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Contains utility functions for StringTemplate engine.
 */
public class St4Templates {
    public static URL locate(String cookbook, String target) throws IOException {
        //TODO yin: Figure out how we want to manage recipe templates
        String resource = "st4/" + cookbook + "/" + target + ".stg";
        return St4Templates.class.getClassLoader().getResource(resource);
    }
}
