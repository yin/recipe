package recipe.model.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * Represents a request with a query.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_QueryRequest.Builder.class)
public abstract class QueryRequest {

    @JsonProperty("query")
    public abstract Query query();

    public static Builder builder() {
        return new AutoValue_QueryRequest.Builder();
    }

    public static QueryRequest create(Query query) {
        return builder().query(query).build();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("query")
        public abstract Builder query(Query query);

        public abstract QueryRequest build();
    }
}
