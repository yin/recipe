package recipe.model.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_Item.Builder.class)
public abstract class Item {

    @JsonProperty("id")
    public abstract int id();

    @JsonProperty("content")
    public abstract String content();

    public static Builder builder() {
        return new AutoValue_Item.Builder();
    }
    public static Item create(int id, String content) {
        return builder().id(id).content(content).build();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        @JsonProperty("id")
        public abstract Builder id(int id);

        @JsonProperty("content")
        public abstract Builder content(String content);

        public abstract Item build();
    }
}
