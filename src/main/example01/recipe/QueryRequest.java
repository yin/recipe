package recipe;

import com.google.auto.value.AutoValue;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
@AutoValue
public abstract class QueryRequest {
    public QueryRequest create(Query query) {
        return new AutoValue_QueryRequest(query);
    }
    public abstract Query query();
}
