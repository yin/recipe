package recipe;

import com.google.auto.value.AutoValue;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
@AutoValue
public abstract class Query {
    public Query create(long lowId, long highId) {
        return new AutoValue_Query(lowId, highId);
    }
    public abstract long lowId();
    public abstract long highId();
}
