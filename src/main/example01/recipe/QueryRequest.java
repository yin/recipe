package recipe;

import com.google.auto.value.AutoValue;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
@AutoValue
public abstract class QueryRequest {
    public Item create(Query query) {
        return new Autovalue_QueryRequest(query);
    }
    public abstract Query query();
}
