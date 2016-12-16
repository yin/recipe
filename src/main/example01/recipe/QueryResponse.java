package recipe;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableCollection;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
@AutoValue
public abstract class QueryResponse {
    public QueryResponse create(ImmutableCollection<Item> results) {
        return new AutoValue_QueryResponse(results);
    }
    public abstract ImmutableCollection<Item> results();
}
