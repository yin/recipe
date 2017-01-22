package com.github.yin.recipe.templating.st4;

import com.github.yin.recipe.model.Ingredient;
import com.github.yin.recipe.templating.TargetTemplate;
import com.google.common.collect.ImmutableMap;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.stringtemplate.StringTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stringtemplate.v4.AutoIndentWriter;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.compiler.GroupLexer;
import org.stringtemplate.v4.compiler.GroupParser;
import org.stringtemplate.v4.misc.ErrorType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

/**
 * Generates code using {@link StringTemplate}.
 */
public class St4TargetTemplate implements TargetTemplate {
    private static final Logger log = LoggerFactory.getLogger(ProperlyWorkingSTGroup.class);
    private final String target;
    private final Class<? extends Ingredient> type;
    private final ProperlyWorkingSTGroup stg;

    public St4TargetTemplate(String target, Class<? extends Ingredient> type) throws IOException {
        URL locate = St4Templates.locate(type.getName(), target);
        this.stg = new ProperlyWorkingSTGroup();
        stg.load(locate.openStream());
        this.target = target;
        this.type = type;
    }

    @Override
    public void generate(Ingredient ingredient, TargetElement element, OutputStream out) throws IOException {
        //TODO yin: Create load Locator value object
        String template = element.locator().split(" ")[0];
        ST st = stg.getInstanceOf("/" + template);
        // TODO yin: Have load way to expose required template inputs
        st.add(element.locator().split(" ")[1], ingredient);
        st.add("java", ImmutableMap.of("package", "recipe.test"));
        try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
            st.write(new AutoIndentWriter(writer));
        }
    }

    private static class ProperlyWorkingSTGroup extends STGroup {
        private static final Logger log = LoggerFactory.getLogger(ProperlyWorkingSTGroup.class);

        public ProperlyWorkingSTGroup load(InputStream in) {
            if (log.isDebugEnabled()) {
                log.debug("Loading string-template group");
            }
            try {
                ANTLRInputStream fs = new ANTLRInputStream(in, this.encoding);
                GroupLexer lexer = new GroupLexer(fs);
                fs.name = "@input";
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                GroupParser parser = new GroupParser(tokens);
                parser.group(this, "/");
            } catch (Exception var8) {
                log.error("Error parsing StringTemplate group", var8);
                this.errMgr.IOError((ST) null, ErrorType.CANT_LOAD_GROUP_FILE, var8, "@input");
            }
            return this;
        }
    }
}
