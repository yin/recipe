package recipe.model.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * Represents a query for records with ids satisfying a given range.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_Query.Builder.class)
public abstract class Query {
    @JsonProperty("low")
    public abstract long lowId();

    @JsonProperty("hi")
    public abstract long highId();

    public static Query.Builder builder() {
        return new AutoValue_Query.Builder();
    }

    public static Query create(long low, long high) {
        return builder().lowId(low).highId(high).build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonProperty("low")
        public abstract Builder lowId(long lowId);

        @JsonProperty("hi")
        public abstract Builder highId(long highId);

        public abstract Query build();
    }
}
