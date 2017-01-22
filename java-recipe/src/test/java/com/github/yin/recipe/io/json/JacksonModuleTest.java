package com.github.yin.recipe.io.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yin.recipe.model.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Guice;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static com.github.yin.recipe.model.Message.Field.Modifiers.OPTIONAL;
import static junit.framework.TestCase.assertEquals;

public class JacksonModuleTest {
    private static ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = Guice.createInjector(new JacksonModule()).getInstance(ObjectMapper.class);
    }

    @Test
    public void queryRequest_serialize() throws Exception {
        Recipe recipe = Recipe.create(
                Reference.create("test-recipe"),
                Targets.create(ImmutableList.of("java")),
                ImmutableList.of(
                        Message.create("Message01", ImmutableList.of(
                                Message.Field.create("id", "int64", ImmutableSet.of()),
                                Message.Field.create("text", "string", ImmutableSet.of(OPTIONAL))
                        )),
                        Service.create("ListingService", ImmutableList.of(
                                Service.Endpoint.create("getMessages", Reference.create("Message01"),
                                        ImmutableMap.of(
                                                "id", Reference.create("int64")
                                        ))
                        ))
                ));
        String serialized = mapper.writeValueAsString(recipe);
        assertEquals("{\"name\":\"test-recipe\",\"targets\":[\"java\"],\"ingredients\":["
                        + "{\"message\":"
                        + "{\"name\":\"Message01\",\"fields\":[{\"name\":\"id\",\"type\":\"int64\",\"modifiers\":[]},"
                        + "{\"name\":\"text\",\"type\":\"string\",\"modifiers\":[\"OPTIONAL\"]}]}},"
                        + "{\"service\":"
                        + "{\"name\":\"ListingService\",\"endpoints\":["
                        + "{\"name\":\"getMessages\",\"output\":\"Message01\",\"inputs\":{\"id\":\"int64\"}}]}}]}",
                serialized);
    }

    @Test
    public void queryRequest_deserialize() throws Exception {
       InputStream in = this.getClass().getClassLoader().getResourceAsStream("fixtures/example01.json");
        Recipe deserialized = mapper.readValue(in, Recipe.class);
        Recipe recipe = Recipe.create(
                Reference.create("test-recipe"),
                Targets.create(ImmutableList.of("java")),
                ImmutableList.of(
                        Message.create("Message01", ImmutableList.of(
                                Message.Field.create("id", "int64", ImmutableSet.of()),
                                Message.Field.create("text", "string", ImmutableSet.of(OPTIONAL))
                        )),
                        Service.create("ListingService", ImmutableList.of(
                                Service.Endpoint.create("getMessages", Reference.create("Message01"),
                                        ImmutableMap.of(
                                                "id", Reference.create("int64")
                                        ))
                        ))
                ));
        assertEquals(recipe, deserialized);
    }
}