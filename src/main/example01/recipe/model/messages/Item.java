package recipe.model.messages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * I am so stupid, that I forgotten to change this javadoc, me fool.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_Item.Builder.class)
public abstract class Item {
    public abstract int id();
    public abstract String content();

    public static Builder builder() {
        return new AutoValue_Item.Builder();
    }
    public static Item create(int id, String content) {
        return builder().id(id).content(content).build();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder id(int id);
        public abstract Builder content(String content);
        public abstract Item build();
    }
}
