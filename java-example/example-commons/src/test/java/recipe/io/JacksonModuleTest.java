package recipe.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import org.junit.Before;
import org.junit.Test;
import recipe.model.messages.Item;
import recipe.model.messages.Query;
import recipe.model.messages.QueryRequest;
import recipe.model.messages.QueryResponse;

import static org.junit.Assert.*;

public class JacksonModuleTest {
    private static ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = Guice.createInjector(new JacksonModule()).getInstance(ObjectMapper.class);
    }

    @Test
    public void queryRequest_serialize() throws Exception {
        QueryRequest request = QueryRequest.create(Query.create(1, 3));
        String serialized = mapper.writeValueAsString(request);

        assertEquals(serialized, "{\"query\":{\"low\":1,\"hi\":3}}");
    }

    @Test
    public void queryRequest_deserialize() throws Exception {
        QueryRequest deserialized = mapper.readValue(
                "{\"query\":{\"low\":1,\"hi\":3}}", QueryRequest.class);

        QueryRequest request = QueryRequest.create(Query.create(1, 3));
        assertEquals(request, deserialized);
    }

    @Test
    public void queryResponse_serialize() throws Exception {
        QueryResponse response = QueryResponse.create(ImmutableList.of(
                Item.create(1, "abc"),
                Item.create(100, "xxx")));
        String serialized = mapper.writeValueAsString(response);
        assertEquals(serialized, "{\"query\":[{\"id\":1,\"content\":\"abc\"},{\"id\":100,\"content\":\"xxx\"}]}");
    }

    @Test
    public void queryResponse_deserialize() throws Exception {
        QueryResponse deserialized = mapper.readValue(
                "{\"query\":[{\"id\":1,\"content\":\"abc\"},{\"id\":100,\"content\":\"xxx\"}]}",
                QueryResponse.class);

        QueryResponse response = QueryResponse.create(ImmutableList.of(
                Item.create(1, "abc"),
                Item.create(100, "xxx")));
        assertEquals(response, deserialized);
    }
}