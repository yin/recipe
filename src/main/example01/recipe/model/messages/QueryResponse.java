package recipe.model.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableCollection;

/**
 * Represents a response to a query request.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_QueryResponse.Builder.class)
public abstract class QueryResponse {

    @JsonProperty("query")
    public abstract ImmutableCollection<Item> results();

    public static Builder builder() {
        return new AutoValue_QueryResponse.Builder();
    }

    public static QueryResponse create(ImmutableCollection<Item> results) {
        return builder().results(results).build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("query")
        public abstract Builder results(ImmutableCollection<Item> results);
        public abstract QueryResponse build();
    }
}
