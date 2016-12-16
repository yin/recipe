package recipe;

import com.google.auto.value.AutoValue;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
@AutoValue
public abstract class Item {
    public Item create(int id, String content) {
        return new Autovalue_Item(id, content);
    }
    public abstract int id();
    public abstract String content();
}
