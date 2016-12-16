package recipe;

import com.google.auto.value.AutoValue;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
@AutoValue
public abstract class Query {
    public Item create(int lowId, int highId) {
        return new Autovalue_Item(lowId, highId);
    }
    public abstract int lowId();
    public abstract int highId();
}
