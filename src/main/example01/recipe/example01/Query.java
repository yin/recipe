package recipe.example01;

import com.google.auto.value.AutoValue;

/**
 * Represents a query for records with ids satisfying a given range.
 */
@AutoValue
public abstract class Query {
    public Query create(long lowId, long highId) {
        return new AutoValue_Query(lowId, highId);
    }
    public abstract long lowId();
    public abstract long highId();
}
