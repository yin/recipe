package com.github.yin.recipe.modules.message;

import com.github.yin.recipe.cookbooks.Cookbook;
import com.github.yin.recipe.cookbooks.TargetMaterializer;
import com.github.yin.recipe.model.Message;
import com.github.yin.recipe.templating.TargetTemplate;
import com.github.yin.recipe.templating.TargetTemplate.TargetElement;
import com.github.yin.recipe.templating.st4.St4TargetTemplate;
import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static java.nio.file.StandardOpenOption.*;

/**
 * Serves as main class for <code>message</code> ingredients.
 */
@AutoService(value = Cookbook.class)
public class MessageCookbook implements Cookbook<Message> {
    private static final Logger log = LoggerFactory.getLogger(MessageCookbook.class);
    @Override
    public Class<Message> ingredientClass() {
        return Message.class;
    }

    @Override
    public void process(Message message, String target) throws Exception {
        Set<TargetElement> elements = processor().process(message, target);
        for (TargetElement element : elements) {
            materializer().materialize(message, target, element);
        }
    }

    public IngredientProcessor<Message> processor() {
        return (Message message, String target) -> ImmutableSet.of(
                    TargetElement.create("output message " + message.name() + ".java",
                            ImmutableSet.of())
            );
    }

    public OutputLocator outStreamLocator() {
        return (TargetElement element) ->
                Paths.get(element.locator().split(" ")[2]);
    }

    public TargetMaterializer materializer() {
        return (Message message, String target, TargetElement element) -> {
            Path located = outStreamLocator().locate(element);
            log.info("Writting file {}", located);
            try(OutputStream out = Files.newOutputStream(located, WRITE, CREATE, TRUNCATE_EXISTING)) {
                targetTemplate(target).generate(message, element, out);
            }
        };
    }

    public TargetTemplate targetTemplate(String target) throws IOException {
        return new St4TargetTemplate(target, ingredientClass());
    }
}
