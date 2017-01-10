package recipe.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recipe.model.messages.Item;
import recipe.model.messages.Query;
import recipe.model.messages.QueryRequest;
import recipe.model.messages.QueryResponse;

import java.io.IOException;

/**
 * Serializes and the deserialized Item... hoping for the best.
 */
public class SerializationTester {
    private static Logger log = LoggerFactory.getLogger(SerializationTester.class);

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        test_QueryRequest(mapper);
        test_QueryResponse(mapper);
    }

    static void test_QueryRequest(ObjectMapper mapper) throws IOException {
        QueryRequest request = QueryRequest.create(Query.create(1, 3));
        String serialized = mapper.writeValueAsString(request);
        QueryRequest deserialized = mapper.readValue(serialized, QueryRequest.class);
        log.info("serialized request: " + serialized);
        log.info("equality:" + request.query().lowId() + " == " + deserialized.query().lowId() + " && "
                + request.query().highId() + " == " + deserialized.query().highId());
    }

    static void test_QueryResponse(ObjectMapper mapper) throws IOException {
        QueryResponse response = QueryResponse.create(ImmutableList.of(
                Item.create(1, "abc"),
                Item.create(100, "xxx")));
        String serialized = mapper.writeValueAsString(response);
        QueryResponse deserialized = mapper.readValue(serialized, QueryResponse.class);
        log.info("serialized request: " + serialized);
        log.info("equality:" + response.results().toString() + " == " + deserialized.results().toString());
    }
}
