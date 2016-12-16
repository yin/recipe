package recipe;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
@AutoValue
public abstract class Item {
    public Item create(int id, String content) {
        return new AutoValue_Item(id, content);
    }
    public abstract int id();
    public abstract String content();

    public static TypeAdapter<Item> typeAdapter(Gson gson) {
        return new AutoValue_Item.GsonTypeAdapter(gson);
    }
}
