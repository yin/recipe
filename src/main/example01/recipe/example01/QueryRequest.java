package recipe.example01;

import com.google.auto.value.AutoValue;

/**
 * Represents a request for a query.
 */
@AutoValue
public abstract class QueryRequest {
    public QueryRequest create(Query query) {
        return new AutoValue_QueryRequest(query);
    }
    public abstract Query query();
}
