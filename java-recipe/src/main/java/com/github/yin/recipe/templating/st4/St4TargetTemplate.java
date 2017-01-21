package com.github.yin.recipe.templating.st4;

import com.github.yin.recipe.model.Ingredient;
import com.github.yin.recipe.templating.TargetTemplate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.antlr.stringtemplate.StringTemplate;
import org.stringtemplate.v4.AutoIndentWriter;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.io.*;
import java.util.Set;

/**
 * Generates code using {@link StringTemplate}.
 */
public class St4TargetTemplate implements TargetTemplate {

    private final Ingredient ingredient;

    public St4TargetTemplate(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public Set<TargetElement> generate() {
        return ImmutableSet.of(new St4TargetElement());
    }

    private class St4TargetElement extends TargetElement {
        @Override
        public String locator() {
            return "";
        }

        @Override
        public Iterable<String> depenencySelectors() {
            return ImmutableList.of();
        }

        @Override
        public InputStream content() throws IOException {
            STGroupFile stg = new STGroupFile(St4Templates.locate(ingredient));
            ST st = stg.getInstanceOf("output");
            // TODO yin: Have a way to expose required template inputs
            st.add("message", ingredient);
            st.add("java", ImmutableMap.of("package", "recipe.test"));
            PipedInputStream contents = new PipedInputStream();
            st.write(new AutoIndentWriter(new OutputStreamWriter(new PipedOutputStream(contents))));
            return contents;
        }
    }
}
